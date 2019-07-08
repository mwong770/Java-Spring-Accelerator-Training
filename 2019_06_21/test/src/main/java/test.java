
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class test {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println(numbers);

        int lastIdx = numbers.size()-1;
        System.out.println(lastIdx);

        int value = numbers.get(lastIdx) + 1;
        System.out.println(value);


        numbers.set(lastIdx, value);

        System.out.println(numbers);
        numbers.add(4);
        numbers.set(1, 3);


    }
}
