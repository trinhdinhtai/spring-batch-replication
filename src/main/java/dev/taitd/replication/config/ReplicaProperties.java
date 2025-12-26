package dev.taitd.replication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "replica")
public class ReplicaProperties {
  private String systemName;

}