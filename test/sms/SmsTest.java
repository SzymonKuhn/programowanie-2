package sms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsTest {

    @Test
    void shouldRemoveAllSpacesAndDots() {
        //Given
        Sms sms = new Sms("Ala ma kota.");

        //When
        String result = sms.getMessageOutput();

        //Then
        assertEquals("AlaMaKota", result);
    }

    @Test
    void shouldGetNumberOfSms() {
        //Given
        Sms sms1 = new Sms ("Ala ma kotka.");
        Sms sms2 = new Sms ("ala ma kota i bardzo go kocha, a mietek ma psa i ten esemes jest już tak długi" +
                "że powinien mieęc ponad 160 znaków ale jeszcze trochę trzeba dopisać żeby były dwa smsy, ale dużo tych znaków piszę i pis");
        Sms sms3 = new Sms ("");

        //When
        int numOfSms1 = sms1.getNumOfSMS();
        int numOfSms2 = sms2.getNumOfSMS();
        int numOfSms3 = sms3.getNumOfSMS();

        //Then
        assertEquals(1, numOfSms1);
        assertEquals(2, numOfSms2);
        assertEquals(1, numOfSms3);
    }

}