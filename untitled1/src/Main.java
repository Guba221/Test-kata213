import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            System.out.println("Некорректный формат ввода");
            return;
        }

        int a, b;
        try {
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            a = RomanNumeral.convert(tokens[0]);
            b = RomanNumeral.convert(tokens[2]);
            if (a == -1 || b == -1) {
                System.out.println("Некорректный формат ввода");
                return;
            }
        }

        char operator = tokens[1].charAt(0);
        int result;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                System.out.println("Некорректный оператор");
                return;
        }

        String output = (a < 0 || b < 0) ? String.valueOf(RomanNumeral.convert(String.valueOf(result))) : Integer.toString(result);
        System.out.println(output);
    }
}

class RomanNumeral {
    private static final String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] decimalValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static int convert(String romanNumeral) {
        romanNumeral = romanNumeral.toUpperCase();
        int decimalValue = 0;
        int i = 0;
        while (i < romanNumeral.length()) {
            char currentChar = romanNumeral.charAt(i);
            int currentValue = getValue(currentChar);
            if (currentValue == -1) {
                return -1;
            }
            i++;
            if (i == romanNumeral.length()) {
                decimalValue += currentValue;
            } else {
                char nextChar = romanNumeral.charAt(i);
                int nextValue = getValue(nextChar);
                if (nextValue == -1) {
                    return -1;
                }
                if (nextValue > currentValue) {
                    decimalValue += nextValue - currentValue;
                    i++;
                } else {
                    decimalValue += currentValue;
                }
            }
        }
        return decimalValue;
    }

    private static int getValue(char romanNumeral) {
        switch (romanNumeral) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }
}
