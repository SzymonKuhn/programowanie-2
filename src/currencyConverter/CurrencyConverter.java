package currencyConverter;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.*;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        //    http://api.nbp.pl/api/exchangerates/rates/a/usd/2016-04-04/?format=json
        String root = "http://api.nbp.pl/api/exchangerates/rates/c/";
        String format = "/?format=json";
        LocalDate now = LocalDate.now();
        LocalDate monthAgo = now.minusMonths(2); // <- 1 sprzed 1 miesiąca wywala wyjątek "file not found exception...???
        List<LocalDate> dates = Arrays.asList(now, monthAgo);
        Currency dollar = Currency.getInstance("USD");
        Currency euro = Currency.getInstance("EUR");
        Currency funt = Currency.getInstance("GBP");
        Currency frank = Currency.getInstance("CHF");
        List<Currency> currencies = Arrays.asList(dollar, euro, funt, frank);
        ArrayList<URL> urls = new ArrayList<>();
        ArrayList<CurrencyNBP> currencyNBPS = new ArrayList<>();

        //list of URL based on currency and dates
        for (Currency currency : currencies) {
            for (LocalDate date : dates) {
                urls.add(getURL(root, currency, date, format));
            }
        }

        //list of CurrencyNBPS based on URL
        for (URL url : urls) {
            currencyNBPS.add(getCurrencyNBP(url));
        }

        //printing currencies for every currency and date
        for (CurrencyNBP currency : currencyNBPS) {
            String currencyName = currency.getCurrency();
            String code = currency.getCode();
            LocalDate date = currency.getEffectiveDate();
            double bid = currency.getBid();
            double ask = currency.getAsk();
            System.out.printf("Kurs sprzedaży waluty %S na dzień %s wynosi %s złotego %n", currencyName, date, bid);
            System.out.printf("Kurs kupna waluty %S na dzień %s wynosi %s %n %n", currencyName, date, ask);
            //printing value of 100 PLN in currency
            if (date.equals(now)) {
                System.out.printf("Kupując dzisiaj walutę %S za 100 złotych otrzymasz %s %s %n %n", currencyName,
                        currency.getCurrencyFromPLN(100), code);
            }
        }




//        System.out.println(now);
//        System.out.println(monthAgo);
//        System.out.println(dollar.getCurrencyCode());
//        System.out.println(euro.getCurrencyCode());
//        System.out.println(funt.getCurrencyCode());
//        System.out.println(frank.getCurrencyCode());
//        System.out.println(urls);
//        System.out.println(currencyNBPS);


    }

    private static URL getURL(String root, Currency currency, LocalDate date, String format)
            throws MalformedURLException {
        StringBuilder adress = new StringBuilder(root);
        adress.append(currency.getCurrencyCode() + "/" + date + format);
        return new URL(adress.toString());
    }

    private static CurrencyNBP getCurrencyNBP(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();
        Scanner scanner = new Scanner(input);
        String jsonString = scanner.nextLine();
//        System.out.println(jsonString);
        Gson gson = new Gson();
        return gson.fromJson(jsonString, CurrencyNBP.class);
    }


}
