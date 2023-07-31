package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model) throws IOException {
        System.out.println("index()");

        SearchController.sendMessage = "";
        SearchController.receiveMessage = "";
        SearchController.isKeyword = false;

        SocketClient.disconnect();
        SocketClient.isUsable = true;

        return "index";
    }
}
