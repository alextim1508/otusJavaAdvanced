package ru.otus.timofeev.task17;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.timofeev.task17.service.ClientShopService;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);
		ClientShopService shopServer = applicationContext.getBean(ClientShopService.class);

		shopServer.createUser("1", "alex", "alex@gmail.com");

		shopServer.changeUserEmail("1","alex@ya.ru");

		shopServer.changeUserName("1","aleksey");

		shopServer.createProduct("1","SamsungS23");

		shopServer.addProductToCard("1","1");
	}
}