package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class SearchController {

    private static final String path = "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\test_files";

    //* 참고
    //product.html은 만들어져있는데
    //form에서 name 받아오는거랑 크롤링, DB 연결 등 완료해야 페이지 보일 것임.

    //* Post mapping? //form을 쓰긴 함 --> 알아보고..
    @PostMapping("product") // 데이터를 보낼 곳
    public String search(@RequestParam("name") String name) {
        //public String search(@RequestParam(value = "name") String name, Model model){
        //System.out.println("SearchController.save");
        System.out.println("검색: " + name);

        String[] sendAr = new String[1];
        Arrays.fill(sendAr, name);

        SocketClient.main(sendAr);

        //Socket
        //ClientConnect cc = new ClientConnect("10.21.20.48", 9999);
        //String2json
        //ObjectMapper mapper = new ObjectMapper();
        //String jsonStr = mapper.writeValueAsString(name);
        //String newFileName= cc.getFile(path, jsonStr);
        //cc.closeConnections();

        //Socket socket = new Socket("10.21.20.48", 8888);
        // 메시지 보내기
        //OutputStream out = socket.getOutputStream();
        //DataOutputStream dos = new DataOutputStream(out);
        //dos.writeUTF("Hello");
        //dos.close();
        //socket.close();

        /*
        try{
            ServerSocket serversocket = new ServerSocket(8888);

            Socket socket = serversocket.accept();

            // 메시지 받기
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            System.out.println("Received: " + dis.readUTF());

            dis.close();
            socket.close();
            serversocket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */

        //System.out.println("name = " + name);
        //검색명 가져와(form.getName()) 크롤링 코드 활용해서 크롤링 후 디비에 저장한다.
        //* (여기서 product name만 받아서 유림이 코드에 전달해줘야하는디) 크롤링 코드를 JAVA로 다시 써서 여기서 해야할 것 같기도.
        //** https://lotuus.tistory.com/108
        //* (productService.searchList(products))
        //이후 디비 저장 내용을 활용해 뷰로 띄우면 된다. (model.addAttribute())
        //* 다음 상품 보여주기는 뷰는 그대로 두고 상품 이미지와 상품 정보들만 바꿔주면 된다.
        //return "homplus"; //|| //return "redirect:/";
        //리다이렉트는 뒤로가기 눌렀을 경우에만 해주고, 아니라면 대기하다가 상품 선택 시 홈플러스 페이지를 띄워준다. (html 추가 생성)
        return "product";
    }
}


// * 참고
// 동작 원리 (스프링 빈 등록(컴포넌트 스캔을 통한 자동 의존 관계) 잘 설정했을 때)
//1) Client 에서 -> Request 를 보낸다. (*View)
//2) Request URL 에 알맞은 Controller 가 수신한다.
//3) Controller 는 요청 처리를 위해 -> Service 를 호출한다.
//4) Service 는 적절히 데이터를 가공해 -> Controller 에게 넘긴다.
//5) Controller 는 Service 의 결과물을 -> Client 에게 전달해준다. (*View)