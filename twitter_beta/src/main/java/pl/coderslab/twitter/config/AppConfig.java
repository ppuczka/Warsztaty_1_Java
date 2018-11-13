package pl.coderslab.twitter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EntityScan(basePackages = {"pl.coderslab.twitter.entity"})
public class AppConfig implements WebMvcConfigurer {

}
