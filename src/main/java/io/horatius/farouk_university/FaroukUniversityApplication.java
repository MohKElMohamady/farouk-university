package io.horatius.farouk_university;

import io.horatius.farouk_university.configurations.DataStaxAstraConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties({DataStaxAstraConfiguration.class})
public class FaroukUniversityApplication {

    /*
     * Stackoverflow links to read:
     * https://stackoverflow.com/questions/54126263/invoking-non-blocking-operations-sequentially-while-consuming-from-a-flux-includ?rq=1
     * https://stackoverflow.com/questions/47988433/mono-vs-flux-in-reactive-stream?rq=1
     * https://stackoverflow.com/questions/50080386/map-vs-flatmap-in-spring-web-flux-and-reactor?rq=1
     * https://stackoverflow.com/questions/60028533/difference-between-two-flux
     * https://stackoverflow.com/questions/56115447/mono-defer-vs-mono-create-vs-mono-just
     * https://stackoverflow.com/questions/57870706/whats-the-point-of-switchifempty-getting-evaluated-eagerly?noredirect=1&lq=1
     */
    public static void main(String[] args) {
        SpringApplication.run(FaroukUniversityApplication.class, args);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraConfiguration dataStaxAstraConfiguration){
        Path bundle = dataStaxAstraConfiguration.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
