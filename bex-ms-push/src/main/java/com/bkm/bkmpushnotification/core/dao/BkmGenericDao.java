package com.bkm.bkmpushnotification.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 06.02.2018.
 */
public interface BkmGenericDao<T extends Serializable> {

    Optional<T> findById(final long id);

    Optional<List<T>> findAll();

    void insert(final T entity);

    int update(final T entity);

    int delete(final T entity);

    void deleteById(final long entityId);
}
