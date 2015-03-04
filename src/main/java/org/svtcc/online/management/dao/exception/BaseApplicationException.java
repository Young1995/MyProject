/**
 * 
 */
package org.svtcc.online.management.dao.exception;

/**
 * Base Exception.
 * 
 * @author <a href="mailto:huangzm529@gmail.com">William Huang</a>
 * 
 * @version $Id: BaseApplicationException.java 1564 2007-12-24 07:06:52Z william $
 */
public class BaseApplicationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7846440509124111891L;

    /**
     * Default Constructor.
     * 
     * @param msg
     *            message
     */
    public BaseApplicationException(String msg) {
        super(msg);
    }

    /**
     * Default Constructor.
     * 
     * @param msg
     *            message
     * @param e
     *            Throwable
     */
    public BaseApplicationException(String msg, Throwable e) {
        super(msg, e);
    }
}
