package multithreading.task3;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculatorCallable implements Callable<Long> {

    private int[] integers;

    CalculatorCallable(List<Integer> integers){
        this.integers = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            this.integers[i] = integers.get(i);
        }
    }

    @Override
    public Long call() throws Exception {
        long totalSum = 0;
        for (Integer num: integers
        ) {
            totalSum += num;
        }
        return totalSum;
    }
}
