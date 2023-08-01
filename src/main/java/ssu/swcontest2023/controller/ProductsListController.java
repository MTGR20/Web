package ssu.swcontest2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssu.swcontest2023.repository.AccessToProductDBRepository;
import ssu.swcontest2023.domain.Product;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;
import static ssu.swcontest2023.controller.SocketClient.disconnect;

@Controller
public class ProductsListController {

    //static String dir_audio = "/Users/leechanho8511/PycharmProjects/ssu_merge/audio";
    static String dir_audio = "C:\\Users\\jweun\\PycharmProjects\\swContest2023\\audio";
    static String filename = null;

    String[] sendAr = null;

    ArrayList<Product> productList;
    Product product;
    //Product product1, product2, product3, product4;

    int id = 0;


    static String mp3 = "src/main/resources/MP3/opt.mp3";
    static MP3Player mp3Player = new MP3Player(mp3);

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

        productList = AccessToProductDBRepository.selectListFromDB();
        setProductInfo(model);

    }

    @RequestMapping("/buynext")
    public String buynext(Model model) throws IOException {
        System.out.println("buynext on!");

        System.out.println("id:" + id);
        id++;
        setProductInfo(model);

        mp3Player.play();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "productSlide";
    }

    @GetMapping("/productSlide")
    public String temp1(Model model) throws IOException {
        System.out.println("id:" + id);

        id++;
        setProductInfo(model);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "product2";
    }


    @RequestMapping("/product2")
    public String temp2(Model model) throws IOException {
        System.out.println("id:" + id);

        id--;
        setProductInfo(model);

        // 음성 출력
        mp3Player.play();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/productSlide";
    }


//    @GetMapping("/product/{id}")
//    public String temp(@PathVariable int id){
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        String str = "다음"; // TEST
//
//        if (str.equals("구매")) {
//            String link = String.format("%s", product.getLink()); //소스 링크 //마지막 링크 전달 시 사용
//            return "redirect:" + link;
//        }
//        else if (str.equals("다음")) {
//            id++;
//        }
//        else if (str.equals("이전")){
//            id--;
//        }
//        return "product/"+id;
//    }




    /*
    @RequestMapping("/buynext")
    public String buynext(Model model) throws IOException {

        System.out.println("buynext on!");

        int port = SocketClient.portNo;
        port++;
        SocketClient.connect(port);
        System.out.println("portNo: "+port);

        SocketClient.dout.writeUTF("구매 의사");
        SocketClient.dout.flush();

        // 음성 출력
        String mp3 = "src/main/resources/MP3/yes_or_no.mp3";
        MP3Player mp3Player = new MP3Player(mp3);
        mp3Player.play();

        String str = SocketClient.din.readUTF();//in.readLine();
        SocketClient.disconnect();

        System.out.println("구매 의사 받기: " + str);
        str = "다음"; // TEST

        if (str.equals("구매")) {
            String link = String.format("%s", product.getLink()); //소스 링크 //마지막 링크 전달 시 사용
            return "redirect:" + link;
        }
        else if (str.equals("다음")) {
            id++;
        }
        else if (str.equals("이전")){
            id--;
        }

        System.out.println("id: "+id);

        setProductInfo(model);
        return "redirect:/buynext";
        //return "redirect:/product/" + id;
    }


     */

    //@PutMapping("/productSlide/{id}")
    //public String setProductInfo(@PathVariable int id, Model model){
    //@GetMapping("/product/{id}")
    public void setProductInfo(Model model){

        product = productList.get(id);

        String rank = String.format("%d", product.getId());
        String name = String.format("%s", product.getName());
        String price = String.format("%s", product.getPrice());
        //String link = String.format("%s", product.getLink());
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

        //return "redirect:/buynext";
    }
}
