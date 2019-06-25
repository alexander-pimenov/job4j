package ru.job4j.basepatterns.behavioral.strategy.example2;


/**
 * Рассмотрим шаблон Strategy
 * Рассмотрим пример с сортировками массивов
 * Мы хотим сортировать массивы. Будем реализовывать различные алгоритмы сортировки
 * это клиентский класс StrategyApp, для проверки работы программы
 * Этот класс создает внутри себя как StrategyClient (Context), так и различные виды сортировки (активности)
 * и выполняет эти сортировки
 */
public class StrategyApp {
    public static void main(String[] args) {

        //первым делом создаем нашего клиента
        StrategyClient c = new StrategyClient();

        int[] arr0 = {1, 3, 2, 1, 0}; //создаем массив
        c.setSrategy(new SelectionSort()); //говорим нашему клиенту: прими эту стратегию
        c.executeStrategy(arr0); //выполнить нашу стратегию

        int[] arr1 = {11, 4, 2, 7, 8, 54};
        c.setSrategy(new InsertingSort());//устанавливаем StrategyClient определенную сортировку
        c.executeStrategy(arr1);

        int[] arr2 = {3, -8, 2, 0, 33, 1, 3, 2};
        c.setSrategy(new BubbleSort());
        c.executeStrategy(arr2);

    }
}

/*Ниже показано, что все отдельные внешние классы можно писать в одном месте!!!*/

////Context. Клиент
//class StrategyClient {
//    Sorting strategy; //у него, у клиента, есть ссылка на сортировку Sorting
//
//    public void setSrategy(Sorting strategy) { //сеттер
//        this.strategy = strategy;
//    }
//
//    /**
//     * executeStrategy вызывает метод sort нашего алгоритма нашей стратегии.
//     * передаем в него массив arr,который хотим отсортировать
//     */
//    public void executeStrategy(int[] arr) {
//        strategy.sort(arr);
//    }
//}

////Strategy. Этот интерфейс будет сортировать массив
//interface Sorting {
//    void sort(int[] arr);
//}

////Bubble sorting strategy (сортировка пузырьком). Первый алгоритм сортировки
////реализуем первую стратегию сортировки
//class BubbleSort implements Sorting {
//
//    @Override
//    public void sort(int[] arr) {
//        System.out.println("Сортировка пузырьком");
//        System.out.println("до:\t" + Arrays.toString(arr)); //Arrays.toString удобно располагает наш массив в виде строки
//        for (int barier = arr.length - 1; barier >= 0; barier--) {
//            for (int i = 0; i < barier; i++) {
//                if (arr[i] > arr[i + 1]) {
//                    int tmp = arr[i];
//                    arr[i] = arr[i + 1];
//                    arr[i + 1] = tmp;
//                }
//            }
//        }
//        System.out.println("после:\t" + Arrays.toString(arr));
//    }
//}

////Selection sorting strategy (Сортировка выборками)
////вторая стратегия сортировки
//class SelectionSort implements Sorting {
//
//    @Override
//    public void sort(int[] arr) {
//        System.out.println("Сортировка выборками");
//        System.out.println("до:\t" + Arrays.toString(arr));
//        for (int barier = 0; barier < arr.length - 1; barier++) {
//            for (int i = barier + 1; i < arr.length; i++) {
//                if (arr[i] < arr[barier]) {
//                    int tmp = arr[i];
//                    arr[i] = arr[barier];
//                    arr[barier] = tmp;
//                }
//            }
//        }
//        System.out.println("после:\t" + Arrays.toString(arr));
//    }
//}

////Inserting sorting strategy (Сортировка вставками)
////третья стратегия сортировки
//class InsertingSort implements Sorting {
//
//    @Override
//    public void sort(int[] arr) {
//        System.out.println("Сортировка вставками");
//        System.out.println("до:\t" + Arrays.toString(arr));
//        for (int barier = 1; barier < arr.length; barier++) {
//            int index = barier;
//            while (index - 1 >= 0 && arr[index] < arr[index - 1]) {
//                int tmp = arr[index];
//                arr[index] = arr[index - 1];
//                arr[index - 1] = tmp;
//                index--;
//            }
//        }
//        System.out.println("после:\t" + Arrays.toString(arr));
//    }
//}