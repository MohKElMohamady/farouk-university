package io.horatius.farouk_university.configurations.database;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Path;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraConfiguration {
    private File secureConnectBundle;

    public DataStaxAstraConfiguration() {
    }

    public DataStaxAstraConfiguration(File secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }

    public File getSecureConnectBundle() {
        return secureConnectBundle;
    }

    public void setSecureConnectBundle(File secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraConfiguration dataStaxAstraConfiguration){
        Path bundle = dataStaxAstraConfiguration.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
