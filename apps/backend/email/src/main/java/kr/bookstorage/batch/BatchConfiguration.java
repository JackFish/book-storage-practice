package kr.bookstorage.batch;

import kr.bookstorage.domain.batch.MailQueue;
import kr.bookstorage.domain.batch.MailResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * Created by ohjic on 2017-06-22.
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public ItemReader<MailQueue> reader() {
        String jpqlQuery = "select mq from MailQueue mq";
        JpaPagingItemReader<MailQueue> reader = new JpaPagingItemReader<>();

        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString(jpqlQuery);

        return reader;
    }

    @Bean
    public ItemProcessor<MailQueue, MailResult> processor() {
        return new MailProcess();
    }

    @Bean
    public ItemWriter<MailResult> writer() {
        JpaItemWriter<MailResult> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean(name = "mailConvert")
    public Job mailConvert(JobBuilderFactory jobs, Step s1) {
        return jobs.get("mailConvert")
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<MailQueue> reader,
                      ItemWriter<MailResult> writer, ItemProcessor<MailQueue, MailResult> processor) {
        return stepBuilderFactory.get("step1")
                .<MailQueue, MailResult>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    public class MailProcess implements ItemProcessor<MailQueue, MailResult> {

        @Override
        public MailResult process(MailQueue mailQueue) throws Exception {
            MailResult mailResult = new MailResult();
            mailResult.setMailQueue(mailQueue);
            return mailResult;
        }
    }

}
