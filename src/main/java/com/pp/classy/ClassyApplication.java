package com.pp.classy;

import com.pp.classy.entities.User;
import com.pp.classy.repositories.UsersRepository;
import java.awt.image.BufferedImage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class ClassyApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClassyApplication.class, args);
  }

  @Bean
  public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
    return new BufferedImageHttpMessageConverter();
  }

  @Bean
  public CommandLineRunner loadData(UsersRepository usersRepository) {
    return (args) -> {
      User santi = new User();
      santi.setName("Santi Lokejaroenlarb");
      User sukanda = new User();
      sukanda.setName("Sukanda Pudpadee");
      User santiUser = usersRepository.save(santi);
      User sukandaUser = usersRepository.save(sukanda);
      System.out.println(santi.toString());
      System.out.println(sukandaUser.toString());
    };
  }

}
