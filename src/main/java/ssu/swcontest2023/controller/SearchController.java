package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssu.swcontest2023.AccessToProductDB;

import java.util.Arrays;

@Controller
public class SearchController {

    //private static final String path = "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\"; //경로 수정해서 사용하세요

    String[] sendAr = null;

    @RequestMapping("/search")
    public String search(@RequestParam("name") String name) {
    //public String search(@RequestParam(value = "name") String name, Model model){
        System.out.println("검색: " + name);

        sendAr = new String[]{name};

        if (name.isBlank()){
            sendAr = new String[]{"0"};
        }

        SocketClient.main(sendAr);
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