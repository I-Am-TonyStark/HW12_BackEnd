package com.mamalimomen.base.services.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.base.services.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseServiceImpl<E extends BaseEntity<PK>, PK extends Number, Rep extends BaseRepository<E, PK>> implements BaseService<E, PK> {
    private final Rep serviceRepository;

    public BaseServiceImpl(Rep serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public void closeEntityManger() {
        serviceRepository.closeEntityManger();
    }

    @Override
    public boolean saveOne(E entity) {
        return serviceRepository.saveOne(entity);
    }

    @Override
    public boolean saveMany(List<E> list) {
        return serviceRepository.saveMany(list);
    }

    @Override
    public boolean updateOne(E entity) {
        return serviceRepository.updateOne(entity);
    }

    @Override
    public boolean updateMany(List<E> list) {
        return serviceRepository.updateMany(list);
    }

    @Override
    public Optional<E> findOne(E entity) {
        return serviceRepository.findOne(entity);
    }

    @Override
    public <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return serviceRepository.findOneByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return serviceRepository.findManyByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public <R, T> List<R> findManyByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c) {
        return serviceRepository.findManyByNamedQuery(f, namedQuery, parameter, c);
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return serviceRepository.findAllByNamedQuery(namedQuery, c);
    }

    @Override
    public List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c) {
        return serviceRepository.findAllByNamedQuery(p, namedQuery, c);
    }

    @Override
    public <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c) {
        return serviceRepository.findAllByNamedQuery(f, namedQuery, c);
    }

    @Override
    public boolean deleteOne(E entity) {
        return serviceRepository.deleteOne(entity);
    }

    @Override
    public boolean deleteMany(List<E> list) {
        return serviceRepository.deleteMany(list);
    }
}
