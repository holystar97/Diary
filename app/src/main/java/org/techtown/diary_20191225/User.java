package org.techtown.diary_20191225;

/**
 * Created by mkrice on 2020-01-04.
 */

public class User {
    private String id;
    private String pw;
    private String name;
    private String birth;
    private String email;

    public User(){}

    public User(String id, String pw,String name, String birth, String email){
        this.id=id;
        this.pw=pw;
        this.name=name;
        this.birth=birth;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
