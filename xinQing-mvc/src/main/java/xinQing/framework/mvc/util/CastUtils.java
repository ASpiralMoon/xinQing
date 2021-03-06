package xinQing.framework.mvc.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Array;

/**
 * 类型转化工具
 *
 * Created by xuan on 16-10-3.
 */
public class CastUtils {

    /**
     * String转化为boolean
     *
     * @param flag
     * @return
     */
    public static boolean cast(String flag) {
        return Boolean.parseBoolean(flag);
    }

    /**
     * 将String[]转化为Class类型
     *
     * @param strings
     * @param clazz
     * @return
     */
    public static Object cast(String[] strings, Class<?> clazz) {
        System.out.println(clazz + "____________");
        Object instance = null;
        if (strings == null || strings.length == 0) {
            return instance;
        }
        int len = strings.length;
        // 如果是数组类型
        if (clazz.isArray()) {
            // 获取数组元素类型
            Class<?> componentType = clazz.getComponentType();
            // 通过反射创建一个指定数据类型的数组实例
            instance = Array.newInstance(componentType, len);
            for (int i = 0; i < len; i++) {
                Array.set(instance, i, cast(strings[i], componentType));
            }
            return instance;
        }
        // 如果是基本数据类型
        if (clazz.isPrimitive()) {
            instance = cast(strings[0], clazz);
            return instance;
        }
        if (clazz.isAssignableFrom(String.class)) {
            instance = strings[0];
            return instance;
        }
        return instance;
    }

    /**
     * String转为Class类型
     *
     * @param str
     * @param clazz
     * @return
     */
    public static Object cast(String str, Class<?> clazz) {
        return ConvertUtils.convert(str, clazz);
    }

}
