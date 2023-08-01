package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssu.swcontest2023.AccessToProductDB;
import ssu.swcontest2023.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ProductsListController {

    String[] sendAr = null;

    ArrayList<Product> productList;
    Product product;
    Product product1, product2, product3, product4;

    @PostMapping("/search")
    public String search(@RequestParam("name") String name) {

        sendAr = new String[]{name};
        if (name.isBlank()){
            sendAr = new String[]{"0"};
        }

        return "redirect:/product";
    }
    @GetMapping("/product")
    public void productView(Model model){

        System.out.println("send msg: " + Arrays.toString(sendAr));
        //SocketClient.main(sendAr); //OCR 아껴야해서 일단 빼둘게요

        int id = 0;
        productList = AccessToProductDB.selectListFromDB();
        setProductInfo(model, id);

        /*//TEST
        //다음 상품으로 넘기면 id 변경 후 ...
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        setProductInfo(model, id+1);
         */

        //상품 선택 입력되면
        // return "homeplus"; //: 해당 product link 띄우기
    }

    public void setProductInfo(Model model, int id){

        product = productList.get(id);

        String rank = String.format("%d", product.getId());
        String name = String.format("%s", product.getName());
        String price = String.format("%s", product.getPrice());
        //String link = String.format("%s", product.getLink()); //소스 링크 //마지막 링크 전달 시 사용
        String pic = String.format("%s", product.getPic());
        String allergy = String.format("%s", product.getAllergy());

        String stts_msg = rank + "번 제품의 이름은 " + name + "입니다. 가격은 " +
                price + "원 입니다. 알러지정보는 " + allergy + "입니다.";

        System.out.println(stts_msg);
        model.addAttribute("productInfo", stts_msg);
        model.addAttribute("productName", name);
        model.addAttribute("productPic", pic);
        model.addAttribute("productPrice", price);

        for (int i=1; i<=4; i++){
            product = productList.get(id+i);
            name = String.format("%s", product.getName());
            price = String.format("%s", product.getPrice());
            pic = String.format("%s", product.getPic());
            model.addAttribute("productName"+i, name);
            model.addAttribute("productPic"+i, pic);
            model.addAttribute("productPrice"+i, price);
        }
        /*
        //1번
        product1 = productList.get(id+1);
        String name1 = String.format("%s", product1.getName());
        String price1 = String.format("%s", product1.getPrice());
        String pic1 = String.format("%s", product1.getPic());
        model.addAttribute("productName1", name1);
        model.addAttribute("productPic1", pic1);
        model.addAttribute("productPrice1", price1);

        //2번
        product2 = productList.get(id+2);
        String name2 = String.format("%s", product2.getName());
        String price2 = String.format("%s", product2.getPrice());
        String pic2 = String.format("%s", product2.getPic());
        model.addAttribute("productName2", name2);
        model.addAttribute("productPic2", pic2);
        model.addAttribute("productPrice2", price2);

        //3번
        product3 = productList.get(id+3);
        String name3 = String.format("%s", product3.getName());
        String price3 = String.format("%s", product3.getPrice());
        String pic3 = String.format("%s", product3.getPic());
        model.addAttribute("productName3", name3);
        model.addAttribute("productPic3", pic3);
        model.addAttribute("productPrice3", price3);

        //4번
        product4 = productList.get(id+4);
        String name4 = String.format("%s", product4.getName());
        String price4 = String.format("%s", product4.getPrice());
        String pic4 = String.format("%s", product4.getPic());
        model.addAttribute("productName4", name4);
        model.addAttribute("productPic4", pic4);
        model.addAttribute("productPrice4", price4);
*/
    }
}
