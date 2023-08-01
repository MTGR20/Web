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

    int id = 0;

    @GetMapping("/product")
    public void productView(Model model){

        //SocketClient.main(sendAr); //OCR 아껴야해서 일단 빼둘게요

        productList = AccessToProductDBRepository.selectListFromDB();
        setProductInfo(model);

    }

    @RequestMapping("/buynext")
    public String buynext(Model model) throws IOException, InterruptedException {

        System.out.println("buynext on!");
        System.out.println("id:" + id+1);

        id++;
        setProductInfo(model);

        System.out.println(filename);
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();

        String mp3 = "src/main/resources/MP3/opt.mp3";
        mp3Player = new MP3Player(mp3);
        mp3Player.play();

        String _mp3 = "src/main/resources/MP3/MP_Pling.mp3";
        MP3Player _mp3Player = new MP3Player(_mp3);
        _mp3Player.play();
        sleep(2000);

        return "productSlide";
    }

    @GetMapping("/productSlide")
    public String temp1(Model model) throws IOException, InterruptedException {
        System.out.println("id:" + id+1);

        id++;
        setProductInfo(model);

        System.out.println(filename);
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();

        String _mp3 = "src/main/resources/MP3/MP_Pling.mp3";
        MP3Player _mp3Player = new MP3Player(_mp3);
        _mp3Player.play();
        sleep(2000);

        return "product2";
    }


    @RequestMapping("/product2")
    public String temp2(Model model) throws IOException {

        setProductInfo(model);
        return "redirect:/productSlide";
    }

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

        String msg = "알러지정보는 " + allergy + "입니다.";
        filename = dir_audio + "\\" + id + ".mp3";

        System.out.println(stts_msg);
        //model.addAttribute("productInfo", stts_msg);
        model.addAttribute("productInfo", msg);
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

    }
}
