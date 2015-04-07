package org.svtcc.online.management.dao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类..
 * 
 * @author <a href="mailto:huangzm529@gmail.com">William Huang</a>
 * 
 * @param <T>
 * @version $Id: PageSupport.java 2230 2008-02-25 08:43:55Z wangsulong $
 */
public class PageSupport<T> implements Serializable {

    /**
     * user custom editor which translate special Object to define string.
     */
    private Map<Class<?>, ICustomEditor> customEditor = new HashMap<Class<?>, ICustomEditor>();

    /**
     * page href.
     */
    private String link;

    /**
     * load other param
     */
    private Map<String, Object> otherParams = new HashMap<String, Object>();

    /**
     * 
     */
    private static final long serialVersionUID = 4959812113238234719L;

    /**
     * 
     * 默认的每页的记录数..
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页的记录数.
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前页第一条数据在List中的位置,从0开始
     */
    private int start;

    /**
     * 当前页中存放的记录,类型一般为ArrayList
     */
    private List<T> resultData;

    /**
     * 总记录数.
     */
    private int totalCount;
    

    /**
     * 构造方法，只构造空页.
     */
    public PageSupport() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }

    /**
     * 构造方法，只构造每页显示的页面数.
     * 
     * @param pageSize
     *            the record number of per page.
     */
    public PageSupport(int pageSize) {
        this(0, 0, pageSize, new ArrayList<T>());
    }

    /**
     * 默认构造方法.
     * 
     * @param start
     *            本页数据在数据库中的起始位置
     * @param totalSize
     *            数据库中总记录条数
     * @param pageSize
     *            本页容量
     * @param data
     *            本页包含的数据
     */
    public PageSupport(int start, int totalSize, int pageSize, List<T> data) {
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalSize;
        this.resultData = data;
    }

    /**
     * 取数据库中包含的总记录数.
     * 
     * @return int
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 取总页数.
     * 
     * @return int
     */
    public int getTotalPageCount() {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    /**
     * 取每页数据容量..
     * 
     * @return int
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 当前页中的记录..
     * 
     * @return List
     */
    public List<T> getResultData() {
        return resultData;
    }

    /**
     * 取当前页码,页码从1开始.
     * 
     * @return int
     */
    public int getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /**
     * 是否有下一页.
     * 
     * @return {@link Boolean}
     */
    public boolean isNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    /**
     * 是否有上一页.
     * 
     * @return {@link Boolean}
     */
    public boolean isPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据的位置，每页条数使用默认值..
     * 
     * @param pageNo
     *            pageNo
     * @return int
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据的位置,startIndex从0开始.
     * 
     * @param pageNo
     *            pageNo
     * @param pageSize
     *            pageSize
     * @return int
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    /**
     * register custom editor to parameter translating of page navigator.
     * 
     * @param className
     *            the class name which need special translate.
     * @param customEditor
     *            the translate tool.
     */
    public void registerCustomEditor(Class<?> className, ICustomEditor customEditor) {
        this.customEditor.put(className, customEditor);
    }

    /**
     * add another parameter.
     * 
     * @param key
     *            the key of parameter.
     * @param value
     *            the value of parameter.
     */
    public void addOtherParams(String key, Object value) {
        this.otherParams.put(key, value);
    }

    /**
     * create navigator string of parameter.
     * 
     * @param request
     *            ...
     * @return String of navigator parameter.
     */
    public String getParamsStr(HttpServletRequest request) {
        String result = "";
        Set<String> keySet = this.otherParams.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            result += "&" + key + "=" + this.translate(request, key, otherParams.get(key));
        }
        return result;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    private String translate(HttpServletRequest request, String key, Object value) {
        String result = "";
        boolean customEditor = false;
        Set<Class<?>> keySet = this.customEditor.keySet();
        Iterator<Class<?>> it = keySet.iterator();
        while (it.hasNext()) {
            Class<?> tempClass = it.next();
            if (value.getClass().equals(tempClass)) {
                customEditor = true;
                result = this.customEditor.get(tempClass).parse(request, value);
                break;
            }
        }
        if (!customEditor) {
            result = (this.otherParams.get(key) == null) ? "" : this.otherParams.get(key).toString();
        }
        return result;
    }
    
    public List<Integer> getPageNumbers() {
    	List<Integer> pageNumbers = new ArrayList<Integer>();
    	for (int i = 1; i <= this.getTotalPageCount(); i++) {
			pageNumbers.add(i);
		}
    	
    	return pageNumbers;
    }
}
