package dev.taitd.replication.util;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


public class MessageUtil {

  private static MessageSource messageSource;

  private MessageUtil() {
    // Nothing
  }

  private static void init() {
    messageSource = StaticBeanContext.getBeanByClass(MessageSource.class);
  }

  public static String getMessage(String code, Object[] params) {
    return getMessage(code, params, null);
  }

  private static String getMessage(String code, Object[] params, String defaultMessage) {
    if (messageSource == null) {
      init();
    }

    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(code, params, defaultMessage, locale);
  }


}