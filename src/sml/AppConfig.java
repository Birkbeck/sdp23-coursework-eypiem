package sml;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Spring DI configuration.
 *
 * @author Amir Parsa Mahdian
 */
@Configuration
@ComponentScan({"sml.instruction.factory"})
public class AppConfig {

    @Bean
    Function<String, RegisterName> registerValueOf() {
        return (String s) -> {
            try {
                return Registers.Register.valueOf(s);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("This machine architecture does not have register named '" + s + "'.");
            }
        };
    }
}
