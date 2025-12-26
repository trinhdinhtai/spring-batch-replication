package dev.taitd.replication.batch.job;

import dev.taitd.replication.batch.listener.JobExecutionLoggingListener;
import dev.taitd.replication.batch.tasklet.SynchronousTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;


@Configuration
@RequiredArgsConstructor
public class SynchronousJobConfig {

  public static final String SYNCHRONOUS_FLOW_NAME = "synchronousFlow";
  public static final String STEP_NAME = "synchronousTasklet";

  private final JobRepository jobRepository;
  private final JobExecutionLoggingListener listener;
  private final SynchronousTasklet synchronousTasklet;
  private final PlatformTransactionManager transactionManager;

  @Value("${replica.job.name:batch-replica}")
  private String jobName;

  @Bean
  public Step synchronousStep() {
    return new StepBuilder(STEP_NAME, jobRepository)
        .tasklet(synchronousTasklet, transactionManager)
        .build();
  }

  @Bean
  public Flow synchronousFlow() {
    return new FlowBuilder<Flow>(SYNCHRONOUS_FLOW_NAME)
        .start(synchronousStep())
        .build();
  }

  @Bean
  public Job synchronousJob() {
    // Validate job name
    if (!StringUtils.hasText(jobName)) {
      throw new IllegalStateException(
          "Job name must be configured via 'replica.job.name' property");
    }

    return new JobBuilder(jobName, jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(synchronousFlow())
        .end()
        .build();

  }

}
