package com.example.myapp.Model;

import Utils.DBUtils.StateBean;
import annos.Column;
import annos.LGDate;
import annos.Table;
import annos.UUID;

import java.math.BigDecimal;

/**
 * Created by wangss on 2015/3/17.
 * email:genhaoai@gmail.com
 */
@Table(name = "Product", keyField = "proId")
public class Product extends StateBean {
    @UUID(isUUID = true)
    @Column(columnName = "PRO_ID")
    private String proId;//商品编号
    @Column(columnName = "PRO_NAME")
    private String proName;//商品名称
    @Column(columnName = "SELLER_ID")
    private String sellerId;//店家
    @Column(columnName = "PRO_AMOUNT")
    private BigDecimal proAmount;//价格
    @Column(columnName = "VALIDATE")
    @LGDate
    private String validate;//有效期

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getProAmount() {
        return proAmount;
    }

    public void setProAmount(BigDecimal proAmount) {
        this.proAmount = proAmount;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    @Override
    public String toString(){
        return proName;
    }
}