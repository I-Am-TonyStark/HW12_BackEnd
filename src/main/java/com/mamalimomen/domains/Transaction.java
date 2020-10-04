package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_transaction", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Transaction.findAll",
                query = "SELECT t FROM Transaction t"),
        @NamedQuery(
                name = "Transaction.findAllSucceed",
                query = "SELECT t FROM Transaction t WHERE t.succeed = TRUE"),
        @NamedQuery(
                name = "Transaction.findManyByDate",
                query = "SELECT t FROM Transaction t WHERE t.date BETWEEN ?1 AND ?2"),
        @NamedQuery(
                name = "Transaction.findAllByAccountNumber",
                query = "SELECT t FROM Transaction t JOIN t.account a WHERE a.accountNumber = ?1")
})
public class Transaction extends BaseEntity<Long> implements Comparable<Transaction> {

    @Transient
    private static final long serialVersionUID = 6120959356340565984L;

    @Transient
    private static long count = 0;

    @Column(name = "is_succeed", nullable = false, updatable = false)
    private Boolean succeed;

    @Column(name = "origin_card", nullable = false, updatable = false)
    private String originCardNumber;

    @Column(name = "destination_card", updatable = false)
    private String destinationCardNumber;

    @Column(name = "money_amount", updatable = false, columnDefinition = "DECIMAL", precision = 2, scale = 2)
    private BigDecimal moneyAmount;

    @Column(name = "transaction_cost", nullable = false, updatable = false, columnDefinition = "DECIMAL", precision = 2, scale = 2)
    private BigDecimal tCost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_account", nullable = false, updatable = false)
    private Account account;

    @Column(nullable = false, updatable = false)
    private Date date;

    public Transaction() {
        this.setId(count);
        count++;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
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

    public BigDecimal getTCost() {
        return tCost;
    }

    public void settCost(BigDecimal tCost) {
        this.tCost = tCost;
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

    @Override
    public int compareTo(Transaction o) {
        return this.getDate().compareTo(o.getDate());
    }

    //TODO repository, service & crud services
    //سرویس دیدن تراکنش های ثبت شده در سیستم برای هر اکانت
    //سرویس مشاهده تراکنش های هر اکانت بعد از تاریخ مشخص شده تا تاریخ جاری
    //با گرفتن شناسه اکانت و تاریخ، تراکنش هارا بر اساس این دو فیلد و تاریخ جاری فیلر کرده و لیستی از رکوردها را بر میگرداند
}
