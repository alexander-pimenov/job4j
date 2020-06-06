package ru.job4j.concurrent;

public class Wget2 {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 4; i <= 105; i++) {
                            if (i != 105) {
                                ////Loading ... |. Последний символ должен меняться: - \ | / - эффект вращающегося круга
                                if (i % 4 == 0) System.out.print("\rLoading : " + (i - 4) + "% " + "|");
                                if (i % 4 == 1) System.out.print("\rLoading : " + (i - 4) + "% " + "/");
                                if (i % 4 == 2) System.out.print("\rLoading : " + (i - 4) + "% " + "\u2014"); //длинное тире
                                if (i % 4 == 3) System.out.print("\rLoading : " + (i - 4) + "% " + "\\");

                            } else {
                                //Небольшая задержка перед выводом результата
                                //об успешной загрузке
                                Thread.sleep(1000);
                                System.out.print("\rLoaded successfully. " + "\u2764");
                            }
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
