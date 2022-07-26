package io.horatius.farouk_university.models.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

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
}
