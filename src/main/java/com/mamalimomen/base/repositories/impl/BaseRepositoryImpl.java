package com.mamalimomen.base.repositories.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<E extends BaseEntity> implements BaseRepository<E> {
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
        } catch (EntityExistsException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean saveMany(List<E> list) {
        //FIXME
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
        //FIXME
        return false;
    }

    @Override
    public Optional<E> findOne(E entity) {
        E found = (E) em.find(entity.getClass(), entity.getId());
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
    public <R, T> List<R> findManyByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c) {
        return findManyByNamedQuery(namedQuery, parameter, c)
                .stream()
                .map(f)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .getResultList();
    }

    @Override
    public List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c) {
        return findAllByNamedQuery(namedQuery, c)
                .stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c) {
        return findAllByNamedQuery(namedQuery, c)
                .stream()
                .map(f)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteOne(E entity) {
        try {
            Optional<E> oEntity = findOne(entity);
            if (oEntity.isPresent()) {
                em.getTransaction().begin();

                em.remove(oEntity.get());

                em.getTransaction().commit();

                return true;
            } else return false;
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean deleteMany(List<E> list) {
        //FIXME
        return false;
    }
}
