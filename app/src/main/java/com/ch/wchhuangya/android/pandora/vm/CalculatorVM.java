package com.ch.wchhuangya.android.pandora.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Button;

/**
 * Created by wchya on 2016-12-07 16:17
 */

public class CalculatorVM extends BaseVM {

    public static final String EMPTY_STR = " ";
    public static final String EQUALS_EMPTY_STR = "= ";
    public ObservableField<String> firstNum = new ObservableField<>("0");
    public ObservableField<String> secondNum = new ObservableField<>("");
    public ObservableField<String> resNum = new ObservableField<>("");

    double fNum;
    double sNum;
    double rNum;
    boolean initState = true;
    CalOperator mCurOperator;
    CalOperator mPreOperator;

    /** 运算符枚举 */
    enum CalOperator {
        ADD("+"),
        MINUS("－"),
        MULTIPLY("×"),
        DIVIDE("÷");

        private String value;

        CalOperator(String value) {
            this.value = value;
        }

        /** 根据运算符字符串获取运算符枚举 */
        public static CalOperator getOperator(String value) {
            CalOperator otor = null;
            for (CalOperator operator : CalOperator.values()) {
                if (operator.value.equals(value))
                    otor = operator;
            }
            return otor;
        }
    }

    public CalculatorVM(Context context) {
        mContext = context;
    }

    /**
     * 数字点击处理 
     * 当数字变化时，先变化 firstNum，然后计算结果
     */
    public void numClick(View view) {
        String btnVal = ((Button) view).getText().toString();

        if (btnVal.equals("0")) { // 当前点击 0 按钮
            if (firstNum.get().equals("0")) // 当前显示的为 0
                return;
        }

        String originalVal = firstNum.get();
        boolean firstIsDigit = Character.isDigit(originalVal.charAt(0));

        if (isInitState()) { // 初始状态（既刚打开页面或点击了 Clear 之后）
            handleFirstNum(btnVal, Double.parseDouble(btnVal));
            handleResNum(EQUALS_EMPTY_STR + btnVal, Double.parseDouble(btnVal));
        } else {
            if (firstIsDigit) { // 首位是数字，直接在数字后添加
                String changedVal = originalVal + btnVal;
                handleFirstNum(changedVal, Double.parseDouble(changedVal));
                handleResNum(String.valueOf(fNum), Double.parseDouble(changedVal));
            } else { // 首位是运算符，计算结果后显示

                if (originalVal.length() == 3 && Double.parseDouble(originalVal.substring(2)) == 0L)
                    handleFirstNum(mCurOperator.value + EMPTY_STR, Double.parseDouble(btnVal));
                else
                    handleFirstNum(originalVal + btnVal, Double.parseDouble((originalVal + btnVal).substring(2)));

                cal();
            }
        }

        setInitState(false);
    }

    /** 退格键事件 */
    public void del() {
        boolean firstIsDigit = Character.isDigit(firstNum.get().charAt(0));

        if (secondNum.get().length() > 0) { // 正在计算

        } else { // 没有计算
            String first = firstNum.get();

            if (firstIsDigit) { // 全部数字
                if (first.length() == 1) // 只有一位数字
                    setInitState(true);

                String changedFristNum = first.length() > 1 ? first.substring(0, firstNum.get().length() - 1) : "0";
                handleFirstNum(changedFristNum, Double.parseDouble(changedFristNum));

                String changedResNum = firstNum.get().equals("0") ? "" : (EQUALS_EMPTY_STR + firstNum.get());
                handleFirstNum(changedResNum, Double.parseDouble(changedFristNum));
            } else if (first.length() == 1) { // 只有一个运算符
                clear();
            } else { // 既有运算符又有数字，需要判断数字的位数
                if (first.length() == 3) { // 由 运算符 + 空格 + 1位数字组成
                    handleFirstNum(first.substring(0, 1), 0L);
                } else {
                    handleFirstNum(first.substring(0, first.length() - 1), Double.parseDouble(first.substring(2, first.length() - 3)));
                }
            }
        }
    }

    /** 运算符点击处理 */
    public void operatorClick(View view) {
        String btnVal = ((Button) view).getText().toString();
        if (mCurOperator != null && firstNum.get().length() >= 3)
            mPreOperator = mCurOperator;
        mCurOperator = CalOperator.getOperator(btnVal);

        if (secondNum.get().equals("")) { // 1. 没有 secondNum，把 firstNum 赋值给 secondNum，然后把运算符赋值给 firstNum
            handleSecondNum(firstNum.get(), Double.parseDouble(firstNum.get()));
            handleFirstNum(mCurOperator.value + EMPTY_STR, 0L);
        } else { // 2. 有 secondNum
            if (firstNum.get().length() == 2) { // 2.1 只有运算符时，只改变运算符显示，其它不变
                firstNum.set(mCurOperator.value + EMPTY_STR);
            } else { // 2.2 既有运算符，又有 firstNum 和 secondNum 时，计算结果
                if (mPreOperator != null) {
                    mPreOperator = null;

                    handleFirstNum(mCurOperator.value + EMPTY_STR, 0L);
                    handleSecondNum(rNum + "", rNum);
                } else {
                    cal();
                    handleFirstNum(mCurOperator.value + EMPTY_STR, 0L);
                }
            }
        }
    }

    /**
     * 点的事件处理
     * 1. 只能有一个点
     * 2. 输入点后，firstNum 的值不变，只改变显示
     */
    public void dotClick() {
        if (firstNum.get().contains("."))
            return;
        else {
            String val = firstNum.get();
            handleFirstNum(val + ".", fNum);
        }
    }

    /**
     * 百分号的事件处理
     * 1. 初始状态或刚刚经过 clear 操作时，点击无反应
     * 2. 当 firstNum 为运算符时，点击无反应
     * 3. 其余情况，点击后将 firstNum 乘以 0.01
     */
    public void percentClick() {
        String originalVal = firstNum.get();
        if (isInitState())
            return;
        else if (originalVal.length() == 1 && !Character.isDigit(originalVal.charAt(0)))
                return;
        else {
            fNum = fNum * 0.01;
            if (mCurOperator != null) {
                handleFirstNum(mCurOperator.value + " " + fNum, fNum);
                cal();
            } else {
                handleFirstNum(String.valueOf(fNum), fNum);
                handleResNum(String.valueOf(fNum), fNum);
            }
        }
    }

    /**
     * 等号事件处理
     * 1. 只有 firstNum，不作任何处理
     * 2. 有 secondNum 时，把 secondNum 和 firstNum 的值进行运算，然后把值赋值给 firstNum，清空 secondNum，
     */
    public void equalsClick() {
        if (!secondNum.get().equals("")) {
            cal();
            handleFirstNum(String.valueOf(rNum), rNum);
            handleSecondNum("", 0L);
        }
        adjustNums();
    }

    private void cal() {
        switch (mCurOperator) {
            case ADD:
                rNum = sNum + fNum;
                handleResNum(EQUALS_EMPTY_STR + rNum, rNum);
                break;
            case MINUS:
                rNum = sNum - fNum;
                handleResNum(EQUALS_EMPTY_STR + rNum, rNum);
                break;
            case MULTIPLY:
                rNum = sNum * fNum;
                handleResNum(EQUALS_EMPTY_STR + rNum, rNum);
                break;
            case DIVIDE:
                if (fNum == 0L) {
                    rNum = 0L;
                    handleFirstNum("= ∞", rNum);
                } else {
                    rNum = sNum / fNum;
                    handleResNum(EQUALS_EMPTY_STR + rNum, rNum);
                }
                break;
        }
        adjustNums();
    }

    private void adjustNums() {
        String ffNum = firstNum.get();
        String ssNum = secondNum.get();
        String rrNum = resNum.get();
        if (ffNum.endsWith(".0")) {
            firstNum.set(ffNum.substring(0, ffNum.length() - 2));
        }
        if (ssNum.endsWith(".0")) {
            secondNum.set(ssNum.substring(0, ssNum.length() - 2));
        }
        if (rrNum.endsWith(".0"))
            resNum.set(rrNum.substring(0, rrNum.length() - 2));
    }

    /** 将计算器恢复到初始状态 */
    public void clear() {
        setInitState(true);

        handleFirstNum("0", 0L);

        handleSecondNum("", 0L);

        handleResNum("", 0L);

        mCurOperator = null;
    }

    private void handleFirstNum(String values, double val) {
        firstNum.set(values);
        fNum = val;
    }

    private void handleSecondNum(String values, double val) {
        secondNum.set(values);
        sNum = val;
    }

    private void handleResNum(String values, double val) {
        resNum.set(values);
        rNum = val;
    }

    public boolean isInitState() {
        return initState;
    }

    public void setInitState(boolean initState) {
        this.initState = initState;
    }

    @Override
    public void reset() {

    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }
}
