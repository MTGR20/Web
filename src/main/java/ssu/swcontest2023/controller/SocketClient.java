package ssu.swcontest2023.controller;

import java.io.*;
import java.net.*;
import java.lang.*;

public class SocketClient {
        public static void main(String[] args) {

            try{
                Socket socket=new Socket("IPv4 값 찾아서 수정",8000);

                DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
                DataInputStream din=new DataInputStream(socket.getInputStream());

                dout.writeUTF(args[0]);
                dout.flush();

                System.out.println("send first mess");
                String str = din.readUTF();//in.readLine();

                System.out.println("Message "+str);


                dout.close();
                din.close();
                socket.close();
            }

            catch(Exception e){
                e.printStackTrace();}

        }
}
