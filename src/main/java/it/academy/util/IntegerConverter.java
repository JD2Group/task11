package it.academy.util;

public class IntegerConverter {

    private IntegerConverter() {
    }

    public static Integer convertToInt(String string) {

        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }

    }
}
