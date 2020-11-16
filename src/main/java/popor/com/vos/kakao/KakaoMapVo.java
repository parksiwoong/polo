package popor.com.vos.kakao;

public class KakaoMapVo {

    private int num;
    private String id;
    private String passwd;
    private String name;
    private String address;
    private String tel;
    private String info;
    private String lat;
    private String lang;

    public KakaoMapVo(int num, String id, String passwd, String name, String address, String tel, String info, String lat, String lang) {
        this.num = num;
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.info = info;
        this.lat = lat;
        this.lang = lang;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
