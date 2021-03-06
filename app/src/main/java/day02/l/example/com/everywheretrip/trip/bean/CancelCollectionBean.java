package day02.l.example.com.everywheretrip.trip.bean;

public class CancelCollectionBean {

    /**
     * code : 0
     * desc : 用户已收藏线路
     * result : {}
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
    }

    @Override
    public String toString() {
        return "CancelCollectionBean{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", result=" + result +
                '}';
    }
}
