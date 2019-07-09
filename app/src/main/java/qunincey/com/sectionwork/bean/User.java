package qunincey.com.sectionwork.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
    private BmobFile headImag;
    private boolean sex;
    private String nickName;
    private String info;

    public BmobFile getHeadImag() {
        return headImag;
    }

    public User setHeadImag(BmobFile headImag) {
        this.headImag = headImag;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public User setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public User setInfo(String info) {
        this.info = info;
        return this;
    }
}
