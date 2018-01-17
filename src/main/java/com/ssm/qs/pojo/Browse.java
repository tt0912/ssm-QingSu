package com.ssm.qs.pojo;

import java.util.Date;

/**
 * 浏览记录
 */
public class Browse {

    private Integer id;
    private Integer houseId;
    private Integer userId;
    private Date createdDate;//浏览时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Browse{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                '}';
    }
}
