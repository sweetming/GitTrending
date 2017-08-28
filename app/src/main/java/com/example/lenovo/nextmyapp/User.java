package com.example.lenovo.nextmyapp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/5/16.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    @Property
    private String bt;
    private String js;
    private String wz;
    public String getJs() {
        return this.js;
    }
    public void setJs(String js) {
        this.js = js;
    }
    public String getBt() {
        return this.bt;
    }
    public void setBt(String bt) {
        this.bt = bt;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWz() {
        return this.wz;
    }
    public void setWz(String wz) {
        this.wz = wz;
    }
    @Generated(hash = 1697676093)
    public User(Long id, String bt, String js, String wz) {
        this.id = id;
        this.bt = bt;
        this.js = js;
        this.wz = wz;
    }
    @Generated(hash = 586692638)
    public User() {
    }

}
