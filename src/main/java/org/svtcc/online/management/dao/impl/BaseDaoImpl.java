/**
 *
 */
package org.svtcc.online.management.dao.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.svtcc.online.management.dao.BaseDAO;
import org.svtcc.online.management.dao.exception.DaoException;
import org.svtcc.online.management.dao.util.PageSupport;
import org.svtcc.online.management.dao.util.ReflectUtils;
import org.svtcc.online.management.dao.util.SqlOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * {@inheritDoc}
 *
 * @param <T>
 * @see org.svtcc.online.management.dao.BaseDAO <T>
 */

public class BaseDaoImpl<T> implements BaseDAO<T> {
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Entity class for each Dao.
     */
    private Class<T> entityClass;

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        entityClass = (Class<T>) ReflectUtils.getSuperClassGenricType(this.getClass());
    }

    /**
     * Constructor that compatible with JDK1.4.
     *
     * @param entityClass Entity class for each Dao
     */
    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /******************** 保存/更新对象方法 *********************/
    /**
     * 保存实体
     * @param entity entity to insert
     * @return T
     */
    public T insert(T entity) {
        try {
            getCurrentSession().persist(entity);
            return entity;
        } catch (DataAccessException e) {
            logger.error("save entity error", e);
            throw new DaoException("save entity error", e);
        }
    }
    
    /**
     * 实体如果存在则更新，不存在则保存
     */
    public T saveOrUpdate(T entity) {
        try {
                getCurrentSession().saveOrUpdate(entity);
            return entity;
        } catch (Exception e) {
            logger.error("save or update entity error.");
            throw new DaoException("can't save object", e);
        }
    }
    
    /**
     * 更新实体
     */
    public T update(T entity) {
        try {
            getCurrentSession().merge(entity);
            return entity;
        } catch (DataAccessException e) {
            logger.error("save entity error", e);
            throw new DaoException("save entity error", e);
        }
    }
    
    /******************** 保存对象方法 *********************/
    
    /******************** 删除对象方法 *********************/
    /**
     * 删除实体
     */
    public void delete(T entity) {
        try {
            getCurrentSession().delete(entity);
        } catch (DataAccessException e) {
            logger.error("delete entity error", e);
            throw new DaoException("delete entity error", e);
        }
    }
    
    /**
     * 根据Id删除对象
     */
    public void deleteById(Serializable id) {
        delete(find(id));
    }
    
    /**
     * 根据id数组来删除
     */
    public void deleteByIds(Serializable[] ids) {
        deleteByIds(entityClass, ids);
    }
    
    /**
     * 根据ID数组来删除实体
     * @param clazz Class 实体类型
     * @param ids String[] id数组
     */
    public void deleteByIds(Class<T> clazz, Serializable[] ids) {
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ids.length - 1; i++) {
                sb.append(ids[i]);
                sb.append(",");
            }
            sb.append(ids[ids.length - 1]);
            StringBuffer sql = new StringBuffer();
            sql.append("delete ").append(clazz.getName()).append(" where ").append(getIdName()).append(" in (").append(
                    sb).append(")");
            if (logger.isInfoEnabled()) {
                logger.info(sql);
            }
            getQuery(sql.toString()).executeUpdate();
        } catch (HibernateException e) {
            logger.error("delete entity by ids error", e);
            throw new DaoException("delete entity by ids error", e);
        }
    }
    
    /******************** 删除对象方法 *********************/
    
    /******************** 查找对象方法 *********************/
    
    // 简单查询
    /**
     * 根据id以及实体类型来查寻
     * @param clazz Class 实体类型
     * @param id Serializable 实体id
     * @return T 查寻结果
     */
    @SuppressWarnings("unchecked")
    public T find(Class<T> clazz, Serializable id) {
        T o = (T) getCurrentSession().get(clazz, id);
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }
        return o;
    }
    
    /**
     * 根据id查寻实体
     * @param id Serializable 实体id
     * @return T 查寻结果
     */
    @SuppressWarnings("unchecked")
    public T find(Serializable id) {
        try {
            return (T) getCurrentSession().get(entityClass, id);
        } catch (DataAccessException e) {
            logger.error("get entity by id error", e);
            throw new DaoException("get entity by id error", e);
        }
    }
    
    // 根据条件查寻所有
    
    /**
     * 查寻所有
     * @return List<T> 查寻结果
     */
    public List<T> findAll() {
        return findByCriteria();
    }
    /**
     * 根据实体类型查寻所有
     * @param clazz Class 实体类型
     * @return List<T> 查寻结果
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(Class<T> clazz) {
        return getCurrentSession().createCriteria(clazz).list();
    }

    /**
     * 通过Criterion查找符合条件的对象
     *
     * @param criterion 可变条件列表,org.hibernate.criterion.Restrictions生成的条件，空表示所有
     * @return List<T> 查寻结果
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) {
        return getCriteria(criterion).list();
    }

    /**
     * 通过Criterion查找符合条件的对象.
     *
     * @param listSize  listSize
     * @param criterion 可变条件列表,org.hibernate.criterion.Restrictions生成的条件，空表示所有
     * @return Document Me
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int listSize, Criterion... criterion) {
        return getCriteria(criterion).setMaxResults(listSize).addOrder(Order.desc("id")).list();
    }

    /**
     * 通过Criterion查找符合条件的对像.
     *
     * @param t         the entity
     * @param criterion 可变条件列表,org.hibernate.criterion.Restrictions生成的条件，空表示所有
     * @return Document Me
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(T t, Criterion... criterion) {
        return getCriteria(t, criterion).list();
    }

    /**
     * find by example .
     *
     * @param exampleEntity exampleEntity
     * @return {@link java.util.List}
     */
    public List<T> findByExample(T exampleEntity) {
        return findByCriteria(exampleEntity, Example.create(exampleEntity).excludeZeroes().enableLike());
    }

    /**
     * 通过表的字段查找实体对象列表.
     *
     * @param name  key
     * @param value property value
     * @return {@link java.util.List} the result list
     */
    public List<T> findByProperty(String name, Object value) {
        return findByCriteria(Restrictions.eq(name, value));
    }

    /**
     * 通过Query查找符合条件的对象.
     *
     * @param hql    Query查询语句
     * @param values 查询语句的可变参数
     * @return Document Me
     */
    @SuppressWarnings("unchecked")
    public List<T> findByQuery(String hql, Object... values) {
        Query q = getCurrentSession().createQuery(hql);

        for (int i = 0; i < values.length; i++) {
            q.setParameter(i, values[i]);
        }

        return q.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> find(String query, Object... args) {
        Query q = getCurrentSession().createQuery(query);

        for (int i = 0; i < args.length; i++) {
            q.setParameter(i, args[i]);
        }

        return q.list();
    }

    // 根据条件执行分页查寻
    
    /**
     * 根据实体类型和页码查寻.
     *
     * @param pageNo the pageNo
     * @return {@link PageSupport}
     */
    public PageSupport<T> findPagination(Class<T> entityClass, int pageNo) {
        return pagedQuery(new StringBuffer("from ").append(entityClass.getName()).toString(), pageNo);
    }

    /**
     * 根据页码查寻.
     *
     * @param pageNo the pageNo
     * @return {@link PageSupport}
     */
    public PageSupport<T> findPagination(int pageNo) {
        String hql = "from " + entityClass.getName();

        return pagedQuery(hql, pageNo);
    }

    @Override
    public PageSupport<T> findPagination(int pageNo, int pageSize, Object... values) {
        String hql = "from " + entityClass.getName();
        return pagedQuery(hql, pageNo, pageSize, values);
    }

    @Override
    public List<T> findList(int pageNo, int pageSize) {
        String hql = "from " + entityClass.getName();
        return pagedQuery(hql, pageNo, pageSize);
    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

//    public PageSupport<T> findPagination(Object exampleEntity, int pageNo, SqlOrder... orders) {
//        return pagedCriteria(exampleEntity, pageNo, PageSupport.DEFAULT_PAGE_SIZE, orders);
//    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

    public PageSupport<T> findPagination(String hql, int pageNo, int pageSize, Object... values) {
        return pagedQuery(hql, pageNo, pageSize, values);
    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

//    public PageSupport<T> findPagination(String hql, int pageNo, Object... values) {
//        return pagedQuery(hql, pageNo, values);
//    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

    @SuppressWarnings("unchecked")
    public T findUniqueByProperty(String name, Object value) {
        return (T) getCriteria(Restrictions.eq(name, value)).uniqueResult();
    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

    public Criteria getCriteria(Criterion... criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);

        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 创建Criteria对象.
     *
     * @param o         the object
     * @param criterion 可变条件列表,org.hibernate.criterion.Restrictions生成的条件
     * @return {@link org.hibernate.Criteria}
     */
    protected Criteria getCriteria(Object o, Criterion... criterion) {
        Criteria criteria = getCurrentSession().createCriteria(o.getClass());

        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * {@inheritDoc}
     *
     * @see
     */

    public String getIdName() {
        String idName = getCurrentSession().getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName();
        return idName;
    }

    /**
     * 创建Query对象.
     *
     * @param values values
     * @param hql    hql
     *               <p/>
     * @return {@link org.hibernate.Query}
     */
    protected Query getQuery(String hql, Object... values) {
        Query query = getCurrentSession().createQuery(hql);

        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    

    /**
     * 检查查实体是否唯一.
     *
     * @param entity             entity
     * @param propertyNameValues 需要检查的属键值对
     * @return {@link Boolean}
     */
    public boolean isNotUnique(T entity, Map<String, Object> propertyNameValues) {
        Criteria criteria = getCriteria(entity).setProjection(Projections.rowCount());
        try {
            criteria.add(Restrictions.allEq(propertyNameValues));

            String idName = getIdName();
            Object id = PropertyUtils.getProperty(entity, idName);

            if (id != null) {
                criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
            }

        } catch (Exception e) {
            throw new DaoException("check entity unique error", e);
        }
        return (Integer) criteria.uniqueResult() > 0;
    }

    /**
     * 通过DetachedCriteria进行分页查询,DetachedCriteria中不能加Order对象，Order对象采用参数传入.
     *
     * @param detachedCriteria 离线的Criteria
     * @param pageNo           当前页
     * @param pageSize         页的大小
     * @param orders           排序字段的动态参数
     * @return {@link PageSupport}
     */
    @SuppressWarnings("unchecked")
	protected PageSupport<T> pagedCriteria(DetachedCriteria detachedCriteria, int pageNo, int pageSize, Order... orders) {

        Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

        if (totalCount < 1) {
            return new PageSupport<T>();
        }

        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        // 增加排序
        for (Order order : orders) {
            criteria.addOrder(order);
        }

        int startIndex = PageSupport.getStartOfPage(pageNo, pageSize);
        List<T> items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();

        return new PageSupport<T>(startIndex, totalCount, pageSize, items);
    }

    /**
     * 通过DetachedCriteria进行分页查询,DetachedCriteria中不能加Order对象，Order对象采用参数传入.
     *
     * @param detachedCriteria 离线的Criteria
     * @param pageNo           当前页
     * @param orders           排序字段的动态参数
     * @return Document Me
     */
    protected PageSupport<T> pagedCriteria(DetachedCriteria detachedCriteria, int pageNo, Order... orders) {
        return pagedCriteria(detachedCriteria, pageNo, PageSupport.DEFAULT_PAGE_SIZE, orders);
    }

    /**
     * 采用Example进行分页查询.
     *
     * @param exampleEntity 采用Example的对象
     * @param pageNo        当前页
     * @param pageSize      页的大小
     * @param orders        排序字段的动态参数
     * @return Document Me
     */
    @SuppressWarnings("unchecked")
	protected PageSupport<T> pagedCriteria(Object exampleEntity, int pageNo, int pageSize, SqlOrder... orders) {
        if (exampleEntity == null) {
            return new PageSupport<T>();
        }
        Criterion criterion = Example.create(exampleEntity).excludeZeroes().ignoreCase().enableLike();
        Criteria criteria = getCriteria(exampleEntity, criterion);
        int totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

        if (totalCount < 1) {
            return new PageSupport<T>();
        }

        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

        for (SqlOrder order : orders) {
            if (order.isMode()) {
                criteria.addOrder(Order.desc(order.getName()));
            } else {
                criteria.addOrder(Order.asc(order.getName()));
            }
        }

        // 实际查询返回分页对象
        int startIndex = PageSupport.getStartOfPage(pageNo, pageSize);
        List<T> items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new PageSupport<T>(startIndex, totalCount, pageSize, items);
    }

    /**
     * 分页查询函数，使用hql.
     *
     * @param hql      query查询语句
     * @param pageNo   页号，从0开始
     * @param pageSize 页的大小
     * @param values   query查询语句的参数
     * @return 查询的页的数据
     */
    @SuppressWarnings("unchecked")
	protected PageSupport<T> pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List<T> countlist = find(countQueryString, values);
        int totalCount = ((Long) countlist.get(0)).intValue();

        if (totalCount < 1) {
            return new PageSupport<T>(pageSize);
        }
        // 实际查询返回分页对象
        int startIndex = PageSupport.getStartOfPage(pageNo, pageSize);
        Query query = getQuery(hql, values);
        List<T> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        logger.info(list.size());
        return new PageSupport<T>(startIndex, totalCount, pageSize, list);
    }

    @SuppressWarnings("unchecked")
	protected List<T> pagedQuery(String hql, int pageNo, int pageSize) {
        int startIndex = PageSupport.getStartOfPage(pageNo, pageSize);
        Query query = getQuery(hql);
        List<T> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        logger.info(list.size());
        return list;
    }

    /**
     * 分页查询函数，使用hql.
     *
     * @param hql    query查询语句
     * @param pageNo 页号，从0开始
     * @param values query查询语句的参数
     * @return Document Me
     */
    protected PageSupport<T> pagedQuery(String hql, int pageNo, Object... values) {
        return pagedQuery(hql, pageNo, PageSupport.DEFAULT_PAGE_SIZE, values);
    }

    /************************工具方法********************/
    protected static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,，用于pagedQuery.
     */
    protected static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }
    /************************工具方法********************/
    

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
