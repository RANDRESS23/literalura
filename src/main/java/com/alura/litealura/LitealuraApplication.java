package com.alura.litealura;

import com.alura.litealura.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LitealuraApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LitealuraApplication.class, args);
		Main main = context.getBean(Main.class);
		main.showMenu();
	}
}
