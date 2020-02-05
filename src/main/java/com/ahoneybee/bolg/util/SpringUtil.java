package com.ahoneybee.bolg.util;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext applicationContext;

    @Override

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (SpringUtil.applicationContext == null) {

            SpringUtil.applicationContext = applicationContext;

        }

        log.info("ApplicationContext配置成功,applicationContext对象：" + SpringUtil.applicationContext);

    }

    /**
     * 获取容器
     *
     * @return 容器
     */
    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }

    /**
     * 根据名称获取bean
     *
     * @param name 名称
     * @return bean
     */
    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);

    }

    /**
     * 根据类型获取bean
     *
     * @param clazz 类型
     * @param <T>   范型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }

    /**
     * 根据名称和类型获取bean
     *
     * @param name  名称
     * @param clazz 类型
     * @param <T>   范型
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {

        return getApplicationContext().getBean(name, clazz);

    }
}
