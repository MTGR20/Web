package ssu.swcontest2023.controller;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

class MP3Player {
    private final String mp3FileToPlay;
    private Player jlPlayer;

    // 테스트
//    public static void main(String[] args) throws Exception {
//
//        String filename = "src/main/resources/MP3/sample.mp3";    // 파일 경로
//        MP3Player mp3Player = new MP3Player(filename);
//        mp3Player.play();                                          // 재생
//
//        // "stop" 입력 시, 재생 종료
////        Scanner sc = new Scanner(System.in);
////        System.out.println("Write stop to stop the music: ");
////        if (sc.nextLine().equalsIgnoreCase("stop")) {
////            mp3Player.close();
////        }
//
//        // Enter 입력 시, 재생 종료
//        int keycode = System.in.read();
//        if (keycode > 0) mp3Player.close();
//
////        addKeyListener(new KeyListener() {
////            @Override
////            public void keyTyped(KeyEvent e) { }
////            @Override
////            public void keyPressed(KeyEvent e) { mp3Player.close(); }
////            @Override
////            public void keyReleased(KeyEvent e) { }
////        });
////        this.setFoucusable(true);
////        this.requestFocus();
//    }

    public MP3Player(String mp3FileToPlay) {
        this.mp3FileToPlay = mp3FileToPlay;
    }

    public void play() {
        try {
            FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }

        new Thread() {
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();

    }

    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}