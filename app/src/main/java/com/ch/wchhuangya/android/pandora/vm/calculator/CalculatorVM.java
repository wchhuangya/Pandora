package com.ch.wchhuangya.android.pandora.vm.calculator;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Button;

import com.ch.wchhuangya.android.pandora.vm.BaseVM;

/**
 * Created by wchya on 2016-12-07 16:17
 */

public class CalculatorVM extends BaseVM {

    /** 用于定义操作符后的空格显示 */
    public static final String EMPTY_STR = " ";
    /** 用于定义结果数字前的显示 */
    public static final String EQUALS_EMPTY_STR = "= ";

    /** 被操作数 */
    public ObservableField<String> firstNum = new ObservableField<>("0");
    /** 上一次结果 */
    public ObservableField<String> secondNum = new ObservableField<>("");
    /** 当前结果 */
    public ObservableField<String> resNum = new ObservableField<>("");

    /** 被操作数的数值 */
    double fNum;
    /** 上一次结果的数值 */
    double sNum;
    /** 当前结果的数值 */
    double rNum;
    /** 标识当前是否为初始状态 */
    boolean initState = true;
    /** 当前运算符 */
    CalOperator mCurOperator;
    /** 前一运算符 */
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
                handleResNum(EQUALS_EMPTY_STR + String.valueOf(fNum), Double.parseDouble(changedVal));
            } else { // 首位是运算符，计算结果后显示

                if (originalVal.length() == 3 && Double.parseDouble(originalVal.substring(2)) == 0L) // 被操作数是 运算符 + 空格 + 0
                    handleFirstNum(mCurOperator.value + EMPTY_STR, Double.parseDouble(btnVal));
                else
                    handleFirstNum(originalVal + btnVal, Double.parseDouble((originalVal + btnVal).substring(2)));

                cal();
            }
        }
        adjustNums();
        setInitState(false);
    }

    /** 退格键事件 */
    public void del() {
        String first = firstNum.get();
        if (secondNum.get().length() > 0) { // 正在计算

            if (first.length() <= 3) { // firstNum 是运算符，把 secondNum 的值赋值给 firstNum，secondNum 清空
                handleFirstNum(sNum + "", sNum);
                handleResNum(EQUALS_EMPTY_STR + secondNum.get(), sNum);
                handleSecondNum("", 0L);
                mCurOperator = null;
            } else { // 把最后一个数字删除，重新计算
                String changedVal = first.substring(0, first.length() - 1);
                handleFirstNum(changedVal, Double.parseDouble(changedVal.substring(2)));
                cal();
            }
        } else { // 没有计算

            if ((first.startsWith("-") && first.length() == 2) || first.length() == 1) { // 只有一位数字
                setInitState(true);
                handleFirstNum("0", 0L);
                handleResNum("", 0L);
            } else {
                String changedFirst = first.substring(0, firstNum.get().length() - 1);
                handleFirstNum(changedFirst, Double.parseDouble(changedFirst));
                handleResNum(EQUALS_EMPTY_STR + fNum, fNum);
            }
        }
        adjustNums();
    }

    /** 运算符点击处理 */
    public void operatorClick(View view) {
        String btnVal = ((Button) view).getText().toString();

        // 如果当前有运算符，并且运算符后有数字，把当前运算符赋值给前一运算符
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
        setInitState(false);
        adjustNums();
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
            setInitState(false);
            String val = firstNum.get();

            if (!Character.isDigit(val.charAt(0)) && val.length() == 2) {
                handleFirstNum(val + "0.", fNum);
            } else
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

    /** 计算结果 */
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
                    handleResNum("= ∞", rNum);
                } else {
                    rNum = sNum / fNum;
                    handleResNum(EQUALS_EMPTY_STR + rNum, rNum);
                }
                break;
        }
        adjustNums();
    }

    /**
     * 调整结果，主要将最后无用的 .0 去掉
     */
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

    /** 处理被操作数的显示和值 */
    private void handleFirstNum(String values, double val) {
        firstNum.set(values);
        fNum = val;
    }

    /** 处理上次结果的显示和值 */
    private void handleSecondNum(String values, double val) {
        secondNum.set(values);
        sNum = val;
    }

    /** 处理本次结果的显示和值 */
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
        // 释放其它资源
        mContext = null;

        // 取掉观察者的注册
        unsubscribe();
    }
}
