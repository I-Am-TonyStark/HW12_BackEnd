package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.repositories.AccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {
    public AccountRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Account> advancedSearch(AccountSearchDTO asDTO) {

        //To begin, let's set up our query:
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Article> query = cb.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        //Now, we'll need to build a Predicate to find items having a blue or a red color:
        List<Predicate> prList = new ArrayList<>();
        setAttributeForAdvancedSearch(asDTO, prList, cb, root);

        //Finally, we define the AND Predicate of these two, apply the where() method, and execute our query:
        query.where(cb.and(prList.toArray(new Predicate[0])));
        return em.createQuery(query).getResultList();
    }

    private void setAttributeForAdvancedSearch(ArticleSearchDTO asDTO, List<Predicate> prList, CriteriaBuilder cb, Root<Article> root) {
        setTitle(asDTO.getTitle(), prList, cb, root);
        //setWriterFirstName();
        setCategory(asDTO.getCategoryID(), prList, cb, root);
    }

    private void setCategory(Long categoryID, List<Predicate> prList, CriteriaBuilder cb, Root<Article> root) {
        if (categoryID != null) {
            prList.add(
                    //a where clause
                    cb.isMember(new Category(categoryID), root.get("categories"))
            );
        }
    }

    private void setTitle(String title, List<Predicate> prList, CriteriaBuilder cb, Root<Article> root) {
        if (title != null && !title.isEmpty()) {
            prList.add(
                    //a where clause
                    cb.like(root.get("title"), "%" + title.trim() + "%") //root.get(Article class String title fieldName)
            );
        }
    }

    private void setWriterFirstName(String writerFirstName, List<Predicate> prList, CriteriaBuilder cb, Root<Article> root) {
        if (writerFirstName != null && !writerFirstName.isEmpty()) {
            prList.add(
                    //a where clause
                    cb.like(root.get("firstName"), "%" + writerFirstName.trim() + "%")
            );
        }
    }
}
