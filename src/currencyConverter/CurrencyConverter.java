package currencyConverter;

import com.google.gson.Gson;
import org.apache.commons.math3.util.Precision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.*;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {

        LocalDate now = LocalDate.now().minusDays(1);
        LocalDate monthAgo = now.minusMonths(1);
        List<LocalDate> dates = Arrays.asList(now, monthAgo);

        Currency dollar = Currency.getInstance("USD");
        Currency euro = Currency.getInstance("EUR");
        Currency funt = Currency.getInstance("GBP");
        Currency frank = Currency.getInstance("CHF");
        List<Currency> currencies = Arrays.asList(dollar, euro, funt, frank);

        //list of CurrencyNBP based on currency and dates
        ArrayList<CurrencyNBP> currencyNBPS = new ArrayList<>();
        for (Currency currency : currencies) {
            for (LocalDate date : dates) {
                currencyNBPS.add(getCurrencyNBP(currency, date));
            }
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

        for (Currency currency : currencies) {
            double inputInPLN = 100.00;
            double outputInValue;
            double outputInPLN;
            double difference;
            CurrencyNBP currencyNBPNow = getCurrencyNBP(currency, now);
            CurrencyNBP currencyNBPMonthAgo = getCurrencyNBP(currency, monthAgo);
            outputInValue = currencyNBPMonthAgo.getCurrencyFromPLN(inputInPLN);
            outputInPLN = currencyNBPNow.getPLNFromCurrency(outputInValue);
            difference = Precision.round(outputInPLN - inputInPLN, 2);
            System.out.printf("Wymieniając %s złotych dnia %s otrzymałeś %s %s. Wymieniając dzisiaj tę kwotę na" +
                    " złote otrzymasz %s. Różnica wynosi %s %n%n", inputInPLN,
                    monthAgo.toString(), outputInValue, currency.getCurrencyCode(), outputInPLN, difference);
            }
        }

    private static URL getURL(Currency currency, LocalDate date)
            throws MalformedURLException {
        //    http://api.nbp.pl/api/exchangerates/rates/a/usd/2016-04-04/?format=json
        String root = "http://api.nbp.pl/api/exchangerates/rates/c/";
        String format = "/?format=json";
        StringBuilder adress = new StringBuilder(root);
        adress.append(currency.getCurrencyCode() + "/" + date + format);
        return new URL(adress.toString());
    }

    private static CurrencyNBP getCurrencyNBP(Currency currency, LocalDate date) throws IOException {
        InputStream input = null;
        do {
            try {
                URL url = getURL (currency, date);
                URLConnection connection = url.openConnection();
                input = connection.getInputStream();
            } catch (FileNotFoundException fnfe) {
                date = date.minusDays(1);
            }
        }while (input == null);
        Scanner scanner = new Scanner(input);
        String jsonString = scanner.nextLine();
        Gson gson = new Gson();
        return gson.fromJson(jsonString, CurrencyNBP.class);
    }
}
