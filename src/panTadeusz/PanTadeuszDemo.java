package panTadeusz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class PanTadeuszDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("resources\\pan-tadeusz.txt");
        PanTadeusz panTadeusz = new PanTadeusz();

        List<String> listOfWords = panTadeusz.createListOfWords(file);
        System.out.println(listOfWords.toString());
        System.out.println("Number of words: " + listOfWords.size());

        Map<String, Integer> mapOfWords = panTadeusz.createMapOfWords(listOfWords);
        System.out.println(mapOfWords.toString());
        System.out.println("Number od words in map: " + mapOfWords.size());
        Map<Integer, List<String>> mapOfNumbers = panTadeusz.createMapOfNumbers(mapOfWords);
        System.out.println(mapOfNumbers);
        System.out.println("Number of numbers: " + mapOfNumbers.size());
    }
}
