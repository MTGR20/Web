/*package ssu.swcontest2023;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Java에서 Python 실행하기
// 참고: https://yookeun.github.io/java/2020/07/12/java-call-python/
public class CallMain {

    //private static String sourceFile = "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\python";
    //private static String sourceFile = "src/main/resources/python/GetProductInfo.py";
    private static String sourceFile = "src/main/resources/python/test2.py";
    public static void main(String[] args)  {
        System.out.println("Python Call");
        String[] command = new String[4];
        command[0] = "python";
        command[1] = sourceFile;
        command[2] = Integer.toString(10);
        command[3] = "20";
        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
///*
        for (int i=-1; i<3; i++){
            System.out.println("Python Call");
            String[] command = new String[4];
            command[0] = "python";
            command[1] = sourceFile;
            command[2] = Integer.toString(i);
            try {
                execPython(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

 //
    }

    public static void execPython(String[] command) throws IOException, InterruptedException {
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        //System.out.println("result: " + result);
        //System.out.println("output: " + outputStream.toString());
        System.out.println();

    }

}


*/