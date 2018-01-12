package com.ssm.qs.pojo;
/**
 *  Author 田宇
 *  Date   2018/1/12 0012 15:03
 *  Description 记录登陆票据
 */
public class Info {

    private Integer id;
    private String ticket;
    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", uid=" + uid +
                '}';
    }
}
