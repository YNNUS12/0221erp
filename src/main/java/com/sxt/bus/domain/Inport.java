package com.sxt.bus.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Inport {
    private Integer id;
    private String paytype;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inporttime;
    private String operateperson;
    private Integer number;
    private String remark;
    private Double inportprice;
    private Integer providerid;
    private Integer goodsid;
    //添加属性
    private String providername;
    private String goodsname;
    private String size;
    
    public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Date getInporttime() {
        return inporttime;
    }

    public void setInporttime(Date inporttime) {
        this.inporttime = inporttime;
    }

    public String getOperateperson() {
        return operateperson;
    }

    public void setOperateperson(String operateperson) {
        this.operateperson = operateperson == null ? null : operateperson.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getInportprice() {
        return inportprice;
    }

    public void setInportprice(Double inportprice) {
        this.inportprice = inportprice;
    }

    public Integer getProviderid() {
        return providerid;
    }

    public void setProviderid(Integer providerid) {
        this.providerid = providerid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
}