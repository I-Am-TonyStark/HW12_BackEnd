package com.mamalimomen.base.repositories.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<E extends BaseEntity<PK>, PK extends Number> implements BaseRepository<E, PK> {
    private final EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void closeEntityManger() {
        if (em.isOpen())
            em.close();
    }

    @Override
    public boolean saveOne(E entity) {
        try {
            em.getTransaction().begin();

            em.persist(entity);

            em.getTransaction().commit();

            return true;
        } catch (EntityExistsException | RollbackException e) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean saveMany(List<E> list) {
        try {
            em.getTransaction().begin();

            for (E entity : list)
                em.persist(entity);

            em.getTransaction().commit();

            return true;
        } catch (EntityExistsException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean updateOne(E entity) {
        try {
            em.getTransaction().begin();

            if (!em.contains(entity))
                em.merge(entity);

            em.getTransaction().commit();

            return true;
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean updateMany(List<E> list) {
        try {
            em.getTransaction().begin();

            for (E entity : list)
                if (!em.contains(entity))
                    em.merge(entity);

            em.getTransaction().commit();

            return true;
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public Optional<E> findOne(Class<E> c, PK id) {
        E found = em.find(c, id);
        return found != null ? Optional.of(found) : Optional.empty();
    }

    @Override
    public <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        E e;
        try {
            e = em.createNamedQuery(namedQuery, c)
                    .setParameter(1, parameter)
                    .getSingleResult();
        } catch (NoResultException exception) {
            e = null;
        }
        return e != null ? Optional.of(e) : Optional.empty();
    }


    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .setParameter(1, parameter)
                .getResultList();
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter1, T parameter2, T parameter3, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .setParameter(1, parameter1)
                .setParameter(2, parameter2)
                .getResultList();
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .getResultList();
    }

    @Override
    public boolean deleteOne(Class<E> c, PK id) {
        try {
            Optional<E> oEntity = findOne(c, id);
            if (oEntity.isPresent()) {
                em.getTransaction().begin();

                em.remove(oEntity.get());

                em.getTransaction().commit();

                return true;
            }
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean deleteMany(List<E> list, Class<E> c) {
        try {
            em.getTransaction().begin();
            for (E entity : list) {
                Optional<E> oEntity = findOne(c, entity.getId());
                oEntity.ifPresent(em::remove);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }
}
