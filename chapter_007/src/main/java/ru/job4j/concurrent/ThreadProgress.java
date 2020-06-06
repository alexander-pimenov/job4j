package ru.job4j.concurrent;

public class ThreadProgress {
    public static void main(String[] args) {

        Thread progress = new Thread(new ConsoleProgress());
        progress.start();

        Thread progress2 = new Thread(new ConsoleProgress());
        progress2.start();

        Thread thread = new Thread(
                () -> {
                    for (int i = 0; i < 300; i++) {
                        System.out.println("Hello from " + Thread.currentThread().getName() + " " + i);
                    }
                }
        );
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //progress.interrupt();

    }
}

class ConsoleProgress implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("Hello from " + Thread.currentThread().getName() + " " + i);
        }
    }
}

//class ConsoleProgress extends Thread {
//
//    //переопределяем метод run()
//    //пишем наш код
//    @Override
//    public void run() {
////        while (!Thread.currentThread().isInterrupted()){
////
////        }
//        for (int i = 0; i < 200; i++) {
//            System.out.println("Hello from " + Thread.currentThread().getName() + " " + i);
//        }
//
//    }
////
////    public static void main(String[] args) {
////        Thread progress = new Thread(
////                new Runnable() {
////                    @Override
////                    public void run() {
////                        while (!Thread.currentThread().isInterrupted()) {
//////                            try {
//////                                Thread.sleep(500);
//////                            } catch (InterruptedException e) {
//////                                e.printStackTrace();
//////                            }
////                            //Loading ... |. Последний символ должен меняться: - \ | /.
////                            System.out.print("\r load: " + "|");
////                            System.out.print("\r load: " + "/ ");
////                            System.out.print("\r load: " + "\u2014");
////                            System.out.print("\r load: " + "\\");
////
////                        }
////                    }
////                });
////        progress.start();
////        try {
////            Thread.sleep(1);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        progress.interrupt();
////
////    }
//}
