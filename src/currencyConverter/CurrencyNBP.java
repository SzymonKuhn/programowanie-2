package currencyConverter;

import org.apache.commons.math3.util.Precision;
import java.time.LocalDate;
import java.util.ArrayList;

public class CurrencyNBP {

    private String currency;
    private String code;
    private String table;
    ArrayList<Data> rates;

    @Override
    public String toString() {
        return "CurrencyNBP{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", table='" + table + '\'' +
                ", bid='" + getBid() + '\'' +
                ", ask='" + getAsk() + '\'' +
                ", effectiveDate='" + getEffectiveDate().toString() + '\'' +
                '}';
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public String getTable() {
        return table;
    }

    public double getBid() {
        return rates.get(0).getBid();
    }

    public double getAsk() {
        return rates.get(0).getAsk();
    }

    public LocalDate getEffectiveDate() {
        return LocalDate.parse(rates.get(0).getEffectiveDate());
    }

    private class Data {
        private String effectiveDate;
        private double bid;
        private double ask;

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public double getBid() {
            return bid;
        }

        public double getAsk() {
            return ask;
        }
    }
    public double getCurrencyFromPLN (double valueInPLN) {

        return Precision.round(valueInPLN / getAsk(), 2);
    }

    public double getPLNFromCurrency (double valueInCurrency) {
        return Precision.round(valueInCurrency * getBid(), 2);
    }
}
