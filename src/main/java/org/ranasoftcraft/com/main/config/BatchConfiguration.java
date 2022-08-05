package org.ranasoftcraft.com.main.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author sandeep.rana
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private Reader reader;
	
	@Autowired
	private Writer writer;

	@Autowired
	private Processor processor;
	
	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("download-file")
				.incrementer(new RunIdIncrementer())
				.start(createStep()).build();
	}
	
	@Bean
	public Job runAgain() {
		return jobBuilderFactory.get("download-file-v2")
				.incrementer(new RunIdIncrementer())
				.start(createStep()).build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("step")
				.chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	
}
