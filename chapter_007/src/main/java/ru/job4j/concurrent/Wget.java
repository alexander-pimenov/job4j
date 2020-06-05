package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i <= 101; i++) {
                            if (i != 101) {
                                System.out.print("\rLoading : " + i + "%");
                            } else {
                                //Небольшая задержка перед выводом результата
                                //об успешной загрузке
                                Thread.sleep(1000);
                                System.out.print("\rLoaded successfully.");
                            }
                            Thread.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
