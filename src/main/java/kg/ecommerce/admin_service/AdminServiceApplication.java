package kg.ecommerce.admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(value = "kg.ecommerce")
public class AdminServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminServiceApplication.class, args);
  }

}
