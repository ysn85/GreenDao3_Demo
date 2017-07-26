package com.lee.shoppingcart.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/******************************************
 * 类描述： 购物车表数据对象 类名称：ShoppingCartData
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-7-26 15:14
 ******************************************/

@Entity
public class ShoppingCartData {
    @Id
    @NotNull
    private Long productId;
    @NotNull
    private String productName;
    @NotNull
    private Integer productNum;
    @NotNull
    private Date updateDate;
    @NotNull
    private String productPrice;

    @Generated(hash = 1360067192)
    public ShoppingCartData() {
    }

    @Generated(hash = 1858109154)
    public ShoppingCartData(@NotNull Long productId, @NotNull String productName, @NotNull Integer productNum,
            @NotNull Date updateDate, @NotNull String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productNum = productNum;
        this.updateDate = updateDate;
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
