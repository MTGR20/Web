package ssu.swcontest2023;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ssu.swcontest2023.controller.SocketClient;
//import ssu.swcontest2023.controller.ClientConnect;

import java.io.IOException;
import java.net.Socket;

@SpringBootApplication
public class SwContest2023Application {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SwContest2023Application.class, args);
	}

}
