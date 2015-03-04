package org.svtcc.online.management.dao.exception;


/**
 * The Data access object Exception.
 * 
 * @author <a href="mailto:huangzm529@gmail.com">William Huang</a>
 * @version $Id: DaoException.java 1566 2007-12-24 07:08:48Z william $
 */
public class DaoException extends BaseApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = 8923411727399075937L;

    /**
     * Default constructor.
     * 
     * @param msg
     *            exception message
     * @param e
     *            the exception
     */
    public DaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
