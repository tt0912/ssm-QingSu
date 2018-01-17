package com.ssm.qs.pojo;
/**
 *  Author 田宇
 *  Date   2018/1/12 0012 15:03
 *  Description 记录登陆票据
 */
public class Info {

    private Integer id;
    private String ticket;//票据
    private Integer uid;//票据对应的用户
    private Integer cid;//用户对应的优惠券
    private Integer hid;//为哪个房源找优惠券

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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", uid=" + uid +
                ", cid=" + cid +
                ", hid=" + hid +
                '}';
    }
}
