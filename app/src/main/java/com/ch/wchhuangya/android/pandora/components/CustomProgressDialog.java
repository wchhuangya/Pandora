package com.ch.wchhuangya.android.pandora.components;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ch.wchhuangya.android.pandora.R;

/**
 * 类名: CustomProgressDialog <br/> 
 * 功能: 自定义进度提示控件. <br/> 
 * 创建时间: 2014-9-16 上午11:39:25 <br/> 
 * 
 * @author wchhuangya 
 * @version  
 * @since Jdk 1.6
 */
public class CustomProgressDialog extends Dialog {

    @SuppressWarnings("unused")
	private Context mContext = null;

    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context){
        super(context);
        this.mContext = context;
    }

	public static Dialog show(Context context){

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.common_loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog_vartical);// 创建自定义样式dialog

		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

		loadingDialog.show();
		return loadingDialog;
	}
}
