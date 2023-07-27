package ssu.swcontest2023.controller;

import ssu.swcontest2023.domain.Product;
import ssu.swcontest2023.sevice.ProductServiceSQL;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class SocketClient {
        public static void main(String[] args) {

            try{
//                Socket socket = new Socket("172.30.1.87",8000); //IPv4 값 수정해서 실행하세요
                Socket socket = new Socket("127.0.0.1",8000);

                DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
                DataInputStream din=new DataInputStream(socket.getInputStream());

                dout.writeUTF(args[0]);
                dout.flush();

                // 로딩 음성 출력
                sleep(1000);
                String mp3 = "src/main/resources/MP3/voice2.mp3";
                MP3Player mp3Player = new MP3Player(mp3);
                mp3Player.play();

                //System.out.println("send first mess");
                String str = din.readUTF();//in.readLine();
                System.out.println("리턴 받은 메세지: "+str);

                dout.close();
                din.close();
                socket.close();
            }

            catch(Exception e){
                e.printStackTrace();}

        }
}
