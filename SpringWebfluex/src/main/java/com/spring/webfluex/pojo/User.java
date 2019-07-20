package com.spring.webfluex.pojo;

import com.spring.webfluex.enumeration.SexEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;

@Document
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    //性别
    private SexEnum sex;

    @Field("user_name")
    private String userName;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", sex=" + sex + ", userName='" + userName + '\'' + ", note='" + note + '\''
                + '}';
    }
}
