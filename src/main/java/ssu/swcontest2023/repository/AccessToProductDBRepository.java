package ssu.swcontest2023.repository;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.util.ArrayList;

public class AccessToProductDBRepository {

    static ProductServiceSQL db = new ProductServiceSQL();

    public static ArrayList<Product> selectListFromDB() {
        return db.selectAll();
    }

    public static Product selectOneFromDB(int id){
        return db.selectById(id);
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
