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

    public static String host = "10.21.20.17";
    public static Socket socket;
    public static DataOutputStream dout;
    public static DataInputStream din;
    public static int portNo = 8000;

    public static void main(String[] args) {

        try {
            connect(portNo);

            dout.writeUTF(args[0]);
            dout.flush();

            // 로딩 음성 출력
            if (!args[0].equals("0")) {
                System.out.println("loading message out");
                sleep(1000);
                String mp3 = "src/main/resources/MP3/voice2.mp3";
                MP3Player mp3Player = new MP3Player(mp3);
                mp3Player.play();
            }

            //System.out.println("send first mess");
            String str = din.readUTF();//in.readLine();
            System.out.println("리턴 받은 메세지: " + str);

            disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void connect(int port) throws IOException {
        socket = new Socket(host, port); //IPv4 값 수정해서 실행하세요
        dout = new DataOutputStream(socket.getOutputStream());
        din = new DataInputStream(socket.getInputStream());
        System.out.println("port: "+port);
    }

    public static void disconnect() throws IOException {
        dout.close();
        din.close();
        socket.close();
    }

    /*
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

     */
}
