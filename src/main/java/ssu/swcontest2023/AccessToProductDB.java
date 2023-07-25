package ssu.swcontest2023;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.util.ArrayList;

public class AccessToProductDB {
    public static void main() {

        //socket server (python) -> TABLE 생성해두었다는 가정 하에 DB 접근해서 사용할 예정

        //SQL TEST
        ProductServiceSQL p = new ProductServiceSQL();
        ArrayList<Product> list = p.selectAll();
        System.out.println("[Print All]");
        for (Product product : list) {
            printProduct(product);
        }
        System.out.println();

        Product product = p.selectById(1);
        System.out.println("[Print One]");
        printProduct(product);
    }


    public static void printProduct(Product product){
        String id = String.format("%-3d ", product.getId());
        String name = String.format("%-25s ", product.getName());
        String price = String.format("%-10s ", product.getPrice());
        String link = String.format("%-100s ", product.getLink());
        String src_link = String.format("%100s ", product.getSrc_link());
        System.out.println(id + name + price + link + src_link);
    }
}
