package com.mamalimomen.base.domains;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<PK extends Number> implements Serializable {

    @Transient
    private static final long serialVersionUID = -128138883706363343L;

    @Id
    @Column(name = "identity", updatable = false, nullable = false, unique = true)
    private PK id;

    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted = false;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
