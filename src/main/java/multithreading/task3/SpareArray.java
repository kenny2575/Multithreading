package multithreading.task3;

import java.util.ArrayList;
import java.util.List;

public class SpareArray {
    private List<Integer> list;

    public SpareArray(){
        list = new ArrayList<>();
    }

    public List<Integer> getList() {
        return list;
    }

    public void add(int num) {
        if (this.list == null) {
            list = new ArrayList<>();
        }
        list.add(num);
    }
}
