package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssu.swcontest2023.AccessToProductDB;
import ssu.swcontest2023.domain.Product;

import java.util.ArrayList;

@Controller
public class ProductsListController {

    ArrayList<Product> productList;
    Product product;
    static String dir_audio = "C:\\Users\\User\\IdeaProjects\\Web\\src\\main\\resources\\MP3";
    static String filename = null;

    @GetMapping("/product")
    public void productView(Model model){

        int id = 2;
        product = AccessToProductDB.selectOneFromDB(id);
        //AccessToProductDB.printProduct(product);

        String rank = String.format("%d", product.getId());
        String name = String.format("%s", product.getName());
        String price = String.format("%s", product.getPrice());
        //String link = String.format("%s", product.getLink()); //소스 링크 //추후 사용
        String pic = String.format("%s", product.getPic()); //상품 이미지 //추후 사용
        String allergy = String.format("%s", product.getAllergy());

        String stts_msg = rank + "번 제품의 이름은 " + name + "입니다. 가격은 " +
                price + "원 입니다. 알러지정보는 " + allergy + "입니다.";

        filename = dir_audio + "\\" + id + ".mp3";
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();

        System.out.println(stts_msg);
        model.addAttribute("productName", name);
        model.addAttribute("productLink", pic);
        model.addAttribute("productInfo", stts_msg);
        model.addAttribute("productPrice", price);

        //home 버튼 입력되면 return "redirect:/";
        //상품 선택 입력되면 return "homeplus"; //: 해당 product link 띄우기
    }

}
