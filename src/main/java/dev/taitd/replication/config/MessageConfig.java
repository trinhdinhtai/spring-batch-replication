package dev.taitd.replication.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

@Configuration
public class MessageConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.messages")
  public MessageSourceProperties messageSourceProperties() {
    return new MessageSourceProperties();
  }

  @Bean
  public MessageSource messageSource(MessageSourceProperties messageSourceProperties) {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    List<String> baseNames = messageSourceProperties.getBasename();
    if (!baseNames.isEmpty()) {
      messageSource.setBasenames(baseNames.stream()
          .map(StringUtils::trimAllWhitespace)
          .toArray(String[]::new));
      messageSource.setDefaultEncoding("UTF-8");
    }
    return messageSource;
  }
}