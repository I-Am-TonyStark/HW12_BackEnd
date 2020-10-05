package com.mamalimomen.base.services.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.base.services.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseServiceImpl<E extends BaseEntity<PK>, PK extends Number, Rep extends BaseRepository<E, PK>> implements BaseService<E, PK> {
    protected final Rep baseRepository;

    public BaseServiceImpl(Rep serviceRepository) {
        this.baseRepository = serviceRepository;
    }


    @Override
    public void closeEntityManger() {
        baseRepository.closeEntityManger();
    }

    @Override
    public boolean saveOne(E entity) {
        return baseRepository.saveOne(entity);
    }

    @Override
    public boolean saveMany(List<E> list) {
        return baseRepository.saveMany(list);
    }

    @Override
    public boolean updateOne(E entity) {
        return baseRepository.updateOne(entity);
    }

    @Override
    public boolean updateMany(List<E> list) {
        return baseRepository.updateMany(list);
    }

    @Override
    public Optional<E> findOne(E entity) {
        return baseRepository.findOne((Class<E>) entity.getClass(), entity.getId());
    }

    @Override
    public <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findOneByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter1, T parameter2, T parameter3, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery, parameter1, parameter2, parameter3, c);
    }

    @Override
    public <T> List<E> findManyByNamedQuery(Predicate<E> p, String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery,parameter,c)
                .stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public <R, T> List<R> findManyByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery, parameter, c)
                .stream()
                .map(f)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(namedQuery, c);
    }

    @Override
    public List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(namedQuery, c)
                .stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(namedQuery, c)
                .stream()
                .map(f)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteOne(E entity) {
        return baseRepository.deleteOne((Class<E>) entity.getClass(), entity.getId());
    }

    @Override
    public boolean deleteMany(List<E> list) {
        return baseRepository.deleteMany(list, (Class<E>) list.get(0).getClass());
    }
}
