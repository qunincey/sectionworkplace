package qunincey.com.sectionwork.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ConstellationBean implements MultiItemEntity {

    /**
     * id : 1
     * name : 白羊座
     * head : /img/constellation/baiyang_head_icon.png
     * img : /img/constellation/baiyang_icon.png
     * icon : /img/constellation/baiyang.png
     * date : 3.21~4.19
     * info : 白羊座的人热情冲动、爱冒险、慷慨、天不怕地不怕而且一旦下定决心，不到黄河心不死，排除万难的要达到目的。
     * whole : 3
     * love : 3
     * career : 4
     * money : 4
     * whole_info : 今天对爱情的向往没那么强烈，即使是遇到喜欢的人表现都相比之前要冷静不少，不容易擦出爱火花。财运有好转，投资上会有收获，理财规划好，消费上能够精打细算，货比三家。
     * love_info : 身边出现不少爱慕者，有伴者感情生活稳定。
     * career_info : 做事效率高，但是有点急进，要注意细节之处。
     * money_info : 有得财机会，赚钱轻松，还会得偏财的运气。
     * health_info : 要多爱惜身体，不要忽略了健康。
     */

    private int id;
    private String name;
    private String head;
    private String img;
    private String icon;
    private String date;
    private String info;
    private int whole;
    private int love;
    private int career;
    private int money;
    private String whole_info;
    private String love_info;
    private String career_info;
    private String money_info;
    private String health_info;

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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getWhole() {
        return whole;
    }

    public void setWhole(int whole) {
        this.whole = whole;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWhole_info() {
        return whole_info;
    }

    public void setWhole_info(String whole_info) {
        this.whole_info = whole_info;
    }

    public String getLove_info() {
        return love_info;
    }

    public void setLove_info(String love_info) {
        this.love_info = love_info;
    }

    public String getCareer_info() {
        return career_info;
    }

    public void setCareer_info(String career_info) {
        this.career_info = career_info;
    }

    public String getMoney_info() {
        return money_info;
    }

    public void setMoney_info(String money_info) {
        this.money_info = money_info;
    }

    public String getHealth_info() {
        return health_info;
    }

    public void setHealth_info(String health_info) {
        this.health_info = health_info;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
