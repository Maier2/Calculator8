import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        CalculateClass calc = new CalculateClass();


        ////   распознование операци


        String read = scan.nextLine();
        if (read.contains("+")) {
            read = read.replace(" ", "");
            calc.dataInput = read.split("\\+");
            calc.sign = '+';
        } else if (read.contains("-")) {
            read = read.replace(" ", "");
            calc.dataInput = read.split("-");
            calc.sign = '-';
        } else if (read.contains("*")) {
            read = read.replace(" ", "");
            calc.dataInput = read.split("\\*");
            calc.sign = '*';
        } else if (read.contains("/")) {
            read = read.replace(" ", "");

            calc.dataInput = read.split("/");
            calc.sign = '/';
        } else {
            throw new Exception("Ошибка: не является математической операцией.");                                       //// ОШИБКА: с введением не матиматической операции ( одна цифра без знака )
        }


        ///////ПЕРЕКЛЮЧАТЕЛЬ КАЛЬКУЛЯТОРА
        if (calc.dataInput.length <= 1) {
            throw new Exception("Ошибка: нехватает операнда.");                                                         //// ОШИБКА: нехватает операнда
        } else if (calc.dataInput.length > 1) {
            boolean numDetect = calc.calcDetector(calc.dataInput[0]);                                                // переклуючатель
            boolean numDetect2 = calc.calcDetector(calc.dataInput[1]);
            if (calc.dataInput.length > 2) {
                if (calc.dataInput[0].equals("")) {
                    throw new Exception("Ошибка: нехватает операнда.");                                                 ////ОШИБКА: не хватает операнда
                } else {
                    throw new Exception("Ошибка: слишком много операндов.");                                            //// ОШИБКА: слишком много операндов
                }
            } else if ((numDetect != true) && (numDetect2 == true) || (numDetect == true) && (numDetect2 != true)) {
                if (calc.dataInput[0].equals("")) {
                    throw new Exception("Ошибка: нехватает операнда.");                                                 //// ОШИБКА: не хватает первого операнда
                } else {
                    throw new Exception("Ошибка: разные системы счисления.");                                           //// ОШИБКА: разные чистемы счисления
                }


            } else if ((numDetect == false) && (numDetect2 == false)) {
                calc.roman();                                                                                        // вход в римский калькулятор
            } else if ((numDetect == true) && (numDetect2 == true)) {
                calc.arab(Integer.valueOf(calc.dataInput[0]), Integer.valueOf(calc.dataInput[1]));                   // вход в арабский калькулятор
            }
        }

    }
}


class CalculateClass {
    int number;                // первое значение
    int number2;             // второе значение
    char sign;                 // знак
    int exit;                  // ответ
    boolean bool;
    String[] dataInput = null;
    String[] data2 = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


    //метод переключателя калькулятора
    boolean calcDetector(String dataInput1) {
        try {
            Integer.parseInt(dataInput1);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    ///////////////////арабский калькулятор
    void arab(int number, int number2) throws Exception {
        this.number = number;
        this.number2 = number2;
        if ((number > 10) || (number2 > 10)) {                                                                          ////ОШИБКА: число больше 10
            throw new Exception("ОШИБКА: число является больше 10, числа принимаются от 1 до 10.");
            //System.exit(0);
        } else if ((number < 1) || (number2 < 1)) {
            throw new Exception("ОШИБКА: число является меньше чем 1, числа принимаются от 1 до 10.");                  ////ОШИБКА: -1+1 ( ВВЕДЕНО ОТРИЦАТЕЛЬНОЕ ЧИСЛО)
        } else if ((number <= 10) && (number2 <= 10)) {                                                              // 1.2) вход в араб. калькулятор.
            if (sign == '+') {
                exit = number + number2;
                System.out.println(number + "+" + number2 + "=" + exit);
            } else if (sign == '-') {
                exit = number - number2;
                System.out.println(number + "-" + number2 + "=" + exit);
            } else if (sign == '*') {
                exit = number * number2;
                System.out.println(number + "*" + number2 + "=" + exit);
            } else if (sign == '/') {
                exit = number / number2;
                System.out.println(number + "/" + number2 + "=" + exit);

            }
        }

    }


    ///////////////////КАЛЬКУЛЯТОР РИМСКИХ ЧИСЕЛ
    void roman() throws Exception {

        for (int i = 0; i <= data2.length - 1; i++) {                                                                // 2.1) Инициализация рим. чисел
            if (dataInput[0].equals(data2[i])) {
                number = i;
            }
            if (dataInput[1].equals(data2[i])) {
                number2 = i;
            }
        }


        if ((number > 10) || (number2 > 10)) {                                                                          ////ОШИБКА: число больше 10
            throw new Exception("ОШИБКА: число является больше 10, числа принимаются от 1 до 10.");


        } else if ((number == 0) || (number2 == 0)) {
            if (dataInput[0].equals("")) {
                throw new Exception("Ошибка: нехватает операнда.");                                                     //// ОШИБКА: не хватает первого операнда
            } else {
                throw new Exception("не является математической операцией.");                                           //// ОШИБКА: не является матиматической операцией
            }


        } else if ((number <= 10) && (number2 <= 10)) {                                                              // 2.2) римский калькуляор
            if (sign == '+') {
                exit = number + number2;
                System.out.println(dataInput[0] + "+" + dataInput[1] + "=" + data2[exit]);


            } else if (sign == '-') {
                exit = number - number2;
                if (exit <= 0) {
                    throw new Exception("ОШИБКА: в римской системе нет отрицательных чисел.");                          ////ОШИБКА: В римской системе нет отрицательных чисел
                } else if ((number >= 0) && (number2 >= 0)) {
                    System.out.println(dataInput[0] + "-" + dataInput[1] + "=" + data2[exit]);


                }
            } else if (sign == '*') {
                exit = number * number2;
                System.out.println(dataInput[0] + "*" + dataInput[1] + "=" + data2[exit]);


            } else if (sign == '/') {
                exit = number / number2;
                if (exit <= 0) {
                    throw new Exception("ОШИБКА: в римской системе нет отрицательных чисел.");                          ////ОШИБКА: В римской системе нет отрицательных чисел

                } else if ((number >= 0) && (number2 >= 0)) {
                    System.out.println(dataInput[0] + "/" + dataInput[1] + "=" + data2[exit]);


                }
            }
        }


    }
}