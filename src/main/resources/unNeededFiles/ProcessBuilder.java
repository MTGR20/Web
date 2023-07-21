/*package ssu.swcontest2023;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilder {
    @RequestMapping(value="pythonbuilder")
    @ResponseBody
    public String pythonProcessbuilder() throws IOException, InterruptedException {
        System.out.println("pythonbuilder ");
        String argStr;
        int argInt;
        ProcessBuilder builder;
        BufferedReader br;

        argStr =  "C:\\Users\\jweun\\Spring\\sw-contest-2023\\src\\main\\resources\\python";
        argInt = 1;
        builder = new ProcessBuilder(argStr, argInt, ""); //python3 error

        builder.redirectErrorStream(true);
        Process process = builder.start();

        // 자식 프로세스가 종료될 때까지 기다림
        int exitval = process.waitFor();

        //// 서브 프로세스가 출력하는 내용을 받기 위해
        br = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(">>>  " + line); // 표준출력에 쓴다
        }

        if(exitval !=0){
            //비정상종료
            System.out.println("비정상종료");
        }

        return "pythonconnet";
    }
}


 */