import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.print("Введите выражение через пробелы: ");
        String input = scan.nextLine();
        System.out.println("Ответ: " + calc(input));
    }

    public static String calc (String input) throws IOException {

        String[] parts = input.split(" ");

        if (parts.length != 3)
            throw new IOException("Неверный ввод");

        int num1, num2 = 0, ansA = 110;
        String sign = parts[1];

        if (!isArabic(parts[0]))
            num1 = goArabic(parts[0]);
        else
            num1 = Integer.parseInt(parts[0]);

        if (!isArabic(parts[2]) && !isArabic(parts[0]))
            num2 = goArabic(parts[2]);
        else if (isArabic(parts[2]) && isArabic(parts[0]))
            num2 = Integer.parseInt(parts[2]);
        else
            throw new IOException("Разные типы чисел");

        if (num1 < 1 || num2 < 1 ||
                num1 > 10 || num2 > 10)
            throw new IOException("Число вне диапазона");

        switch (sign) {
            case "+" -> ansA = num1 + num2;
            case "-" -> ansA = num1 - num2;
            case "*" -> ansA = num1 * num2;
            case "/" -> ansA = num1 / num2;
            default -> throw new IOException("Неверный знак");
        }

        if (isArabic(parts[0]))
           return Integer.toString(ansA);

        if (ansA < 1)
            throw new IOException("Ответ не может быть записан римскими числами");
        return goRoman(ansA);
    }

    public static int goArabic (String numR) throws IOException {

        return switch (numR) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IOException("Неверное число или не число");
        };
    }

    public static String goRoman (int ansA) {

        String ansR = "";
        int a = ansA / 10;
        int b = ansA % 10;

        if (a == 10)
            ansR += "C";
        if (a == 9 || a == 4)
            ansR += "X";
        if (a == 9)
            ansR += "C";
        if (a >= 4 && a<= 8)
            ansR += "L";

        if (a == 1 || a == 6)
            ansR += "X";
        if (a == 2 || a == 7)
            ansR += "XX";
        if (a == 3 || a == 8)
            ansR += "XXX";

        if (b == 9 || b == 4)
            ansR += "I";
        if (b == 9)
            ansR += "X";
        if (b >= 4 && b <= 8)
            ansR += "V";

        if (b == 1 || b == 6)
            ansR += "I";
        if (b == 2 || b == 7)
            ansR += "II";
        if (b == 3 || b == 8)
            ansR += "III";

        return ansR;
    }

    public static boolean isArabic (String num) {

        try {
            int i = Integer.parseInt(num.trim());
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

