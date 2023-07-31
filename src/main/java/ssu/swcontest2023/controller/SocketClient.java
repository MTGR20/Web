package ssu.swcontest2023.controller;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class SocketClient {

    // 수정해서 사용하기
//    static String host = "172.30.1.87";
    static String host = "127.0.0.1";
    static int port = 8000;

    static boolean isUsable = true;
    static Socket socket;
    static DataOutputStream dout;
    static DataInputStream din;

//    public static void main(String[] args) {
//
//            try{
//                Socket socket = new Socket("10.21.20.14",8000); //IPv4 값 수정해서 실행하세요
//
//                DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
//                DataInputStream din=new DataInputStream(socket.getInputStream());
//
//                dout.writeUTF(args[0]);
//                dout.flush();
//
//                // 로딩 음성 출력
//                if (!args[0].equals("0")) {
//                    System.out.println("loading message out");
//                    sleep(1000);
//                    String mp3 = "src/main/resources/MP3/voice2.mp3";
//                    MP3Player mp3Player = new MP3Player(mp3);
//                    mp3Player.play();
//                }
//
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
//            }
//
//            catch(Exception e){
//                e.printStackTrace();}
//
//        }

    // 소켓 연결하기
    public static void connect() throws IOException {
        System.out.println("SocketClient.connect()");

        socket = new Socket(host, port);
        dout = new DataOutputStream(socket.getOutputStream());
        din = new DataInputStream(socket.getInputStream());
    }

    // 소켓 연결 끊기
    public static void disconnect() throws IOException {
        System.out.println("SocketClient.disconnect()");

        dout.close();
        din.close();
        socket.close();
    }

    // 연결이 있다면 연결 끊기
    public static void check() throws IOException {

        if (socket == null) return;
        if (socket.isConnected()) disconnect();
        // while (socket.isConnected()) System.out.print(".");
    }

    // 메시지 보내기
    public static String sendMessage(String sendMessage) throws IOException {
        System.out.println("SocketClient.sendMessage(): " + sendMessage);

        dout.writeUTF(sendMessage);
        dout.flush();

        String receiveMessage = din.readUTF();//in.readLine();
        System.out.println("리턴 받은 메세지: " + receiveMessage);

        return receiveMessage;
    }
}
