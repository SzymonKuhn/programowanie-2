package detectLanguageApp;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;
import sun.util.locale.LanguageTag;

import javax.security.auth.callback.LanguageCallback;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// Enable secure mode (SSL) if passing sensitive information
// DetectLanguage.ssl = true;

// detectlanguage.com email: szymon.kuhn@gmail.com pssword: szymonkuhn
// API key: 88d55799b541807403e4f2c4a9a59c14

public class DetectLanguageApp {
    public static void main(String[] args) throws APIError, IOException {
        DetectLanguage.apiKey = "88d55799b541807403e4f2c4a9a59c14";
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Type in: ");
//        String input = scanner.nextLine();

//        String language = DetectLanguage.simpleDetect(input);
//        System.out.println("Language: " + language);

        Path basePath = Paths.get("D:\\LanguageDetectArticles");
//        File file = basePath.toFile();
          File[] files = basePath.toFile().listFiles();
//        Path file1 = basePath.resolve("chunichi.txt");
//        Path file2 = basePath.resolve("focus-1.txt");
//        Path file3 = basePath.resolve("focus-2.txt");
//        Path file4 = basePath.resolve("la-vanguardia.txt");
//        Path file5 = basePath.resolve("publico.txt");
//        Path file6 = basePath.resolve("zeit.txt");

//        List<Path> paths = Arrays.asList(file1, file2, file3, file4, file5, file6);

//        try (Reader reader = new BufferedReader(new FileReader(file1.toString()))) {
//            text = ((BufferedReader) reader).readLine();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
////        }
//        Scanner scannerFile1 = new Scanner(file1);
//        Scanner scannerFile2 = new Scanner(file2);
//        Scanner scannerFile3 = new Scanner(file3);
//        Scanner scannerFile4 = new Scanner(file4);
//        Scanner scannerFile5 = new Scanner(file5);
//        Scanner scannerFile6 = new Scanner(file6);
//        String text1 = scannerFile1.nextLine();
//        String text2 = scannerFile2.nextLine();
//        String text3 = scannerFile3.nextLine();
//        String text4 = scannerFile4.nextLine();
//        String text5 = scannerFile5.nextLine();
//        String text6 = scannerFile6.nextLine();

        List<String> texts = new ArrayList<>();

        //

        for (File file : files){
            Scanner scanner = new Scanner(file);
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()){
                text.append(scanner.nextLine());
            }
            texts.add(text.toString());

        }

        List<String> languages = new ArrayList<>();
        for (String text : texts) {
            languages.add(DetectLanguage.simpleDetect(text));
        }

        System.out.println(texts.toString());
        System.out.println(languages.toString());

//        File languagesFile = new File("languages.csv");
//        Scanner scannerOfLanguages = new Scanner(languagesFile);
//        System.out.println(scannerOfLanguages.nextLine());

//        Locale locale = new Locale("ko");
//        System.out.println(locale.getDisplayLanguage());

        List<String> countries = new ArrayList<>();
        for (String language : languages) {
            Locale locale = new Locale(language);
            countries.add(locale.getDisplayLanguage());
        }

        System.out.println(countries.toString());



    }

}
