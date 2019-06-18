package sms;

import java.util.Scanner;

public class SmsDemo {
    public static void main(String[] args) {
        Sms sms = new Sms ("Dzisiaj jest sobota, imieniny kota");
        Sms sms3 = new Sms ("Użytkownik wysyła jedną długą wiadomość zawierającą np. 760 znaków." +
                " Pierwotnie, aby napisać taki tekst należałoby 5-krotnie rozpoczynać nową wiadomość i wysłać 5 SMSów." +
                " Dzięki takim tzw. długim SMS piszemy wiadomość tworząc ją tylko raz a telefon nadawcy już sam dzieli" +
                " je w odpowiedni sposób, aby odbiorca otrzymał jedną długą wiadomość. &#8221;Długa wiadomość&#8221;" +
                " jest dzielona w następujący sposób:");
        Sms sms4 = new Sms ("Użytkownik wysyła jedną długą wiadomość zawierającą np. 760 znaków. " +
                "Pierwotnie, aby napisać taki tekst należałoby 5-krotnie rozpoczynać nową wiadomość i wysłać 5 SMSów." +
                " Dzięki takim tzw. długim SMS piszemy wiadomość tworząc ją tylko raz a telefon nadawcy już sam dzieli " +
                "je w odpowiedni sposób, aby odbiorca otrzymał jedną długą wiadomość. &#8221;Długa wiadomość&#8221;" +
                " jest dzielona w następujący sposób: pierwszy SMS zawiera 160 znaków 2 SMS - 145 3 SMS - 152 " +
                "kolejne części zawierają po 152 znaki Sprawcą zamieszania jest tryb UNICODE odpowiedzialny" +
                "za przesyłanie w SMS znaków narodowych (w wypadku języka polskiego są to następujące litery:" +
                " ąęćłńóśźż) pomiędzy użytkownikami telefonów komórkowych (wspomniane w artykule słowo miś" +
                " zawiera literę ś występującą tylko w j. polskim). Standard ten nie powstał z woli operatorów," +
                " lecz z woli Klientów. Telefon obsługujący tryb po napisaniu pierwszego znaku narodowego automatycznie" +
                "- standard GSM nie przewidywał takiego rozwiązania. Tekst widoczny na ekranie jest kodowany tak, aby" +
                        " znaki z ekranu nadawcy pojawiły się w ten sam sposób na wyświetlaczu komórki odbiorcy." +
                " Zdefiniowanie takiej informacji powoduje, że do tekstu dodawana jest paczka informacji - ona zawiera" +
                " 90 znaków - W efekcie, aby wysłać jedną     wiadomość z polskimi znakami w trybie UNICODE tekst nie powinien " +
                "przekroczyć 70 liter (90+70 = 160). Wiadomość dłuższa niż 70 znaków spowoduje, że zostanie " +
                "wysłany długi SMS, czyli z telefonu do sieci zostanie wysłana wielokrotność SMS w zależności" +
                " od ilości znaków w tekście.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Sms sms1 = new Sms(input);
    }
}
