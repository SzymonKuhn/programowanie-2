package dataCollections;

import org.apache.commons.math3.primes.Primes;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DataCollectionsDemo {
    public static void main(String[] args) {


//        1) Wszystkie nazwiska o długości co najwyżej 4 znaków, zapisane wielkimi literami
        List<String> list = DataCollections.getSurnames().stream()
                .filter(p -> p.length() <= 4)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Wszystkie nazwiska o długości co najwyżej 4 znaków, zapisane wielkimi literami " + list);

//        2) Wszystkie nazwiska zaczynające się na literę 'B'
        List<String> list2 = DataCollections.getSurnames().stream()
                .filter(p -> p.startsWith("B"))
                .collect(Collectors.toList());
        System.out.println("Wszystkie nazwiska zaczynające się na literę 'B'" + list2);

//        3) Początkowe trzy litery wszystkich nazwisk, bez powtórzeń, z małych liter
        Set<String> list3 = DataCollections.getSurnames().stream()
                .map(p -> p.substring(0, 3).toLowerCase())
                .collect(Collectors.toSet());
        System.out.println("Początkowe trzy litery wszystkich nazwisk, bez powtórzeń, z małych liter " + list3);

//        4) 10 najdłuższych nazwisk, posortowanych malejąco według długości
        //        4*) Obsłuż miejsca "ex aequo"

        List<String> list4 = DataCollections.getSurnames().stream()
                .sorted((o1, o2) -> o2.length() - o1.length())
                .limit(10)
                .collect(Collectors.toList());
        Integer surnameLength = list4.get(9).length();

        List<String> list4a = DataCollections.getSurnames().stream()
                .sorted(Comparator.comparingInt(String::length))
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
        System.out.println("Odwróć kolejność liter we wszystkich nazwiskach i pozstaw jedynie te, które mają literę 'A' wsród pierwszych trzech liter (odwróconego nazwiska)" + list6);

//        7) Policz, ile jest nazwisk zaczynających się na każdą z liter alfabetu (rezultat jako
//        Map<Character, Integer>)   -> OK
        Map<Character, Integer> map7 = DataCollections.getSurnames().stream()
                .collect(Collectors.toMap(s -> s.charAt(0), s -> 1, (oldValue, newValue) -> oldValue + newValue));
        System.out.println("Policz, ile jest nazwisk zaczynających się na każdą z liter alfabetu " + map7);

//        8*) Jaka litera pojawia się najcześciej we wszystkich nazwiskach?
        Map<String, Integer> collect = DataCollections.getSurnames().stream()
                .map(String::toLowerCase)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(s -> s, s -> 1, (oldValue, NewValue) -> oldValue + 1));

        List<Map.Entry<String, Integer>> entries = collect.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(1)
                .collect(Collectors.toList());

        System.out.println("Mapa liter: " + collect);
        System.out.println("Najczęściej występująca litera: " + entries.toString());

        System.out.println("------------------------LICZBY-----------------------------");
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
        List<Integer> collect1 = DataCollections.getNumbers(100_000).stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Różnica między największą a najmniejszą liczbą: " + (collect1.get(collect1.size() - 1) - collect1.get(0))); //<- DA SIĘ INACZEJ?

//        5) Jaka jest średnia wszystkich liczb?
        OptionalDouble average = DataCollections.getNumbers(100_000).stream()
                .mapToInt(v -> v)
                .average();
        System.out.println("Średnia: " + average.getAsDouble());

//                6*) Jaka jest mediana wszystkich liczb?
        List<Integer> numbers = DataCollections.getNumbers(100_000);
        Integer size = numbers.size();
        OptionalDouble mediana = numbers.stream()
                .sorted()
                .skip((size - 1) / 2)
                .limit(size - ((size - 1)))
                .mapToInt(s -> s)
                .average();
        System.out.println("Mediana wszystkich liczb: " + (int)mediana.getAsDouble());


//                7*) Jaka cyfra pojawia się najczęściej we wszystkcih liczbach?
        List<String> listOfMostCommonNumbers = DataCollections.getNumbers(100_000).stream()
                .map(n -> n.toString().split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(n -> (String) n, n -> 1, (oldValue, newValue) -> oldValue + 1))
                .entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Jaka cyfra pojawia się najczęściej we wszystkcih liczbach " + listOfMostCommonNumbers);


//        8*) Ile jest wystąpień każdej cyfry (rezultat jako Map<Integer, Integer> z kluczami od 0 do 9)
        Map<Integer, Integer> mapOfIntegers = DataCollections.getNumbers(100_000).stream()
                .map(i -> i.toString().split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(Integer::parseInt, s -> 1, (oldValue, newValue) -> oldValue + 1));
        System.out.println("Wystąpień każdej cyfry " + mapOfIntegers);


//        9) Wypisz wszystkie liczby pierwsze, posortowane rosnąco
        List<Integer> list9 = DataCollections.getNumbers(100_000).stream()
                .filter(Primes::isPrime)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Wszystkie liczby pierwsze posortowane rosnąco: " + list9);


        System.out.println("-------------------Loremipsum------------------------");
//        Lorem ipsum:

//        1) Ile jest wszystkich słów?
        long count = DataCollections.getLoremIpsum().stream()
                .count();
        System.out.println("Ilość słów: " + count);


//        2) Ile słów zaczyna się na literę 'D'?
        long wordsStratsWithD = DataCollections.getLoremIpsum().stream()
                .map(String::toLowerCase)
                .filter(s -> s.substring(0, 1).equals("d"))
                .count();
//                .collect(Collectors.toList());
        System.out.println("Ilość wyrazów na \"D\": " + wordsStratsWithD);

//                3) Policz, ile jest słów o danej długości (Map<Integer, Integer>)
        Map<Integer, Integer> mapOfWordsLength = DataCollections.getLoremIpsum().stream()
                .collect(Collectors.toMap(String::length, s -> 1, (oldValue, newValue) -> oldValue + 1));
        System.out.println("słowa od danej długości: " + mapOfWordsLength);

//        4) Jaka litera pojawia się narzadziej?
        Map<String, Integer> mapOfLetters = DataCollections.getLoremIpsum().stream()
                .map(String::toLowerCase)
                .map(v -> v.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(v -> v, v -> 1, (oldValue, newValue) -> oldValue + 1));

        List<Map.Entry<String, Integer>> listEntries = mapOfLetters.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .limit(1)
                .collect(Collectors.toList());
        System.out.println("Jaka litera pojawia się narzadziej? " + listEntries);

//                5*) Ile jest słów, które posiadają dwie identyczne litery obok siebie (np. 'g' w "debugger")?
        List<String> listOfWordsWithDoubledLetters = DataCollections.getLoremIpsum().stream()
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^a-zęóąśłżćń]", ""))
                .filter(s -> {
                    for (int i = 0; i < s.length()-1; i++) {
                        if (s.substring(i, i + 1).equals(s.substring(i + 1, i + 2))) return true;
                    }
                    return false;
                })
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Ile jest słów, które posiadają dwie identyczne litery obok siebie " + listOfWordsWithDoubledLetters.size());
        System.out.println("Lista słów które posiadają dwie identyczne litery obok siebie " + listOfWordsWithDoubledLetters);


//        6**) Ile jest słów, które są palindromami?
        List<String> palindromList = DataCollections.getLoremIpsum().stream()
//                .filter(s -> s.length()>1)
                .map (String::toLowerCase)
                .map(s -> s.replaceAll("[^a-zęóąśłżćń]", ""))
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Wyrazy które są palindromami: " + palindromList);
        System.out.println("Ilość wyrazów które są palindromami: " + palindromList.size());

    }
}
