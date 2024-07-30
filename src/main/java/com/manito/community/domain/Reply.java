package com.manito.community.domain;

import java.util.Date;

public class Reply {
    private int rid;
    private int uid;
    private int pid;
    private String msg;
    private Date regdate;

    private String author = "익명";

    public Reply() {
    }

    public Reply(int rid, int uid, int pid, String msg, Date regdate, String author) {
        super();

        this.rid = rid;
        this.uid = uid;
        this.pid = pid;
        this.msg = msg;
        this.regdate = regdate;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", msg='" + msg + '\'' +
                ", regdate='" + regdate + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
