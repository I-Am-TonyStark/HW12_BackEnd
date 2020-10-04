package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction extends BaseEntity<Long> {
    private static final BigDecimal cardToCardCost = BigDecimal.valueOf(5_000.00);

    private String originCardNumber;

    private String destinationCardNumber;

    private BigDecimal moneyAmount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false,updatable = false)
    private Date date;

    public static BigDecimal getCardToCardCost() {
        return cardToCardCost;
    }

    public String getOriginCardNumber() {
        return originCardNumber;
    }

    public void setOriginCardNumber(String originCardNumber) {
        this.originCardNumber = originCardNumber;
    }

    public String getDestinationCardNumber() {
        return destinationCardNumber;
    }

    public void setDestinationCardNumber(String destinationCardNumber) {
        this.destinationCardNumber = destinationCardNumber;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //TODO repository, service & crud services
    //سرویس دیدن تراکنش های ثبت شده در سیستم برای هر اکانت
    //سرویس مشاهده تراکنش های هر اکانت بعد از تاریخ مشخص شده تا تاریخ جاری
   //با گرفتن شناسه اکانت و تاریخ، تراکنش هارا بر اساس این دو فیلد و تاریخ جاری فیلر کرده و لیستی از رکوردها را بر میگرداند
}
