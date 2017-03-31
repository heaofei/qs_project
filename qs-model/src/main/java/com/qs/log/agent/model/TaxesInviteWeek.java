package com.qs.log.agent.model;

import java.math.BigDecimal;
import java.util.Date;

public class TaxesInviteWeek {
    private Integer mid;

    private Date date;

    private BigDecimal paytotal;

    private BigDecimal rebatetotal;

    private Short bindpeople;

    private Byte scale;

    private BigDecimal taxrate;

    private Byte isaward;

    private Integer parentid;

    private String info;
    
    public TaxesInviteWeek() {
		super();
	}
    
    public TaxesInviteWeek(BigDecimal rebatetotal, Byte isaward) {
		super();
		this.rebatetotal = rebatetotal;
		this.isaward = isaward;
	}

	public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPaytotal() {
        return paytotal;
    }

    public void setPaytotal(BigDecimal paytotal) {
        this.paytotal = paytotal;
    }

    public BigDecimal getRebatetotal() {
        return rebatetotal;
    }

    public void setRebatetotal(BigDecimal rebatetotal) {
        this.rebatetotal = rebatetotal;
    }

    public Short getBindpeople() {
        return bindpeople;
    }

    public void setBindpeople(Short bindpeople) {
        this.bindpeople = bindpeople;
    }

    public Byte getScale() {
        return scale;
    }

    public void setScale(Byte scale) {
        this.scale = scale;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public Byte getIsaward() {
        return isaward;
    }

    public void setIsaward(Byte isaward) {
        this.isaward = isaward;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}