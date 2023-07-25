package ssu.swcontest2023.controller;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.ArrayList;

public class SocketClient {
        public static void main(String[] args) {

            try{
                Socket socket = new Socket("172.30.1.87",8000); //IPv4 값 수정해서 실행하세요

                DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
                DataInputStream din=new DataInputStream(socket.getInputStream());

                dout.writeUTF(args[0]);
                dout.flush();

                //System.out.println("send first mess");
                String str = din.readUTF();//in.readLine();
                System.out.println("리턴 받은 메세지: "+str);

                dout.close();
                din.close();
                socket.close();

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

            catch(Exception e){
                e.printStackTrace();}

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
