package com.mamalimomen.base.repositories;

import com.mamalimomen.base.domains.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BaseRepository<E extends BaseEntity<PK>, PK extends Number> {

    void closeEntityManger();

    boolean saveOne(E entity);

    boolean saveMany(List<E> list);

    boolean updateOne(E entity);

    boolean updateMany(List<E> list);

    Optional<E> findOne(E entity);

    <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c);

    <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c);

    <R, T> List<R> findManyByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c);

    List<E> findAllByNamedQuery(String namedQuery, Class<E> c);

    List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c);

    <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c);

    boolean deleteOne(E entity);

    boolean deleteMany(List<E> list);
}
