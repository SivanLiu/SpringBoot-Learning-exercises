package com.spring.webfluex.client;

public class UserPojo {
    private Long id;
    private String userName;
    // 1-男 2-女
    private int sexCode = 1;

    private String sexName;

    private String note = null;

    /**** setter and getter ****/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSexCode() {
        return sexCode;
    }

    public void setSexCode(int sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
