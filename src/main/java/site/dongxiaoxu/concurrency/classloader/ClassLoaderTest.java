package site.dongxiaoxu.concurrency.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dongxiaoxu on 2019/8/11.
 */
public class ClassLoaderTest {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyParentClassLoader classLoader = new MyParentClassLoader();
        Class<?> clazz = classLoader.loadClass("site.dongxiaoxu.concurrency.classloader.Demo");
        Object demo = clazz.newInstance();
        Method method = clazz.getMethod("print");
        method.invoke(demo);
        System.out.println(demo);
    }
}
