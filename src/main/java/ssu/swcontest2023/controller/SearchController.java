package ssu.swcontest2023.controller;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//<<<<<<< HEAD
//import ssu.swcontest2023.repository.AccessToProductDB;
//=======
//>>>>>>> tmp

import java.io.IOException;

import static java.lang.Thread.sleep;

@Controller
public class SearchController {

    //private static final String path = "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\"; //경로 수정해서 사용하세요
    static boolean isRecord = true;
    static boolean isKeyword = true;
    static String errorMessage = "fail to get key";
    static String[] sendAr = null;

    @RequestMapping("/search")
    public String search(@RequestParam("name") String name) throws IOException, InterruptedException {
        //public String search(@RequestParam(value = "name") String name, Model model){
        System.out.println("검색: " + name);

        // 검색어가 없는 경우
        if (name.isBlank()){
            if (isRecord) {
                System.out.println("execute recoding");
                isRecord = false;

                sendAr = new String[]{"0"};

                SocketClient.connect();
                String ret = SocketClient.sendForRecord(sendAr);

                while (ret.equals(errorMessage)) {
                    // 음성 입력 실패 시, 다시 입력 받기
                    System.out.println("again message out");
                    sleep(500);
                    String mp3 = "src/main/resources/MP3/voice3.mp3";
                    MP3Player mp3Player = new MP3Player(mp3);
                    mp3Player.play();

                    sleep(2000);
                    SocketClient.disconnect();
                    SocketClient.connect();
                    ret = SocketClient.sendForRecord(sendAr);
                }
                SocketClient.disconnect();

                sendAr = new String[]{ret};

                isRecord = true;
            }
            // 이미 음성 입력을 받고 있는 경우
            else {
                System.out.println("fail to socket connect: already execute recoding");
            }
        }
        // 검색어가 있는 경우
        else {
            if (isKeyword) {
                // 음성 입력을 받고 있는 경우, 연결 끊기
                if (SocketClient.socket.isConnected()) SocketClient.disconnect();

                isRecord = false;
                isKeyword = false;

//            Arrays.fill(sendAr, name);
                sendAr = new String[]{name};
            }
            // 이미 검색어가 입력된 경우
            else {
                System.out.println("fail to socket connect: already input keyword");
            }
        }

//        SocketClient.main(sendAr);  //소켓 통신 요청 (:상품명 넘겨주고 상품 정보가 저장된 파일 경로 스트링 얻기 || 완료 메세지만 받기 ) / 아님 자바에서 디비 접근하게 할거면 파이썬 소켓 서버에서는 디비에 저장까지만 해도 됨


        // (로딩 화면 띄우다가)
        return "spinner";           //소켓 응답 받으면 product로 연결하든가

        //AccessToProductDB.main(); => DB에서 읽어와 View로 띄우는 부분은 productsListController에서 처리하면 됨

    }

    @RequestMapping("/spinner")
    public String spinner(Model model) throws IOException, InterruptedException {

        System.out.println("keyword in spinner: " + sendAr[0]);

        // 로딩 음성 출력
        System.out.println("loading message out");
//        sleep(1000);
        String mp3 = "src/main/resources/MP3/voice2.mp3";
        MP3Player mp3Player = new MP3Player(mp3);
        mp3Player.play();

        if (SocketClient.socket.isConnected()) SocketClient.disconnect();

        SocketClient.connect();
        SocketClient.sendForKeyword(sendAr);
        SocketClient.disconnect();

        isRecord = true;
        isKeyword = true;

        return "redirect:/product";
    }
}


// * 참고
// 동작 원리 (스프링 빈 등록(컴포넌트 스캔을 통한 자동 의존 관계) 잘 설정했을 때)
//1) Client 에서 -> Request 를 보낸다. (*View)
//2) Request URL 에 알맞은 Controller 가 수신한다.
//3) Controller 는 요청 처리를 위해 -> Service 를 호출한다.
//4) Service 는 적절히 데이터를 가공해 -> Controller 에게 넘긴다.
//5) Controller 는 Service 의 결과물을 -> Client 에게 전달해준다. (*View)
