package sms;

public class Sms {
    private String messageOutput;
    private int numOfChars;
    private int numOfSMS;

    public Sms(String messageInput) {
        this.messageOutput = compressMessage(messageInput);
        numOfChars = messageOutput.length();
        System.out.println(messageOutput);
        System.out.println("Number of chars: " + numOfChars);
        numOfSMS = numOfSms(numOfChars);
        System.out.println("Number of sms's: " + numOfSMS);
    }



    private String compressMessage (String input) {
        StringBuilder result = new StringBuilder();
        String[] array = input.split(" ");
        for (String word : array) {
            if (!word.equals(".") && !word.equals("")) {

//                word.replaceAll("a", "b");
                String wordWithoutDots = word.replace(".","");
                result.append(upperCaseForFirstLetter(wordWithoutDots));
            }
        }
        return result.toString();
    }

    public String getMessageOutput() {
        return messageOutput;
    }

    public int getNumOfSMS() {
        return numOfSMS;
    }

    private String upperCaseForFirstLetter (String input){
        StringBuilder result = new StringBuilder();
        String uppedFirstLetter = input.substring(0,1).toUpperCase();
        String rest = input.substring(1,input.length());
        result.append(uppedFirstLetter);
        result.append(rest);
        return result.toString();
    }

    private int numOfSms (int numOfChars) {
        if (numOfChars <= 160)  {
            return 1;
        } else if (numOfChars <= 305) {
            return 2;
        } else {
            int restOfChars = numOfChars - 305;
             return 2+ (restOfChars / 152) + 1;
        }
    }
}
