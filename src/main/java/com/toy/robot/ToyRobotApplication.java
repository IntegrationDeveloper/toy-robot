package com.toy.robot;

import java.util.Scanner;

import com.toy.robot.application.Simulator;
import com.toy.robot.application.ToyRobot;
import com.toy.robot.exception.ToyRobotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.toy.robot.application.SquareTableTop;

@SpringBootApplication
public class ToyRobotApplication implements CommandLineRunner{
	public static Logger log = LoggerFactory.getLogger(ToyRobotApplication.class);

	public static void main(String[] args) {
		log.info("Starting Application");
		SpringApplication.run(ToyRobotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Toy Robot simulator");
		Scanner scanner = new Scanner(System.in);
		log.info("Enter a robot command:");
		SquareTableTop squareTableTop = new SquareTableTop(4,4);
		ToyRobot toyRobot = new ToyRobot();
		Simulator simulator = new Simulator(squareTableTop,toyRobot);
		boolean isRobotMoving = true;
		while(isRobotMoving){
			String inputCommand = scanner.nextLine();
			if("EXIT".equals(inputCommand)){
				isRobotMoving = false;
				System.exit(0);
			}else{
				try{
					simulator.simulate(inputCommand);
				}catch(ToyRobotException ex){
					log.error(ex.getMessage());
				}
			}
		}
	}
}
