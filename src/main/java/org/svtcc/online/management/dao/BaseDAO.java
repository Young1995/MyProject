/**
 *
 */
package org.svtcc.online.management.dao;

import org.svtcc.online.management.dao.util.PageSupport;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * A generic interface .
 */
public interface BaseDAO<T> {

    /**
     * Get the domain object T by its id.
     *
     * @param id domain identity
     * @return T
     */
    T find(Serializable id);

    /**
     * Get the domain list.
     *
     * @return List
     */
    List<T> findAll();

    /**
     * 通过例子实体对象的约束来查找对象，一般用于条件查询的时比较有用.
     * <p/>
     * <pre>
     * UserVo user = new UserVo();
     * user.setName(&quot;longbow&quot;);
     * List&lt;UserVo&gt; list = dao.findByExample(user);
     * </pre>
     *
     * @param exampleEntity query object
     * @return {@link java.util.List}
     */
    List<T> findByExample(T exampleEntity);

    /**
     * 保存实体对象.
     *
     * @param entity the entity to insert
     * @return T
     */
    T insert(T entity);

    /**
     * 更新实体对象.
     *
     * @param entity the entity to update
     * @return T
     */
    T update(T entity);

    /**
     * 删除实体对象.
     *
     * @param entity entity to delete.
     */
    void delete(T entity);

    /**
     * 删除实体对象.
     *
     * @param id entity id
     */
    void deleteById(Serializable id);

    /**
     * 删除实体对象.
     *
     * @param ids entity ids
     */
    void deleteByIds(Serializable[] ids);

    /**
     * 删除实体对象.
     *
     * @param ids   entity ids
     * @param clazz entity classs
     */
    void deleteByIds(Class<T> clazz, Serializable[] ids);

    /**
     * 得到实体的PK名称.
     *
     * @return String
     */
    String getIdName();

    /**
     * 通过Example进行分页查询.
     *
     * @param exampleEntity the example entity
     * @param pageNo        当前页
     * @param orders        排序字段的动态参数
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
//    PageSupport<T> findPagination(Object exampleEntity, int pageNo, SqlOrder... orders);

    /**
     * 分页显示.
     *
     * @param pageNo      当前页
     * @param entityClass the entity class
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
    PageSupport<T> findPagination(Class<T> entityClass, int pageNo);

    /**
     * 分页显示.
     *
     * @param pageNo 当前页
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
    PageSupport<T> findPagination(int pageNo);


    /**
     * 分页显示.
     *
     * @param pageNo 当前页
     * @param values hql语句的placeholder参数
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
    PageSupport<T> findPagination(int pageNo, int pageSize, Object... values);

    List<T> findList(int pageNo, int pageSize);

    /**
     * 分页查询.
     *
     * @param hql    查询的hibernate hql语句
     * @param pageNo 当前页
     * @param values hql语句的placeholder参数
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
//    PageSupport<T> findPagination(String hql, int pageNo, Object... values);

    /**
     * 分页查询.
     *
     * @param hql      查询的hibernate hql语句
     * @param pageNo   当前页
     * @param values   hql语句的placeholder参数
     * @param pageSize the pageSize
     * @return {@link PageSupport}
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
    PageSupport<T> findPagination(String hql, int pageNo, int pageSize, Object... values);

    /**
     * Inquire a list of assigned objects.
     *
     * @param clazz assigned Class
     * @return T
     * @throws org.svtcc.online.management.dao.exception.DaoException
     */
    List<T> findAll(Class<T> clazz);

    /**
     * find object by its Class and id.
     *
     * @param clazz the entity class
     * @param id    the entity id
     * @return T
     */
    T find(Class<T> clazz, Serializable id);

    /**
     * save a object.
     *
     * @param t object to save or update
     */
    T saveOrUpdate(T t);

    /**
     * 通过表的字段查找实体对象列表.
     *
     * @param name  key
     * @param value property value
     * @return {@link java.util.List} the result list
     */
    List<T> findByProperty(String name, Object value);

    /**
     * 检查查实体是否唯一.
     *
     * @param entity             entity
     * @param propertyNameValues 需要检查的属键值对
     * @return {@link Boolean}
     */
    boolean isNotUnique(T entity, Map<String, Object> propertyNameValues);
}
