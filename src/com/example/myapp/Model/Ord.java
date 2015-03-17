package com.example.myapp.Model;

import Utils.DBUtils.StateBean;
import annos.Column;
import annos.Table;
import annos.UUID;

import java.math.BigDecimal;

/**
 * Created by wangss on 2015/3/17.
 * email:genhaoai@gmail.com
 */
@Table(name = "ord",keyField ="id" )
public class Ord extends StateBean {
    public Ord() {

    }

    public Ord(String _address, String _telephone_number, BigDecimal _order_amount) {
        this.address = _address;
        this.telephone_number = _telephone_number;
        this.order_amount = _order_amount;
    }


    @UUID(isUUID = false)
    @Column(columnName = "id")
    private String id;
    @Column(columnName = "address")
    private String address;
    @Column(columnName = "telephone")
    private String telephone_number;
    @Column(columnName = "order_amount")
    private BigDecimal order_amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public BigDecimal getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(BigDecimal order_amount) {
        this.order_amount = order_amount;
    }
}
