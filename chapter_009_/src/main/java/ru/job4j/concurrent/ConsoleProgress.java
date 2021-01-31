package ru.job4j.concurrent;

/*В данной программе показано прерывание потока методом interrupt() из метода main*/
public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        /*В этом try-catch мы ловим прерывание потока thread
         * из main метода, поймав его мы проваливаемся в
         * блок catch, выводим сообщение "Loaded."
         * и поток завершает свою работу.
         * Если этот блок не использовать, то прерывание из метода
         * main мы можем услышать используя блок условия if() и
         * с помощью break: выйти из цикла, тем самым завершив работу
         * потока */
        int index = 0;
        char[] process = {'\\', '|', '/', '\u2014'}; //'\u2014' - длиное тире
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading... " + process[index % 4]);
                Thread.sleep(500);
                index++;
            }
        } catch (InterruptedException e) {
            System.out.println("\rLoaded.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}