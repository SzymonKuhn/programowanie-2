package dataCollections;

import org.apache.commons.math3.primes.Primes;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCollectionsDemo {
    public static void main(String[] args) {


//        1) Wszystkie nazwiska o długości co najwyżej 4 znaków, zapisane wielkimi literami ->OK
        List<String> list = DataCollections.getSurnames().stream()
                .filter(p -> p.length() <= 4)
                .map(p -> p.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(list);

//        2) Wszystkie nazwiska zaczynające się na literę 'B'       -> OK
        List<String> list2 = DataCollections.getSurnames().stream()
                .filter(p -> p.startsWith("B"))
                .collect(Collectors.toList());
        System.out.println(list2);

//        3) Początkowe trzy litery wszystkich nazwisk, bez powtórzeń, z małych liter  -> OK
        Set<String> list3 = DataCollections.getSurnames().stream()
                .map(p -> p.substring(0, 3).toLowerCase())
                .collect(Collectors.toSet());
        System.out.println(list3);


//        4) 10 najdłuższych nazwisk, posortowanych malejąco według długości        -> OK
        //        4*) Obsłuż miejsca "ex aequo"                                     -> OK

        List<String> list4 = DataCollections.getSurnames().stream()
                .sorted((o1, o2) -> o2.length() - o1.length())
                .limit(10)
                .collect(Collectors.toList());
        Integer surnameLength = list4.get(9).length();

        List<String> list4a = DataCollections.getSurnames().stream()
                .sorted((o1, o2) -> o1.length() - o2.length())
                .filter(o1 -> o1.length() >= surnameLength)
                .collect(Collectors.toList());

        System.out.println("10 najdłuższych nazwisk, posortowanych malejąco według długości z ex aequo" + list4a);


//        5) 20 najkrótszych nazwisk, posortowanych według ostatniej litery  -> OK
        List<String> list5 = DataCollections.getSurnames().stream()
                .sorted((o1, o2) -> o1.length() - o2.length())
                .limit(20)
                .sorted(Comparator.comparingInt(o -> o.charAt(o.length() - 1)))
                .collect(Collectors.toList());
        System.out.println("20 najkrótszych nazwisk, posortowanych według ostatniej litery " + list5);

//        6) Odwróć kolejność liter we wszystkich nazwiskach i pozstaw jedynie te, które mają literę 'A' wsród pierwszych trzech liter (odwróconego nazwiska)
        List<String> list6 = DataCollections.getSurnames().stream()
                .map(p -> new StringBuilder(p).reverse().toString())
                .filter(p -> p.substring(0, 3).toLowerCase().contains("a"))
                .collect(Collectors.toList());
        System.out.println(list6);

//        7) Policz, ile jest nazwisk zaczynających się na każdą z liter alfabetu (rezultat jako
//        Map<Character, Integer>)   -> OK
        Map<Character, Integer> map7 = DataCollections.getSurnames().stream()
                .collect(Collectors.toMap(s -> s.charAt(0), s -> 1, (oldValue, newValue) -> oldValue + newValue));
        System.out.println(map7);

//        8*) Jaka litera pojawia się najcześciej we wszystkich nazwiskach?
//        DataCollections.getSurnames().stream()
//                .map(s -> s.toLowerCase())
//                .map(s -> s.toCharArray())
//                .flatMap()
//
//                .collect()


//        1) Ile jest liczb parzystych?
        long num1 = DataCollections.getNumbers(100_000).stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("ilość liczb parzystych: " + num1);

//        2) Ile jest liczb pięciocyfrowych?
        long num2 = DataCollections.getNumbers(100_000).stream()
                .filter(n -> n > 9_999 && n < 100_000)
                .count();
        System.out.println("Ilość liczb pięciocyfrowych: " + num2);

//        3) Jaka jest największa i najmniejsza liczba?
        Optional<Integer> min = DataCollections.getNumbers(100_000).stream()
                .min(Integer::compareTo);
        Optional<Integer> max = DataCollections.getNumbers(100_000).stream()
                .max(Integer::compareTo);
        System.out.println("Najmniejsza liczba: " + min.get());
        System.out.println("Największa liczba: " + max.get());


//        4) Jaka jest różnica między największa a najmniejszą liczbą?
        DataCollections.getNumbers(100_000).stream()
        ;

//        5) Jaka jest średnia wszystkich liczb?
        OptionalDouble average = DataCollections.getNumbers(100_000).stream()
                .mapToInt(v -> v)
                .average();
        System.out.println("Średnia: " + average.getAsDouble());

//                6*) Jaka jest mediana wszystkich liczb?
//        DataCollections.getNumbers(100_000).stream()
//                .sorted()
//                .

//                7*) Jaka cyfra pojawia się najczęściej we wszystkcih liczbach?

//        8*) Ile jest wystąpień każdej cyfry (rezultat jako Map<Integer, Integer> z kluczami od 0 do 9)

//        9) Wypisz wszystkie liczby pierwsze, posortowane rosnąco
        List<Integer> list9 = DataCollections.getNumbers(100_000).stream()
                .filter(Primes::isPrime)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Wszystkie liczby pierwsze posortowane rosnąco: " + list9);


//        Lorem ipsum:

//        1) Ile jest wszystkich słów?      -> OK
        long count = DataCollections.getLoremIpsum().stream()
                .count();
        System.out.println("Ilość słów: " + count);


//        2) Ile słów zaczyna się na literę 'D'?        -> OK
        long wordsStratsWithD = DataCollections.getLoremIpsum().stream()
                .filter(s -> s.substring(0, 1).equals("D"))
                .count();
//                .collect(Collectors.toList());
        System.out.println("Ilość wyrazów na \"D\": " + wordsStratsWithD);

//                3) Policz, ile jest słów o danej długości (Map<Integer, Integer>)     -> OK
        Map<Integer, Integer> mapOfWordsLength = DataCollections.getLoremIpsum().stream()
                .collect(Collectors.toMap(s -> s.length(), s -> 1, (oldValue, newValue) -> oldValue + 1));
        System.out.println("słowa od danej długości: " + mapOfWordsLength);

//        4) Jaka litera pojawia się narzadziej?
//        List<Character> collect = DataCollections.getLoremIpsum().stream()
//                .map(String::toCharArray)
//                .flatMap(chars -> chars.
//                .collect(Collectors.toList());

//                5*) Ile jest słów, które posiadają dwie identyczne litery obok siebie (np. 'g' w "debugger")?

//        6**) Ile jest słów, które są palindromami?


    }
}
