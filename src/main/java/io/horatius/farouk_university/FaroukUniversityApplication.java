package io.horatius.farouk_university;

import io.horatius.farouk_university.configurations.database.DataStaxAstraConfiguration;
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
     * https://stackoverflow.com/questions/52420224/how-to-delete-a-row-using-non-primary-key-in-cassandra
     * https://stackoverflow.com/questions/47242335/how-to-check-if-mono-is-empty
     * https://stackoverflow.com/questions/53595420/correct-way-of-throwing-exceptions-with-reactor
     * https://stackoverflow.com/questions/45903813/webflux-functional-how-to-detect-an-empty-flux-and-return-404
     * https://stackoverflow.com/questions/56496426/project-reactor-mono-map-vs-mono-flatmap
     * https://stackoverflow.com/questions/42021559/convert-from-flux-to-mono
     */
    public static void main(String[] args) {
        SpringApplication.run(FaroukUniversityApplication.class, args);
    }

}
