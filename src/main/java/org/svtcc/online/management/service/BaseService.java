package org.svtcc.online.management.service;

import org.svtcc.online.management.dao.util.PageSupport;

/**
 * Created  on 1/11/15.
 */
public interface BaseService<T> {
    void saveOrUpdate(T t);

    void deleteById(Integer id);

    PageSupport<T> findPagination(int pageNo, int pageSize);
}
