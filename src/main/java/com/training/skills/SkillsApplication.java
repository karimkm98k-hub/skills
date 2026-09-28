package com.training.skills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillsApplication.class, args);

        try {
            int x = 10/0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception");
            System.out.println(e.getMessage());
        }
	}

}
