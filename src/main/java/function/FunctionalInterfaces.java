package function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = new BiConsumer<>() {
            @Override
            public void accept(Integer index, String str) {
                map.put(index, str);
            }
        };
        biCon.accept(1, "one");
        biCon.accept(2, "two");
        biCon.accept(3, "three");
        biCon.accept(4, "four");
        biCon.accept(5, "five");
        biCon.accept(6, "six");
        biCon.accept(7, "seven");

        BiPredicate<Integer, String> biPred = new BiPredicate<>() {
            @Override
            public boolean test(Integer index, String str) {
                return index % 2 == 0 || str.length() == 4;
            }
        };
        for (Integer i : map.keySet()) {
            if (biPred.test(i, map.get(i))) {
                System.out.println("key: " + i + " value: " + map.get(i));
            }
        }
        Supplier<List<String>> sup = new Supplier<>() {
            @Override
            public List<String> get() {
                return new ArrayList<>(map.values());
            }
        };
        List<String> strings = sup.get();
        Consumer<String> con = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Function<String, String> func = new Function<>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        for (String s : strings) {
            con.accept(func.apply(s));
        }
    }
}
