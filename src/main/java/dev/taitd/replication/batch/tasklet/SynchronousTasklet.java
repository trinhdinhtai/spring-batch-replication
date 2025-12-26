package dev.taitd.replication.batch.tasklet;

import dev.taitd.replication.config.ReplicaProperties;
import dev.taitd.replication.exeption.GenericException;
import dev.taitd.replication.util.LogMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class SynchronousTasklet implements Tasklet {

  private final ReplicaProperties replicaProperties;

  @Override
  public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    String systemName = replicaProperties.getSystemName();
    log.info("System name: {}", systemName);

    if (StringUtils.hasText(systemName)) {
      throw new GenericException(
          LogMsg.REPLICA_E0009,
          "System name must be configured"
      );
    }
    return RepeatStatus.FINISHED;
  }
}