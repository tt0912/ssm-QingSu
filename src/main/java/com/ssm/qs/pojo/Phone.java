package com.ssm.qs.pojo;

/**
 *  Author 田宇
 *  Date   2018/1/20 0020 14:34
 *  Description 存验证码
 */
public class Phone {

    private Integer id;
    private String phone;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
