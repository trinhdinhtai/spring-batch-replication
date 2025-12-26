package dev.taitd.replication.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StaticBeanContext implements ApplicationContextAware {

  private static ApplicationContext context;

  public static <T> T getBeanByClass(Class<T> clazz) {
    return context.getBean(clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    setContext(context);
  }

  /**
   * Private method context setting (better practice for setting a static field in a bean instance -
   * see comments of this article for more info).
   */
  private static synchronized void setContext(ApplicationContext context) {
    StaticBeanContext.context = context;
  }
}