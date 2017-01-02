package com.ch.wchhuangya.android.pandora.model;

/**
 * Created by wchya on 2017-01-02 10:04
 */

public class IdCardInfo {

    /**
     * errNum : 0
     * retMsg : success
     * retData : {"sex":"M","birthday":"1987-04-20","address":"湖北省孝感市汉川市"}
     */

    private int errNum;
    private String retMsg;
    private RetDataBean retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public RetDataBean getRetData() {
        return retData;
    }

    public void setRetData(RetDataBean retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        /**
         * sex : M
         * birthday : 1987-04-20
         * address : 湖北省孝感市汉川市
         */

        private String sex;
        private String birthday;
        private String address;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
