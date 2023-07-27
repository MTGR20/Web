package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssu.swcontest2023.AccessToProductDB;

import java.util.Arrays;

@Controller
public class SearchController {

    //private static final String path = "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\"; //경로 수정해서 사용하세요

    @PostMapping("product")
    public String search(@RequestParam("name") String name) {
    //public String search(@RequestParam(value = "name") String name, Model model){
        System.out.println("검색: " + name);
        String[] sendAr = null;

        if (!name.isBlank()){
//            Arrays.fill(sendAr, name);
            sendAr = new String[]{name};
        }
        else{
            sendAr = new String[]{"0"};
        }

        SocketClient.main(sendAr);  //소켓 통신 요청 (:상품명 넘겨주고 상품 정보가 저장된 파일 경로 스트링 얻기 || 완료 메세지만 받기 ) / 아님 자바에서 디비 접근하게 할거면 파이썬 소켓 서버에서는 디비에 저장까지만 해도 됨
        // (로딩 화면 띄우다가)
        return "product";           //소켓 응답 받으면 product로 연결하든가

        //AccessToProductDB.main(); => DB에서 읽어와 View로 띄우는 부분은 productsListController에서 처리하면 됨

    }
}


// * 참고
// 동작 원리 (스프링 빈 등록(컴포넌트 스캔을 통한 자동 의존 관계) 잘 설정했을 때)
//1) Client 에서 -> Request 를 보낸다. (*View)
//2) Request URL 에 알맞은 Controller 가 수신한다.
//3) Controller 는 요청 처리를 위해 -> Service 를 호출한다.
//4) Service 는 적절히 데이터를 가공해 -> Controller 에게 넘긴다.
//5) Controller 는 Service 의 결과물을 -> Client 에게 전달해준다. (*View)