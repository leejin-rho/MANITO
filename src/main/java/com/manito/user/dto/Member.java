package com.manito.user.dto;

public class Member {
    private int uid;
    private String username;
    private String manito_name;
    private String login_id;
    private String password;

    public Member() {
    }

    public Member(int uid, String username, String manito_name, String login_id, String password) {
        this.uid = uid;
        this.username = username;
        this.manito_name = manito_name;
        this.login_id = login_id;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", manito_name='" + manito_name + '\'' +
                ", login_id='" + login_id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getManito_name() {
        return manito_name;
    }

    public void setManito_name(String manito_name) {
        this.manito_name = manito_name;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
