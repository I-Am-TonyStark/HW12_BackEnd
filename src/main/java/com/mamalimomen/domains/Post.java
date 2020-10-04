package com.mamalimomen.domains;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_post")
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "select r from Role r"),
        @NamedQuery(name = "Role.findOneByTitle", query = "select r from Role r where r.title =?1")
})
public class Post extends BaseEntity<Long> implements Comparable<Post> {

    @Transient
    private static final long serialVersionUID = -974627443731173113L;

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
        if (!title.matches("[a-zA-Z\\s.,&\\d\\(\\)]{3,}")) {
            throw new InValidDataException("Title");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s", getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Post post = (Post) obj;
        return this.hashCode() == post.hashCode();
    }

    @Override
    public int compareTo(Post p) {
        return this.getTitle().compareTo(p.getTitle());
    }
}
