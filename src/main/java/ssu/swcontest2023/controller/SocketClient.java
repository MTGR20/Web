package ssu.swcontest2023.controller;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SocketClient {

    static Socket socket;
    static DataOutputStream dout;
    static DataInputStream din;
    static String errorMessage = "fail to get key";

//    public static void main(String[] args) {
//>>>>>>> tmp
//
//    public static String host = "10.21.20.17";
//    public static Socket socket;
//    public static DataOutputStream dout;
//    public static DataInputStream din;
//    public static int portNo = 8000;
//
//    public static void main(String[] args) {
//
//        try {
//            connect(portNo);
//
//            dout.writeUTF(args[0]);
//            dout.flush();
//
//<<<<<<< HEAD
//            // 로딩 음성 출력
//            if (!args[0].equals("0")) {
//                System.out.println("loading message out");
//                sleep(1000);
//                String mp3 = "src/main/resources/MP3/voice2.mp3";
//                MP3Player mp3Player = new MP3Player(mp3);
//                mp3Player.play();
//=======
//                //System.out.println("send first mess");
//                String str = din.readUTF();//in.readLine();
//                System.out.println("리턴 받은 메세지: "+ str);
//
//                // 검색된 상품이 없는 경우
//                if (str.equals("")) {
//                    // 처리 구현
//                }
//
//                dout.close();
//                din.close();
//                socket.close();
//>>>>>>> tmp
//            }
//
//            //System.out.println("send first mess");
//            String str = din.readUTF();//in.readLine();
//            System.out.println("리턴 받은 메세지: " + str);
//
//            disconnect();
//        }
//<<<<<<< HEAD
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void connect(int port) throws IOException {
//        socket = new Socket(host, port); //IPv4 값 수정해서 실행하세요
//        dout = new DataOutputStream(socket.getOutputStream());
//        din = new DataInputStream(socket.getInputStream());
//        System.out.println("port: "+port);
//    }
//
//    public static void disconnect() throws IOException {
//        dout.close();
//        din.close();
//        socket.close();
//    }
//
//    /*
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
//
//     */
//=======

    public static void connect() throws IOException {
        //                Socket socket = new Socket("172.30.1.87",8000); //IPv4 값 수정해서 실행하세요
        socket = new Socket("127.0.0.1",8000);
        dout = new DataOutputStream(socket.getOutputStream());
        din = new DataInputStream(socket.getInputStream());
    }

    public static void disconnect() throws IOException {
        System.out.println("socket disconnect");
        dout.close();
        din.close();
        socket.close();
    }

    public static String sendForRecord(String[] args) throws IOException, InterruptedException {
        dout.writeUTF(args[0]);
        dout.flush();

        //System.out.println("send first mess");
        String str = din.readUTF();//in.readLine();
        System.out.println("리턴 받은 메세지: "+str);

        if (str.equals(errorMessage)) return errorMessage;
        // 검색된 상품이 없는 경우
        else if (str.equals("")) searchNothing();

        return str;
    }

    public static void sendForKeyword(String[] args) throws IOException, InterruptedException {
        dout.writeUTF(args[0]);
        dout.flush();

//            // 로딩 음성 출력
//            System.out.println("loading message out");
//            sleep(1000);
//            String mp3 = "src/main/resources/MP3/voice2.mp3";
//            MP3Player mp3Player = new MP3Player(mp3);
//            mp3Player.play();

        //System.out.println("send first mess");
        String str = din.readUTF();//in.readLine();
        System.out.println("리턴 받은 메세지: "+str);

        // 검색된 상품이 없는 경우
        if (str.equals("")) searchNothing();
    }

    public static void searchNothing() {
        // 검색된 상품이 없는 경우 처리 구현
        System.out.println("검색된 상품이 없습니다.");
    }
}
