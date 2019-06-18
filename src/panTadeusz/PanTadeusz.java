package panTadeusz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PanTadeusz {

    public List<String> createListOfWords (File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> result = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] array = line.split(" ");
            for (String word : array) {
                if (word.isEmpty()) continue;
                result.add(cleanWord(word));
            }
        }
        return result;
    }

    public Map<String, Integer> createMapOfWords(List<String> listOfWords) {
        Map<String, Integer> mapOfNumbresOfWords = new HashMap<>();
        for (String word: listOfWords) {
//            mapOfNumbresOfWords.putIfAbsent(word, 1);
            if (mapOfNumbresOfWords.containsKey(word)) {
//                Integer num = mapOfNumbresOfWords.get(word);
                mapOfNumbresOfWords.replace(word, (mapOfNumbresOfWords.get(word)+1));
            } else {
                mapOfNumbresOfWords.put(word, 1);
            }
        }
        return mapOfNumbresOfWords;
    }



//    public List<String> cleanListOfWords (List<String> inputList){
//        List<String> wordsToRemove = Arrays.asList(",", ".", "-");
//        inputList.removeAll()
//
//    }

    private String cleanWord (String word) {
        word = word.toLowerCase();
        word = word.replaceAll("[^a-zęóąśłżźćń]", "");
        return word;
//        return word.replaceAll("!", "").replaceAll(":", "")
//                .replaceAll(",", "").replace(".", "")
//                .replaceAll(";", "").replace("?", "")
//                .replace("(", "").replace(")", "")
//                .toLowerCase();
    }

    public Map<Integer, List<String>> createMapOfNumbers (Map<String, Integer> mapOfWords) {
        Map<Integer, List<String>> mapOfNumbers = new TreeMap<>();
        Set<String> setOfWords = mapOfWords.keySet();

        for (String word : setOfWords) {
            Integer number = mapOfWords.get(word);
            if (mapOfNumbers.containsKey(number)) {
                mapOfNumbers.get(number).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                mapOfNumbers.put(number, list);
            }
        }

        return mapOfNumbers;
    }



}
