package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public String home() throws IOException {
        System.out.println("home()");

        SearchController.sendMessage = "";
        SearchController.receiveMessage = "";
        SearchController.isKeyword = false;

        SocketClient.check();
//        SocketClient.isUsable = true;

        return "home";
    }
}
