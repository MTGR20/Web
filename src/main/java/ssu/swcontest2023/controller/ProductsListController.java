package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ProductsListController {

    //AccessToProductDB.main(); => DB에서 읽어와 View로 띄우는 부분은 productsListController에서 처리하면 됨

    //* 소켓 통신을 통해 가져온 상품 정보(이름, 가격, 이미지, 알레르기 정보)를 활용해 || 디비 저장 내용을 활용해
    //  \뷰로 띄우면 된다. (model.addAttribute())
    //* 다음 상품 보여주기는 뷰는 그대로 두고 상품 이미지와 상품 정보들만 바꿔주면 된다.
    //* home 눌렀을 경우에 return "redirect:/";
    //* 일반 상태는 입력 대기 (키보드 / 음성)
    //  \키보드 입력으로 버튼 클릭 시 해당 상품 페이지 (홈플러스) 띄워준다. return "homplus";
    //  \음성 입력 시 소켓 통신을 통해 선택인지 패스인지 값을 받아온 후 선택일 경우만 홈플러스 상품 페이지 띄워준다.

}
