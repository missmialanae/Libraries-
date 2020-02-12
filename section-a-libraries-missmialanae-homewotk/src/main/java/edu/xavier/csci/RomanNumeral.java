package edu.xavier.csci;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RomanNumeral{
    private Map<String, String> mappings;
    private Stack<Tuple> encodings;

    class Tuple {
        public Integer value;
        public String letter;

        Tuple(Integer v, String s) {
            value = v;
            letter = s;
        }
    }

    public RomanNumeral() {

        mappings = Stream.of(
                new SimpleEntry<>("I", "1"),
                new SimpleEntry<>("V", "5"),
                new SimpleEntry<>("X", "10"),
                new SimpleEntry<>("L", "50"),
                new SimpleEntry<>("C", "100"),
                new SimpleEntry<>("D", "500"),
                new SimpleEntry<>("M", "1000")

        )
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

        encodings = new Stack<>();
        encodings.addAll(Stream.of(
                new Tuple(1, "I"),
                new Tuple(4, "IV"),
                new Tuple(5, "V"),
                new Tuple(9, "IX"),
                new Tuple(10, "X"),
                new Tuple(40, "XL"),
                new Tuple(50, "L"),
                new Tuple(100, "C"),
                new Tuple(400, "CD"),
                new Tuple(500, "D"),
                new Tuple(1000, "M")
        ).collect(Collectors.toList()));

    }

    public String process(int current, Stack<Tuple> stack, String ret) {
        Tuple t = stack.pop();
        int temp = current;
        while (temp > 0 && temp / t.value > 0) {
            ret += t.letter;
            temp -= t.value;
        }
        if(temp == 0) return ret;
        return process(temp, stack, ret);
    }

    public String encode(int value) {

        Stack<Tuple> stack = (Stack)encodings.clone();
        return process(value,stack, "");
    }

    public Integer decode(String romanNumeral) {
        String[] charList = romanNumeral.split("");
        List<String> characters = Arrays.asList(charList);
        Collections.reverse(characters);
        List<Integer> results = new ArrayList<>();
        results.add(0);
        results.add(0);

        characters.stream().map(val -> Integer.valueOf(mappings.get(((String) val).toUpperCase())))
                .forEach(val -> {
                    results.set(0, Math.max(results.get(0), val));
                    if (val >= results.get(0)) {
                        int runningTotal = results.get(1) + val;
                        results.set(1, runningTotal);
                    } else {
                        int runningTotal = results.get(1) - val;
                        results.set(1, runningTotal);
                    }

                });


        return results.get(1);
    }


}// end Roman Numeral Class
