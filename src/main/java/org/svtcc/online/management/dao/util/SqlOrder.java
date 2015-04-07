/**
 * 
 */
package org.svtcc.online.management.dao.util;

import java.io.Serializable;

/**
 * the sql order.
 * 
 * @author <a href="mailto:huangzm529@gmail.com">William Huang</a>
 * @version $Id: SqlOrder.java 1568 2007-12-24 07:11:28Z william $
 */
public class SqlOrder implements Serializable {

    private static final long serialVersionUID = 5291872221651016115L;

    private String name;

    private boolean mode;

    /**
     * default constructor.
     */
    public SqlOrder() {

    }

    /**
     * default constructor.
     * 
     * @param name
     *            the column.
     */
    public SqlOrder(String name) {
        this(name, false);
    }

    /**
     * default constructor.
     * 
     * @param name
     *            the column.
     * @param mode
     *            <code>true</code> means ASC and <code>false</code> means
     *            DESC
     */
    public SqlOrder(String name, boolean mode) {
        this.name = name;
        this.mode = mode;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
