/**
 * 2. Дробь
 * Даны два числа: a и b. Найдите значение числа a/b, записанного в k-ичной системе счисления.
 * Если a/b — периодическая дробь, то период следует заключить в скобки.
 *
 * Пример входных данных:
 * 1 2 8
 * 1 12 10
 *
 * Пример выходных данных:
 * 0.4
 * 0.08(3)
 * */
public class Task2 {

    public static void main(String... args) {

        Task2 instance = new Task2();

        /*
        x - the dividend - делимое
        y - the divisor  - делитель
            quotient - частное
            modulus - остаток
            integer and fractional - целая и дробная
        */
        double a = 1;
        double b = 2;
        int k = 8;

        System.out.println(instance.convertToScale(a / b, k));
    }

    /*
     * Перевод десятичного числа в другую систему счисления
     */
    private String convertToScale(double number, int scale) {
        //для перевода отрицательных чисел учитываем знак
        String sign = number < 0 ? "-" : "";
        number = Math.abs(number);
        return sign + integerPartConvert((int)number, scale) +
                fractionalPartConvert(number - (int)number, scale);
    }

    /*
     * Перевод целой части числа.
     * Для этого целая часть десятичного числа делится на основание новой системы счисления. Цифра,
     * полученная в остатке, есть последняя цифра k-ичной записи данного числа. Полученное
     * частное снова делится на k. Остаток будет предпоследней цифрой k-ичной записи числа, а
     * частное опять делится на k и т.д. Процесс деления прекращается, когда в частном получается
     * цифра, меньшая k, которая будет первой цифрой k-ичной записи данного числа.
     */
    private String integerPartConvert(int number, int scale) {
        StringBuilder result = new StringBuilder();
        while (number >= scale) {
            result.insert(0, number % scale);
            number /= scale;
        }
        result.insert(0, number);
        return result.toString();
    }

    /*
     * Перевод дробной части числа.
     * Дробная часть десятичного числа умножается на основание новой системы счисления.
     * Целая часть полученного произведения является первой цифрой в k-ичной записи
     * дробной части числа. Далее умножаем на k дробную часть этого произведения, целая часть
     * результата будет второй цифрой дробной части и т.д.
     */
    private String fractionalPartConvert(double number, int scale) {
        String result = "";
        double x;
        while (number != 0) {
            x = number * scale;
            result += (int)x;
            number = x - (int)x;
        }
        return result.equals("") ? result : "." + result;
    }
}
