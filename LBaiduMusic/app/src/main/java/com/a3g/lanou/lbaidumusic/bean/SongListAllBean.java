package com.a3g.lanou.lbaidumusic.bean;

import java.util.List;

/**
 * Created by liuHao on 17/2/14.
 */
public class SongListAllBean {


    /**
     * error_code : 22000
     * result : {"tags":[{"second":["华语","欧美","粤语","日语","韩语","纯音乐","小语种"],"first":"语种"},{"second":["流行","摇滚","民谣","电子","影视原声","ACG","轻音乐","新世纪","爵士","古典","乡村","说唱","世界音乐","古风","儿歌","朋克","布鲁斯","RnB/Soul","金属","雷鬼","英伦","民族","后摇","拉丁"],"first":"风格"},{"second":["快乐","美好","安静","伤感","寂寞","思念","孤独","怀旧","悲伤","感动","治愈","放松","清新","浪漫","兴奋","性感","励志"],"first":"情感"},{"second":["运动","驾驶","学习","工作","清晨","夜晚","午后","游戏","旅行","散步","酒吧","夜店","咖啡厅","地铁","校园","婚礼","约会","休息"],"first":"场景"},{"second":["经典","翻唱","榜单","现场","KTV","DJ","网络歌曲","器乐"],"first":"主题"}],"hot":["华语","欧美","流行","摇滚","纯音乐","民谣","影视原声","运动","经典","安静"]}
     */

    private int error_code;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<TagsBean> tags;
        private List<String> hot;

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<String> getHot() {
            return hot;
        }

        public void setHot(List<String> hot) {
            this.hot = hot;
        }

        public static class TagsBean {
            /**
             * second : ["华语","欧美","粤语","日语","韩语","纯音乐","小语种"]
             * first : 语种
             */

            private String first;
            private List<String> second;

            public String getFirst() {
                return first;
            }

            public void setFirst(String first) {
                this.first = first;
            }

            public List<String> getSecond() {
                return second;
            }

            public void setSecond(List<String> second) {
                this.second = second;
            }
        }
    }
}
