package com.odin.install.demo.data;

import java.util.List;

public class ChannelData {

    /**
     * data : [{"odinKey":"985459861c2c4e7b8f4f2c7245e56448","channelCode":"defaultCode","channelName":"默认渠道","channelUrl":"null?channelCode=defaultCode","visitsNum":0,"visitsNoRepeatNum":0,"clickNum":2,"clickNoRepeatNum":1,"installNum":0,"installNoRepeatNum":0,"registerNum":0,"registerNoRepeatNum":0,"callOnNum":3,"callOnNoRepeatNum":1}]
     * code : 0
     * msg : SUCCESS
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * odinKey : 985459861c2c4e7b8f4f2c7245e56448
         * channelCode : defaultCode
         * channelName : 默认渠道
         * channelUrl : null?channelCode=defaultCode
         * visitsNum : 0
         * visitsNoRepeatNum : 0
         * clickNum : 2
         * clickNoRepeatNum : 1
         * installNum : 0
         * installNoRepeatNum : 0
         * registerNum : 0
         * registerNoRepeatNum : 0
         * callOnNum : 3
         * callOnNoRepeatNum : 1
         */

        private String odinKey;
        private String channelCode;
        private String channelName;
        private String channelUrl;
        private int visitsNum;
        private int visitsNoRepeatNum;
        private int clickNum;
        private int clickNoRepeatNum;
        private int installNum;
        private int installNoRepeatNum;
        private int registerNum;
        private int registerNoRepeatNum;
        private int callOnNum;
        private int callOnNoRepeatNum;

        public String getOdinKey() {
            return odinKey;
        }

        public void setOdinKey(String odinKey) {
            this.odinKey = odinKey;
        }

        public String getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelUrl() {
            return channelUrl;
        }

        public void setChannelUrl(String channelUrl) {
            this.channelUrl = channelUrl;
        }

        public int getVisitsNum() {
            return visitsNum;
        }

        public void setVisitsNum(int visitsNum) {
            this.visitsNum = visitsNum;
        }

        public int getVisitsNoRepeatNum() {
            return visitsNoRepeatNum;
        }

        public void setVisitsNoRepeatNum(int visitsNoRepeatNum) {
            this.visitsNoRepeatNum = visitsNoRepeatNum;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public int getClickNoRepeatNum() {
            return clickNoRepeatNum;
        }

        public void setClickNoRepeatNum(int clickNoRepeatNum) {
            this.clickNoRepeatNum = clickNoRepeatNum;
        }

        public int getInstallNum() {
            return installNum;
        }

        public void setInstallNum(int installNum) {
            this.installNum = installNum;
        }

        public int getInstallNoRepeatNum() {
            return installNoRepeatNum;
        }

        public void setInstallNoRepeatNum(int installNoRepeatNum) {
            this.installNoRepeatNum = installNoRepeatNum;
        }

        public int getRegisterNum() {
            return registerNum;
        }

        public void setRegisterNum(int registerNum) {
            this.registerNum = registerNum;
        }

        public int getRegisterNoRepeatNum() {
            return registerNoRepeatNum;
        }

        public void setRegisterNoRepeatNum(int registerNoRepeatNum) {
            this.registerNoRepeatNum = registerNoRepeatNum;
        }

        public int getCallOnNum() {
            return callOnNum;
        }

        public void setCallOnNum(int callOnNum) {
            this.callOnNum = callOnNum;
        }

        public int getCallOnNoRepeatNum() {
            return callOnNoRepeatNum;
        }

        public void setCallOnNoRepeatNum(int callOnNoRepeatNum) {
            this.callOnNoRepeatNum = callOnNoRepeatNum;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "odinKey='" + odinKey + '\'' +
                    ", channelCode='" + channelCode + '\'' +
                    ", channelName='" + channelName + '\'' +
                    ", channelUrl='" + channelUrl + '\'' +
                    ", visitsNum=" + visitsNum +
                    ", visitsNoRepeatNum=" + visitsNoRepeatNum +
                    ", clickNum=" + clickNum +
                    ", clickNoRepeatNum=" + clickNoRepeatNum +
                    ", installNum=" + installNum +
                    ", installNoRepeatNum=" + installNoRepeatNum +
                    ", registerNum=" + registerNum +
                    ", registerNoRepeatNum=" + registerNoRepeatNum +
                    ", callOnNum=" + callOnNum +
                    ", callOnNoRepeatNum=" + callOnNoRepeatNum +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
