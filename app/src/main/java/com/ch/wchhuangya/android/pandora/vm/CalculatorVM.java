package com.ch.wchhuangya.android.pandora.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Button;

/**
 * Created by wchya on 2016-12-07 16:17
 */

public class CalculatorVM extends BaseVM {

    public ObservableField<String> firstNum = new ObservableField<>("0");
    public ObservableField<String> secondNum = new ObservableField<>("");
    public ObservableField<String> resNum = new ObservableField<>("");

    double fNum;
    double sNum;
    double rNum;
    boolean initState = true;
    CalOperator mOperator;

    enum CalOperator {
        ADD,
        MINUS,
        MULTIPLY,
        DIVIDE
    }

    public CalculatorVM(Context context) {
        mContext = context;
    }

    public void numClick(View view) {
        String val = ((Button) view).getText().toString();

//        boolean resEmpty = resNum.get().length() > 0;
        boolean firstIsDigit = Character.isDigit(firstNum.get().charAt(0));

        if (isInitState()) { // 初始状态（既刚打开页面或点击了 Clear）
            firstNum.set(val);
            resNum.set("= " + val);
        } else {
            firstNum.set(firstNum.get() + val);

            if (firstIsDigit) { // 首位是数字，直接在数字后添加
                resNum.set(resNum.get() + val);
                fNum = Double.parseDouble(firstNum.get());
            } else { // 首位是操作符，计算结果后显示
                fNum = Double.parseDouble(firstNum.get().substring(2));
                sNum = Double.parseDouble(secondNum.get());

            }
        }

        /*firstNum.set(firstNum.get().length() == 1 ? firstNum.get() + " " + val : firstNum.get() + val);
        if (resEmpty) {
            resNum.set("= " + val);
        }
        else {
            resNum.set(resNum.get() + val);
        }


        if (Character.isDigit(firstNum.get().charAt(0))) { // 如果首位是数字，直接在数字后添加
            firstNum.set(firstNum.get() + val);
        } else { // 如果首位是操作符
            if (firstNum.get().length() == 1) { // 如果只有操作符，添加空格后加数字
                firstNum.set(firstNum.get() + " " + val);
            } else { // 如果既有操作符又有数字，在后面添加
                firstNum.set(firstNum.get() + val);
            }
        }

        if (resEmpty)
            resNum.set("= " + val);
        else
            resNum.set(resNum.get() + val);*/

        setInitState(false);
    }

    public void del() {
        boolean firstIsDigit = Character.isDigit(firstNum.get().charAt(0));

        if (secondNum.get().length() > 0) { // 正在计算

        } else { // 没有计算
            String first = firstNum.get();
            if (firstIsDigit) { // 全部数字
                if (first.length() == 1)
                    setInitState(true);

                fNum = Double.parseDouble(firstNum.get());
                firstNum.set(first.length() > 1 ? first.substring(0, firstNum.get().length() - 1) : "0");

                resNum.set(firstNum.get().equals("0") ? "" : ("= " + firstNum.get()));
                rNum = Double.parseDouble(firstNum.get());
            } else if (first.length() == 1) { // 只有一个操作符
                setInitState(true);

                secondNum.set("");
                sNum = 0L;

                firstNum.set("0");
                fNum = 0L;

                resNum.set("");
                rNum = 0L;
            } else { // 既有操作符又有数字
                firstNum.set(first.substring(0, first.length() - 1));
                fNum = Double.parseDouble(first.substring(2));
            }
        }
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
