package day02.l.example.com.everywheretrip.trip.bean;

import java.util.List;

public class ParticularsBean {


    /**
     * code : 0
     * desc :
     * result : {"carousel":["http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170804205_5c973a19b0a43c0a19817d73018ec007.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170808348_80aff837ae08f6fab3860890d8a8a270.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170812338_b8a90c114c5445b44b446ff6077801f6.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170817438_a89283485b427e2ac5a2c2f5df94a108.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170820498_6d2f8e9028483981b82301edc77c94ef.jpg"],"route":{"id":80,"banmiID":9,"cityID":15,"priceInCents":190,"title":"轻井泽","intro":"6小时《四重奏》浪漫行","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg","videoURL":"","sequence":1638,"description":"所谓旧轻井泽，其实就是轻井泽北部的老城区。因为海拔较高，且四周环山，所以每到夏季，这里的气候凉爽宜人，从19世纪末开始这里也成为日本最有代表性的避暑胜地，同时也是日本最有名的豪华别墅区和上流社会聚居地。传说天皇在这里邂逅了一生的伴侣，约翰·列侬与小野洋子每年也会来这里度假，热播日剧《四重奏》也在此取景，这里清新隽永的林间风光更是让如今的很多日本名流选择在这里的教堂举办婚礼，还有白桦林中的美术馆，湖岸边的音乐厅，更是给这里平添了独特的艺术气息。\n\n如果你也厌倦了海岛沙滩的度假方式，不如来轻井泽，租一辆单车，一头钻进这里的森林氧吧，静享一段骑行小路上的浪漫时光吧。\n","shareTitle":"东京周边旧轻井泽浪漫之旅","shareContent":"在小森林中的天然氧吧洗肺","purchasedTimes":1472,"price":"1.9","isPurchased":true,"isCollected":false,"city":"日本·东京周边","shareURL":"http://banmi.com/app2017/route3.html?id=80&referer=7835","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg"},"banmi":{"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png"},"reviews":[{"reviewID":2531,"userName":"Lucas-Lou","userPhoto":"http://wx.qlogo.cn/mmopen/P9lv9R9FTiaOKIqkf01ib5ibcCoWGp26IBjPJ9gydInODicGF1E9YSIs5J9qGeJ4XSQSzIkeyMxzAicEIlXnMAkzRsOkuDbiaO7Vm6/0","content":"轻井泽的奥特莱斯简直是女人的天堂，在这里比在银座买的更爽，而且价格便宜很多","createdAt":"8月前","images":[]},{"reviewID":2455,"userName":"Kaykay","userPhoto":"http://img.banmi.com/photos/1478071042951_7bb3a233dba53ea049e8c632c466ec5e","content":"轻井泽的奥特莱斯真的太适合买买买了 人不多 东西全价格也不贵 逛完了娱乐吃饭也不耽误","createdAt":"8月前","images":[]},{"reviewID":2095,"userName":"Wenting","userPhoto":"http://media.banmi.com/photos/1447185030512_9ebc79523c105e8d2d21b90cc03c8a8b","content":"这么文艺小清新的地方太适合度假了","createdAt":"11月前","images":[]}],"reviewsCount":41}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * carousel : ["http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170804205_5c973a19b0a43c0a19817d73018ec007.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170808348_80aff837ae08f6fab3860890d8a8a270.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170812338_b8a90c114c5445b44b446ff6077801f6.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170817438_a89283485b427e2ac5a2c2f5df94a108.jpg","http://cdn.banmi.com/banmiapp/rahdna/1516170820498_6d2f8e9028483981b82301edc77c94ef.jpg"]
         * route : {"id":80,"banmiID":9,"cityID":15,"priceInCents":190,"title":"轻井泽","intro":"6小时《四重奏》浪漫行","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg","videoURL":"","sequence":1638,"description":"所谓旧轻井泽，其实就是轻井泽北部的老城区。因为海拔较高，且四周环山，所以每到夏季，这里的气候凉爽宜人，从19世纪末开始这里也成为日本最有代表性的避暑胜地，同时也是日本最有名的豪华别墅区和上流社会聚居地。传说天皇在这里邂逅了一生的伴侣，约翰·列侬与小野洋子每年也会来这里度假，热播日剧《四重奏》也在此取景，这里清新隽永的林间风光更是让如今的很多日本名流选择在这里的教堂举办婚礼，还有白桦林中的美术馆，湖岸边的音乐厅，更是给这里平添了独特的艺术气息。\n\n如果你也厌倦了海岛沙滩的度假方式，不如来轻井泽，租一辆单车，一头钻进这里的森林氧吧，静享一段骑行小路上的浪漫时光吧。\n","shareTitle":"东京周边旧轻井泽浪漫之旅","shareContent":"在小森林中的天然氧吧洗肺","purchasedTimes":1472,"price":"1.9","isPurchased":true,"isCollected":false,"city":"日本·东京周边","shareURL":"http://banmi.com/app2017/route3.html?id=80&referer=7835","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg"}
         * banmi : {"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png"}
         * reviews : [{"reviewID":2531,"userName":"Lucas-Lou","userPhoto":"http://wx.qlogo.cn/mmopen/P9lv9R9FTiaOKIqkf01ib5ibcCoWGp26IBjPJ9gydInODicGF1E9YSIs5J9qGeJ4XSQSzIkeyMxzAicEIlXnMAkzRsOkuDbiaO7Vm6/0","content":"轻井泽的奥特莱斯简直是女人的天堂，在这里比在银座买的更爽，而且价格便宜很多","createdAt":"8月前","images":[]},{"reviewID":2455,"userName":"Kaykay","userPhoto":"http://img.banmi.com/photos/1478071042951_7bb3a233dba53ea049e8c632c466ec5e","content":"轻井泽的奥特莱斯真的太适合买买买了 人不多 东西全价格也不贵 逛完了娱乐吃饭也不耽误","createdAt":"8月前","images":[]},{"reviewID":2095,"userName":"Wenting","userPhoto":"http://media.banmi.com/photos/1447185030512_9ebc79523c105e8d2d21b90cc03c8a8b","content":"这么文艺小清新的地方太适合度假了","createdAt":"11月前","images":[]}]
         * reviewsCount : 41
         */

        private RouteBean route;
        private BanmiBean banmi;
        private int reviewsCount;
        private List<String> carousel;
        private List<ReviewsBean> reviews;

        public RouteBean getRoute() {
            return route;
        }

        public void setRoute(RouteBean route) {
            this.route = route;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
        }

        public int getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(int reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public List<String> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<String> carousel) {
            this.carousel = carousel;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class RouteBean {
            /**
             * id : 80
             * banmiID : 9
             * cityID : 15
             * priceInCents : 190
             * title : 轻井泽
             * intro : 6小时《四重奏》浪漫行
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg
             * videoURL :
             * sequence : 1638
             * description : 所谓旧轻井泽，其实就是轻井泽北部的老城区。因为海拔较高，且四周环山，所以每到夏季，这里的气候凉爽宜人，从19世纪末开始这里也成为日本最有代表性的避暑胜地，同时也是日本最有名的豪华别墅区和上流社会聚居地。传说天皇在这里邂逅了一生的伴侣，约翰·列侬与小野洋子每年也会来这里度假，热播日剧《四重奏》也在此取景，这里清新隽永的林间风光更是让如今的很多日本名流选择在这里的教堂举办婚礼，还有白桦林中的美术馆，湖岸边的音乐厅，更是给这里平添了独特的艺术气息。

             如果你也厌倦了海岛沙滩的度假方式，不如来轻井泽，租一辆单车，一头钻进这里的森林氧吧，静享一段骑行小路上的浪漫时光吧。

             * shareTitle : 东京周边旧轻井泽浪漫之旅
             * shareContent : 在小森林中的天然氧吧洗肺
             * purchasedTimes : 1472
             * price : 1.9
             * isPurchased : true
             * isCollected : false
             * city : 日本·东京周边
             * shareURL : http://banmi.com/app2017/route3.html?id=80&referer=7835
             * shareImageWechat : http://cdn.banmi.com/banmiapp/rahdna/1510564203339_47a7812e79526ef60f1cc9cbea6f9aea.jpg
             */

            private int id;
            private int banmiID;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private String description;
            private String shareTitle;
            private String shareContent;
            private int purchasedTimes;
            private String price;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String shareURL;
            private String shareImageWechat;

            public boolean getisCollected() {
                return isCollected;
            }

            public void setCollected(boolean collected) {
                isCollected = collected;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBanmiID() {
                return banmiID;
            }

            public void setBanmiID(int banmiID) {
                this.banmiID = banmiID;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public int getPurchasedTimes() {
                return purchasedTimes;
            }

            public void setPurchasedTimes(int purchasedTimes) {
                this.purchasedTimes = purchasedTimes;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getShareImageWechat() {
                return shareImageWechat;
            }

            public void setShareImageWechat(String shareImageWechat) {
                this.shareImageWechat = shareImageWechat;
            }
        }

        public static class BanmiBean {
            /**
             * id : 9
             * name : Isa
             * location : 长野县轻井泽
             * occupation : 互联网公司CEO 世界旅行家
             * introduction : 大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。
             * photo4 : http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private String photo4;
            private String photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getPhoto4() {
                return photo4;
            }

            public void setPhoto4(String photo4) {
                this.photo4 = photo4;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ReviewsBean {
            /**
             * reviewID : 2531
             * userName : Lucas-Lou
             * userPhoto : http://wx.qlogo.cn/mmopen/P9lv9R9FTiaOKIqkf01ib5ibcCoWGp26IBjPJ9gydInODicGF1E9YSIs5J9qGeJ4XSQSzIkeyMxzAicEIlXnMAkzRsOkuDbiaO7Vm6/0
             * content : 轻井泽的奥特莱斯简直是女人的天堂，在这里比在银座买的更爽，而且价格便宜很多
             * createdAt : 8月前
             * images : []
             */

            private int reviewID;
            private String userName;
            private String userPhoto;
            private String content;
            private String createdAt;
            private List<?> images;

            public int getReviewID() {
                return reviewID;
            }

            public void setReviewID(int reviewID) {
                this.reviewID = reviewID;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
