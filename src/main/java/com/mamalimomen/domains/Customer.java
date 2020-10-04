package com.mamalimomen.domains;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.HashSet;
import java.util.Set;

@SelectBeforeUpdate
@Entity
public class Customer extends User {

    private Boolean active;

    @OneToMany(mappedBy = "ownerCustomer")
    @OrderBy(value = "title desc")
    private Set<Account> activeAccounts = new HashSet<>();

    /*@ManyToMany(cascade = CascadeType.ALL) //unidirectional many to many relationship
    @OrderBy(value = "title desc")
    @JoinTable(name = "tbl_company_book"
            , joinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
    private List<Book> books = new ArrayList<Book>();
*/
}
