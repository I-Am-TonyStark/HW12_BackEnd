package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@SelectBeforeUpdate
@Table(name = "tbl_post", catalog = "HW12_One", schema = "HW12_One")
@NamedQueries({
        @NamedQuery(
                name = "Post.findAll",
                query = "SELECT p FROM Post p"),
        @NamedQuery(
                name = "Post.findOneByTitle",
                query = "SELECT p FROM Post p WHERE p.title = ?1")
})
public class Post extends BaseEntity<Long> implements Comparable<Post> {

    @Transient
    private static final long serialVersionUID = 4938129232368394591L;

    @Transient
    private static long count = 0;

    @Column(nullable = false, unique = true, updatable = false)
    private String title;

    public Post() {
        this.setId(count);
        count++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InValidDataException {
        if (!title.matches("\\w{3,}")) {
            throw new InValidDataException("Post title");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s%n", getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Post p = (Post) obj;
        return this.hashCode() == p.hashCode();
    }

    @Override
    public int compareTo(Post p) {
        return this.getTitle().compareTo(p.getTitle());
    }
}
