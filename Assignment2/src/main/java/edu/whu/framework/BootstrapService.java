package edu.whu.framework;

import edu.Main;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

/**
 * 读取配置文件，实现程序启动的类
 */
public class BootstrapService {


    //从properties中找到启动类,并调用初始化方法
    public static Object start(String propFile) throws BootstrapException {
        Properties properties = loadProperties(propFile);
        Class clazz = getBootstrapClass(properties);
        Object obj=creatObject(clazz);
        invokeInitMethod(clazz,obj);
        return obj;
    }


    private static Properties loadProperties(String propFile) throws BootstrapException {
        Properties properties = new Properties();
        try (InputStream stream = Main.class.getResourceAsStream(propFile)) {
            //没有文件
            if (stream == null) {
                throw new BootstrapException(BootstrapException.ErrorType.FILE_NOTFOUND, "加载属性文件出错，请检查文件是否存在");
            }

            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new BootstrapException(BootstrapException.ErrorType.PROP_READ_ERROR, "属性文件读取失败");
        }
    }


//从属性中获取类
    private static Class getBootstrapClass(Properties properties) throws BootstrapException {
        String bootstrapClass = properties.getProperty("bootstrapClass");
        if (bootstrapClass == null) {
            throw new BootstrapException(BootstrapException.ErrorType.PROP_READ_ERROR, "在属性文件中没有设置bootstrapClass");
        }
        try {
            return Class.forName(bootstrapClass);
        } catch (ClassNotFoundException e) {
            throw new BootstrapException(BootstrapException.ErrorType.CLASS_NOTFOUND, "属性中标注的类不存在");
        }
    }

//创建类的对象MyClass
    private static Object creatObject(Class<?> clazz) throws BootstrapException {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new BootstrapException(BootstrapException.ErrorType.CREATE_OBJECT_ERROR, "创建对象失败：请检查是否有无参构造函数");
        } catch (IllegalAccessException e) {
            throw new BootstrapException(BootstrapException.ErrorType.CREATE_OBJECT_ERROR, "创建对象失败：类不能是抽象类，构造函数不能为私有."+e.getMessage());
        }
    }




//调用标注了@InitMethod的方法
    private static void invokeInitMethod(Class clazz,Object obj) throws BootstrapException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(InitMethod.class)) {continue;}
            if (method.getParameterCount() > 0) {
                throw new BootstrapException(BootstrapException.ErrorType.INITMETHOD_ERROR,"带参数的方法不允许标注@InitMethod");
            }
            invokeMethod(Modifier.isStatic(method.getModifiers()) ?clazz:obj, method);
        }
    }



//调用方法
    private static void invokeMethod(Object obj, Method method) throws BootstrapException {
        try {
            method.setAccessible(true);
            method.invoke(obj);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new BootstrapException(BootstrapException.ErrorType.METHOD_CALL_ERROR, "调用方法失败："+e.getMessage());
        }
    }

}
