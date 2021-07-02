import java.util.Scanner;

public class RomanArabicCalculator {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите операцию по принципу Число Пробел Знак Операции Пробел Число. Можно вводить числа от 1 до 10 римскими или арабскими");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        boolean check = arabicRomanSolve(str);
        String[] strToArray = str.split(" ");
        String op = strToArray[1];
        if (strToArray.length != 3) throw new Exception("Некорректный ввод данных");

        if (check){
            if (op.equals("+")) System.out.println(roman(decode(strToArray[0]) + (decode(strToArray[2]))));
            if (op.equals("-")) System.out.println(roman(decode(strToArray[0]) - (decode(strToArray[2]))));
            if (op.equals("*")) System.out.println(roman(decode(strToArray[0]) * (decode(strToArray[2]))));
            if (op.equals("/")) System.out.println(roman(decode(strToArray[0]) / (decode(strToArray[2]))));
        } else {
            int a = Integer.parseInt(strToArray[0]);
            int b = Integer.parseInt(strToArray[2]);
            System.out.println(arabicCalculator(op, a, b));
        }
    }

    private static int arabicCalculator(String operation, int number1, int number2) throws Exception {

        int result = 0;
        if (number1 < 0 || number1 > 10 || number2 < 0 || number2 > 10) throw new Exception("Число должно быть от 1 до 10");
        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
        }
        return result;
    }

    private static boolean arabicRomanSolve(String equation) throws Exception {
        String romPat = "IVXLC";
        if (romPat.indexOf(equation.charAt(0)) != -1 && !equation.matches(".*\\d+.*"))
            return true;  //"Только римские цифры"
        if (romPat.indexOf(equation.charAt(0)) == -1 && equation.matches(".*\\d+.*"))
            return false;  //"Только арабские цифры"
        if (romPat.indexOf(equation.charAt(0)) != -1 && equation.matches(".*\\d+.*"))
            throw new Exception("Невозможная комбинация, используйте только римские или только арабские цифры");
        return false;
    }

    private static int decodeSingle(char letter) {
        switch (letter) {

            case 'M': return 1000;
            case 'D': return 500;
            case 'C': return 100;
            case 'L': return 50;
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default: return 0;

        }
    }

    private static int decode(String roman) throws Exception {
        int result = 0;
        String uRoman = roman.toUpperCase();
        for (int i = 0; i < uRoman.length() - 1; i++) {
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i + 1))) {
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                result += decodeSingle(uRoman.charAt(i));
            }
        }
        result += decodeSingle(uRoman.charAt(uRoman.length() - 1));
        if (result < 0 || result > 10) throw new Exception("Число должно быть от 1 до 10");
            return result;
    }

    private static String roman(int input) throws Exception {

        StringBuilder s = new StringBuilder();

        if (input < 1) throw new Exception ("ОТРИЦАТЕЛЬНОЕ ЧИСЛО");

        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }
}