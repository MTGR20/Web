package ssu.swcontest2023;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.util.ArrayList;

public class AccessToProductDB {
    public static ArrayList<Product> selectListFromDB() {
        ProductServiceSQL db = new ProductServiceSQL();
        ArrayList<Product> list = db.selectAll();        //productList: view에서 이용!
        return list;
    }

    public static Product selectOneFromDB(int id){
        ProductServiceSQL db = new ProductServiceSQL();
        Product product = db.selectById(id);
        return product;
    }

    public static void printProductList(ArrayList<Product> list){
        System.out.println("[Print All]");
        for (Product product : list) {
            printProduct(product);
        }
    }


    public static void printProduct(Product product){
        String id = String.format("%-3d ", product.getId());
        String name = String.format("%-25s ", product.getName());
        String price = String.format("%-10s ", product.getPrice());
        String link = String.format("%-100s ", product.getLink());
        String pic = String.format("%-100s ", product.getPic());
        String src_link = String.format("%100s ", product.getSrc_link());
        String allergy = String.format("%-100s ", product.getAllergy());
        System.out.println(id + name + price + link + pic + src_link + allergy);
    }
}
