package org.horatio.farouk_university;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.horatio.farouk_university.configurations.DataStaxAstraConfiguration;
import org.horatio.farouk_university.dao.CourseByCreator;
import org.horatio.farouk_university.models.Enrollment;
import org.horatio.farouk_university.repositories.CourseByCreatorRepository;
import org.horatio.farouk_university.repositories.CourseCapacityRepository;
import org.horatio.farouk_university.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties({DataStaxAstraConfiguration.class})
public class FaroukUniversityApplication {

    /*
     * Stackoverflow links to read:
     * https://stackoverflow.com/questions/54126263/invoking-non-blocking-operations-sequentially-while-consuming-from-a-flux-includ?rq=1
     * https://stackoverflow.com/questions/47988433/mono-vs-flux-in-reactive-stream?rq=1
     * https://stackoverflow.com/questions/50080386/map-vs-flatmap-in-spring-web-flux-and-reactor?rq=1
     * https://stackoverflow.com/questions/60028533/difference-between-two-flux
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
