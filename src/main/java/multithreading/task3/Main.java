package multithreading.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final int SIZE = 1_000_000_00;
    private static final int MAX_NUM = 1000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long totalSum = 0;
        int[] myBigArray = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            myBigArray[i] = (int)(Math.random() * MAX_NUM);
        }

        long tBeg = System.currentTimeMillis();
        for (int num: myBigArray
             ) {
            totalSum += num;
        }
        long tSpend;
        tSpend = System.currentTimeMillis() - tBeg;
        System.out.println("totalSum = " + totalSum + " calculated in " + tSpend + " mls");

        tBeg = System.currentTimeMillis();
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        ExecutorService calculate = Executors.newFixedThreadPool(availableProcessors);
        List<Integer> spareMyArray = new ArrayList<>();
        List<CalculatorCallable> callableList = new ArrayList<>();
        int idx = SIZE/availableProcessors;
        int multiplier = 0;
        for (int i = 0; i < SIZE; i++) {
            if (i <= idx * multiplier || multiplier == availableProcessors){
                spareMyArray.add(myBigArray[i]);
            } else {
                callableList.add(new CalculatorCallable(spareMyArray));
                spareMyArray.clear();
                spareMyArray.add(myBigArray[i]);
                multiplier++;
            }
        }
        callableList.add(new CalculatorCallable(spareMyArray));
        List<Future<Long>> answers = calculate.invokeAll(callableList);
        totalSum = 0;
        for (Future<Long> answerRecFuture : answers) {
            System.out.println(answerRecFuture.get());
            totalSum += answerRecFuture.get();
        }
        tSpend = System.currentTimeMillis() - tBeg;
        System.out.println("totalSum = " + totalSum + " calculated in " + tSpend + " mls");
        calculate.shutdown();
    }
}
