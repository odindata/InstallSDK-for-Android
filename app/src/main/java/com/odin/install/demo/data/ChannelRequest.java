package com.odin.install.demo.data;

public class ChannelRequest {

    /**
     * page : {"index":1,"size":10}
     * param : {"odinKey":"985459861c2c4e7b8f4f2c7245e56448","timeInterval":"custom","startTime":"2020-06-02 00:00:00","endTime":"2020-06-02 23:59:59"}
     */

    private PageBean page;
    private ParamBean param;

    public ChannelRequest(PageBean page, ParamBean param) {
        this.page = page;
        this.param = param;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class PageBean {
        /**
         * index : 1
         * size : 10
         */

        private int index;
        private int size;

        public PageBean(int index, int size) {
            this.index = index;
            this.size = size;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class ParamBean {
        /**
         * odinKey : 985459861c2c4e7b8f4f2c7245e56448
         * timeInterval : custom
         * startTime : 2020-06-02 00:00:00
         * endTime : 2020-06-02 23:59:59
         */

        private String odinKey;
        private String timeInterval;
        private String startTime;
        private String endTime;

        public ParamBean(String odinKey, String timeInterval, String startTime, String endTime) {
            this.odinKey = odinKey;
            this.timeInterval = timeInterval;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getOdinKey() {
            return odinKey;
        }

        public void setOdinKey(String odinKey) {
            this.odinKey = odinKey;
        }

        public String getTimeInterval() {
            return timeInterval;
        }

        public void setTimeInterval(String timeInterval) {
            this.timeInterval = timeInterval;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
