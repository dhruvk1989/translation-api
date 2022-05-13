package com.dhruv.translationapi;

import com.dhruv.translationapi.model.request.Mail;
import com.dhruv.translationapi.service.MailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.ws.rs.core.Application;
import java.util.Properties;

@SpringBootApplication
public class TranslationApiApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(TranslationApiApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(env.getProperty("spring.mail.host"));
		javaMailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
		javaMailSender.setUsername(env.getProperty("spring.mail.username"));
		javaMailSender.setPassword(env.getProperty("spring.mail.password"));

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", true);

		javaMailSender.setJavaMailProperties(javaMailProperties);
		return javaMailSender;
	}


}
