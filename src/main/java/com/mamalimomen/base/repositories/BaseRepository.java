package com.mamalimomen.base.repositories;

import com.mamalimomen.base.domains.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity<PK>, PK extends Number> {

    void closeEntityManger();

    boolean saveOne(E entity);

    boolean saveMany(List<E> list);

    boolean updateOne(E entity);

    boolean updateMany(List<E> list);

    Optional<E> findOne(Class<E> c, PK id);

    <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c);

    <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c);

    <T> List<E> findManyByNamedQuery(String namedQuery, T parameter1, T parameter2, T parameter3, Class<E> c);

    List<E> findAllByNamedQuery(String namedQuery, Class<E> c);

    boolean deleteOne(Class<E> c, PK id);

    boolean deleteMany(List<E> list, Class<E> c);
}
