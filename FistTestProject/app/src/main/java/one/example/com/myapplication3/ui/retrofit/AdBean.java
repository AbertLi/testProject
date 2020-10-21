package one.example.com.myapplication3.ui.retrofit;

import java.util.List;

public class AdBean {

    /**
     * msg : DEAL_SUCCESS
     * status : true
     * data : {"numId":1,"appkey":"","unitID":"","mydatad":[{"id":2,"style":7,"vdPauseStatus":0,"vdUrl":"","appName":"正统三国","appPackageName":"com.jedigames.p16.mzyx","adDirection":1,"adVersion":"166216","isOneClose":0,"isSysapp":0,"adSource":0,"landscapeUrl":"https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg","portraitUrl":"https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg","skipEventUrl":"http://tfyxb2017-1251304591.file.myqcloud.com/rxsg/rxsg_mzyw_1263489001.apk","clickMode":1}]}
     */

    private String msg;
    private boolean status;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * numId : 1
         * appkey :
         * unitID :
         * mydatad : [{"id":2,"style":7,"vdPauseStatus":0,"vdUrl":"","appName":"正统三国","appPackageName":"com.jedigames.p16.mzyx","adDirection":1,"adVersion":"166216","isOneClose":0,"isSysapp":0,"adSource":0,"landscapeUrl":"https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg","portraitUrl":"https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg","skipEventUrl":"http://tfyxb2017-1251304591.file.myqcloud.com/rxsg/rxsg_mzyw_1263489001.apk","clickMode":1}]
         */

        private int numId;
        private String appkey;
        private String unitID;
        private List<MydatadBean> mydatad;

        public int getNumId() {
            return numId;
        }

        public void setNumId(int numId) {
            this.numId = numId;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getUnitID() {
            return unitID;
        }

        public void setUnitID(String unitID) {
            this.unitID = unitID;
        }

        public List<MydatadBean> getMydatad() {
            return mydatad;
        }

        public void setMydatad(List<MydatadBean> mydatad) {
            this.mydatad = mydatad;
        }

        public static class MydatadBean {
            /**
             * id : 2
             * style : 7
             * vdPauseStatus : 0
             * vdUrl :
             * appName : 正统三国
             * appPackageName : com.jedigames.p16.mzyx
             * adDirection : 1
             * adVersion : 166216
             * isOneClose : 0
             * isSysapp : 0
             * adSource : 0
             * landscapeUrl : https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg
             * portraitUrl : https://cdn.mf.51muzhi.com/1025960/SYKXX/xsAD/sanguoA.jpg
             * skipEventUrl : http://tfyxb2017-1251304591.file.myqcloud.com/rxsg/rxsg_mzyw_1263489001.apk
             * clickMode : 1
             */

            private int id;
            private int style;
            private int vdPauseStatus;
            private String vdUrl;
            private String appName;
            private String appPackageName;
            private int adDirection;
            private String adVersion;
            private int isOneClose;
            private int isSysapp;
            private int adSource;
            private String landscapeUrl;
            private String portraitUrl;
            private String skipEventUrl;
            private int clickMode;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStyle() {
                return style;
            }

            public void setStyle(int style) {
                this.style = style;
            }

            public int getVdPauseStatus() {
                return vdPauseStatus;
            }

            public void setVdPauseStatus(int vdPauseStatus) {
                this.vdPauseStatus = vdPauseStatus;
            }

            public String getVdUrl() {
                return vdUrl;
            }

            public void setVdUrl(String vdUrl) {
                this.vdUrl = vdUrl;
            }

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }

            public String getAppPackageName() {
                return appPackageName;
            }

            public void setAppPackageName(String appPackageName) {
                this.appPackageName = appPackageName;
            }

            public int getAdDirection() {
                return adDirection;
            }

            public void setAdDirection(int adDirection) {
                this.adDirection = adDirection;
            }

            public String getAdVersion() {
                return adVersion;
            }

            public void setAdVersion(String adVersion) {
                this.adVersion = adVersion;
            }

            public int getIsOneClose() {
                return isOneClose;
            }

            public void setIsOneClose(int isOneClose) {
                this.isOneClose = isOneClose;
            }

            public int getIsSysapp() {
                return isSysapp;
            }

            public void setIsSysapp(int isSysapp) {
                this.isSysapp = isSysapp;
            }

            public int getAdSource() {
                return adSource;
            }

            public void setAdSource(int adSource) {
                this.adSource = adSource;
            }

            public String getLandscapeUrl() {
                return landscapeUrl;
            }

            public void setLandscapeUrl(String landscapeUrl) {
                this.landscapeUrl = landscapeUrl;
            }

            public String getPortraitUrl() {
                return portraitUrl;
            }

            public void setPortraitUrl(String portraitUrl) {
                this.portraitUrl = portraitUrl;
            }

            public String getSkipEventUrl() {
                return skipEventUrl;
            }

            public void setSkipEventUrl(String skipEventUrl) {
                this.skipEventUrl = skipEventUrl;
            }

            public int getClickMode() {
                return clickMode;
            }

            public void setClickMode(int clickMode) {
                this.clickMode = clickMode;
            }
        }
    }
}
