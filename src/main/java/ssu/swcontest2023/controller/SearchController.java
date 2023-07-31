package ssu.swcontest2023.controller;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.SocketException;

import static java.lang.Thread.sleep;

@Controller
public class SearchController {

    static String sendMessage = "";
    static String receiveMessage = "";
    static boolean isKeyword = false;

    static final String errorMessageInStt = "fail to get key";
    static final String errorMessageInDb = "no items";

    @RequestMapping("/search")
    public String search(@RequestParam("name") String name) throws IOException, InterruptedException {
        System.out.println("search(): keyword: " + name);

        // 검색어가 없는 경우, 음성 입력 받아오기
        if (name.isBlank()){
            if (!isKeyword && SocketClient.isUsable) {
                SocketClient.isUsable = false;

                sendMessage = "0";
                while (!isKeyword && receiveMessage.isBlank()) {
                    System.out.println("search(): request recoding");

                    try {
                        SocketClient.check();
                        SocketClient.connect();
                        receiveMessage = SocketClient.sendMessage(sendMessage);
                        SocketClient.disconnect();
                    } catch (SocketException e) {

                    }

                    // 음성 입력 실패 시, 다시 입력 받기
                    if (receiveMessage.equals(errorMessageInStt)) {
                        playMP3("src/main/resources/MP3/voice3.mp3", 500);
                        sleep(2000);
                        receiveMessage = "";
                    }
                }
                sendMessage = receiveMessage;

                SocketClient.check();
                SocketClient.isUsable = true;
            }
            else {
                System.out.println("error:: search(): keyword already exists");
                SocketClient.check();
                return "redirect:/";
            }
        }
        // 검색어가 있는 경우
        else {
            if (!isKeyword) {
                SocketClient.check();
                isKeyword = true;
                sendMessage = name;
            }
            else {
                System.out.println("error:: search(): keyword already exists");
                SocketClient.check();
                return "redirect:/";
            }
        }

        return "spinner";
    }

    @RequestMapping("/spinner")
    public String spinner(Model model) throws IOException, InterruptedException {
        System.out.println("spinner(): " + sendMessage);

        // 검색어가 없는 경우
        if (sendMessage.isBlank()) {
            System.out.println("spinner(): sendMessage is blank");
            SocketClient.check();
            return "redirect:/";
        }

//        sleep(1000);
        playMP3("src/main/resources/MP3/voice2.mp3", 0);

        SocketClient.isUsable = false;

        try {
            SocketClient.check();
            SocketClient.connect();
            receiveMessage = SocketClient.sendMessage(sendMessage);
            SocketClient.disconnect();
        } catch (SocketException e) {

        }
        SocketClient.isUsable = true;

        // 검색된 상품이 없는 경우 처리
        if (receiveMessage.equals(errorMessageInDb)) {
            System.out.println("spinner(): " + errorMessageInDb);
            SocketClient.check();
            return "redirect:/";
        }

        return "redirect:/product";
    }

    private void playMP3(String filename, int millis) throws InterruptedException {
        System.out.println("mp3Play(): " + filename + ", " + millis);

        sleep(millis);
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();
    }
}


// * 참고
// 동작 원리 (스프링 빈 등록(컴포넌트 스캔을 통한 자동 의존 관계) 잘 설정했을 때)
//1) Client 에서 -> Request 를 보낸다. (*View)
//2) Request URL 에 알맞은 Controller 가 수신한다.
//3) Controller 는 요청 처리를 위해 -> Service 를 호출한다.
//4) Service 는 적절히 데이터를 가공해 -> Controller 에게 넘긴다.
//5) Controller 는 Service 의 결과물을 -> Client 에게 전달해준다. (*View)