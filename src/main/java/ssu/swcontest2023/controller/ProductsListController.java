package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ProductsListController {

    //* 소켓 통신을 통해 가져온 상품 정보(이름, 가격, 이미지, 알레르기 정보)를 활용해 || 디비 저장 내용을 활용해
    //  \뷰로 띄우면 된다. (model.addAttribute())
    //* 다음 상품 보여주기는 뷰는 그대로 두고 상품 이미지와 상품 정보들만 바꿔주면 된다.
    //* return "homplus"; //|| //return "redirect:/";
    //  \리다이렉트는 home 눌렀을 경우에만 해주고, 아니라면 대기하다가 상품 선택 시 홈플러스 페이지를 띄워준다. (html 추가 생성)

}
