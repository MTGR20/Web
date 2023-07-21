/*package ssu.swcontest2023.controller;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.Scanner;

public class ClientConnect {

    private Socket socket = null;
    private FileOutputStream fos = null;
    private DataInputStream din = null;
    private PrintStream pout = null;
    private Scanner scan = null;

    public ClientConnect(String customSocketIp, int portNumber) throws IOException {
        socket=new Socket(customSocketIp, portNumber);
        scan = new Scanner(System.in);
        din = new DataInputStream(socket.getInputStream());
        pout = new PrintStream(socket.getOutputStream());
    }

    public void send(String msg) throws IOException {
        byte[] data = msg.getBytes();
        ByteBuffer b = ByteBuffer.allocate(4);
        //최초의 4바이트는 데이터 크기
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putInt(data.length);
        pout.write(b.array(), 0, 4);
        pout.print(msg);
        pout.flush();
    }

    public String recv() throws IOException {
        byte[] bytes = new byte[1024];
        din.read(bytes);
        String reply = new String(bytes, "UTF-8");
        return reply;
    }

    public void closeConnections() throws IOException {
        // Clean up when a connection is ended
        socket.close();
        din.close();
        pout.close();
        scan.close();
    }

    // Request a specific file from the server
    public String getFile(String path, String jsonStr) {
        Random random1 = new Random();
        long currentTime1 = System.currentTimeMillis();
        int randomValue1 = random1.nextInt(100);

        String newFileName = Long.toString(currentTime1) + "_"+randomValue1+"_mycustom.hwp";
        try {
            File file = new File(path, newFileName);
            // Create new file if it does not exist
            // Then request the file from server
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            //보낼 데이터의 크기를 먼저 보낸다.
            //데이터를 보낸다.
            send(jsonStr);

            // Get content in bytes and write to a file
            byte[] buffer = new byte[8192];
            for(int counter=0; (counter = din.read(buffer, 0, buffer.length)) >= 0;) {
                fos.write(buffer, 0, counter);
            }
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

}

 */