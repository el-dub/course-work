package com.github.el_dub.vacancies.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class SeleniumConfig {

//    @PostConstruct
//    void postConstruct() throws IOException {
//        Resource resource = new ClassPathResource("selenium/chromedriver.exe");
//        String filePath = resource.getFile().getPath();
//        System.setProperty("webdriver.chrome.driver", filePath);
//    }
//
//    @Bean
//    public ChromeDriver driver() {
//        return new ChromeDriver();
//    }

//    @PreDestroy
//    void preDestroy() {
//        driver.quit();
//    }
}
