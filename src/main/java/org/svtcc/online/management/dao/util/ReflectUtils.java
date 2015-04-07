package org.svtcc.online.management.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 反射工具类..
 * 
 * @author <a href="mailto:huangzm529@gmail.com">William Huang</a>
 * @author <a href="mailto: sulong.wang@symbio-group.com">sulong.wang</a>
 * @version $Id: ReflectUtils.java 1568 2007-12-24 07:11:28Z william $
 * 
 */
public final class ReflectUtils<T> {

    private ReflectUtils() {

    }

    private static Log logger = LogFactory.getLog(ReflectUtils.class);

    /**
     * 通过反射,获得定义Class时声明的父类的第一个泛型参数的类型..
     * 
     * @param clazz
     *            the entity class
     * @return 父类的第一个泛型参数的类型
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的第一个泛型参数的类型..
     * 
     * @param clazz
     *            the entity class
     * @param index
     *            泛型参数位置
     * @return 泛型参数的类型
     * 
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class<?>)params[index];
    }

    /**
     * 得到注入的对象..
     * 
     * @param object
     *            源对象
     * @param clazz
     *            注入对象的Class类型
     * @return 注入的对象
     * @throws IllegalAccessException
     *             IllegalAccessException
     */
    public static Object getIocObject(Object object, Class<?> clazz) throws IllegalAccessException {
        List<Field> list = getFieldsByClass(object, clazz);
        if (list.size() == 0) {
            return null;
        }
        Object result = getDeclaredFieldObject(object, list.get(0));
        return result;
    }

    /**
     * 通过指定的clazz查找object对象中符合条件的定义Field.
     * 
     * @param object
     *            待查找对象
     * @param clazz
     *            条件Class
     * @return 符合条件的Field列表
     */
    public static List<Field> getFieldsByClass(Object object, Class<?> clazz) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 检查field对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
            if (field.getType().isAssignableFrom(clazz)) {
                list.add(field);
            }
        }
        return list;
    }

    /**
     * 得到字段名字对应的对象上此Field表示的字段的值..
     * 
     * @param object
     *            Field所在的对象
     * @param name
     *            Field的名字
     * @return 字段的值
     * @throws SecurityException
     * 
     */
    public static Object getDeclaredFieldObject(Object object, String name) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(name);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return getDeclaredFieldObject(object, field);
    }

    /**
     * 对象上此Field表示的字段的值..
     * 
     * @param object
     *            Field所在的对象
     * @param field
     *            需要获取字段值的字段
     * @return 字段的值
     * 
     */
    public static Object getDeclaredFieldObject(Object object, final Field field) {
        final boolean accessible = field.isAccessible();
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            public Object run() {
                field.setAccessible(true);
                return null;
            }

        });
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            public Object run() {
                field.setAccessible(accessible);
                return null;
            }

        });
        return result;
    }

    /**
     * Copy object attributes' value to another object according by attribute's
     * type and name.
     * 
     * @param src
     *            resource object.
     * @param dest
     *            destination object.
     * @throws IllegalAccessException
     *             DOCUMENT ME
     */
    public static void copyAttrs(Object src, Object dest) throws IllegalAccessException {
        Field[] srcFields = src.getClass().getDeclaredFields();
        Field[] destFields = dest.getClass().getDeclaredFields();
        for (Field srcField : srcFields) {
            srcField.setAccessible(true);
            for (Field destField : destFields) {
                if (destField.getName().equals(srcField.getName())
                        && srcField.getType().isAssignableFrom(destField.getType())) {
                    destField.setAccessible(true);
                    destField.set(dest, srcField.get(src));
                    break;
                }
            }
        }
    }
}
