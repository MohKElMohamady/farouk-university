package org.horatio.farouk_university;

import org.horatio.farouk_university.configurations.DataStaxAstraConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties({DataStaxAstraConfiguration.class})
public class FaroukUniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaroukUniversityApplication.class, args);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraConfiguration dataStaxAstraConfiguration){
        Path bundle = dataStaxAstraConfiguration.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
