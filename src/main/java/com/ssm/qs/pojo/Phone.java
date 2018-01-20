package com.ssm.qs.pojo;

/**
 *  Author 田宇
 *  Date   2018/1/20 0020 14:34
 *  Description 存验证码
 */
public class Phone {

    private Integer id;
    private Integer userId;
    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", userId=" + userId +
                ", code=" + code +
                '}';
    }
}
