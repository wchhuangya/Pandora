package com.ch.wchhuangya.android.pandora.vm.life;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.client.IdCardInfoHandle;
import com.ch.wchhuangya.android.pandora.model.IdCardInfo;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.util.Constant;
import com.ch.wchhuangya.lib.util.CroutonUtil;
import com.ch.wchhuangya.lib.util.RegularUtil;
import com.ch.wchhuangya.lib.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by wchya on 2017-01-01 20:37
 */

public class QueryIdentificationCardInfoVM extends BaseVM {

    public ObservableField<String> sex = new ObservableField<>();
    public ObservableField<String> birth = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();

    public QueryIdentificationCardInfoVM(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void initBtn(Button btn, EditText et) {
        btn.setOnClickListener(view -> {
            String input = et.getText().toString();

            if (StringUtil.isEmpty(input))
                CroutonUtil.showAlert(mActivity, "请输入要查询的身份证号", R.id.card_info_fl);
            else {
                sex.set("");
                birth.set("");
                address.set("");
                pbShow.set(true);

                if (RegularUtil.validIDCard(input)) {

                    IdCardInfoHandle.idCardInfoHandle(input.trim(), idCardInfo -> {
                        String errNum = idCardInfo.get("errNum").toString();
                        if ("0".equals(errNum)) {
                            JsonObject retData = idCardInfo.get("retData").getAsJsonObject();
                            IdCardInfo.RetDataBean retDataBean = new Gson().fromJson(retData.toString(), IdCardInfo.RetDataBean.class);
                            sex.set(StringUtil.convertSex(retDataBean.getSex()));
                            birth.set(retDataBean.getBirthday());
                            address.set(retDataBean.getAddress());
                        } else {
                            CroutonUtil.showAlert(mActivity, StringUtil.delDoubleQuotes(idCardInfo.get("retMsg").toString()), R.id.card_info_middle_fl);
                        }
                    }, throwable -> {
                        pbShow.set(false);
                        Log.e(Constant.LOG_TAG, "initBtn: 获取身份证信息失败～", throwable);
                        CroutonUtil.showAlert(mActivity, "获取身份证信息失败", R.id.card_info_middle_fl);
                    }, () -> {
                        pbShow.set(false);
                    });
                } else {
                    CroutonUtil.showAlert(mActivity, "身份证号格式不正确", R.id.card_info_fl);
                }
            }
        });
    }

    @Override
    public void reset() {
        mContext = null;
        unsubscribe();
    }
}
