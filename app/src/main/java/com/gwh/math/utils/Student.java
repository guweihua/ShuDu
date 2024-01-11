package com.gwh.math.utils;

import com.blankj.utilcode.util.LogUtils;

import java.util.*;

public class Student {

    /**
     * 存储表达式
     */
    private static String strTest = "";
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Integer number_random;

    public static void main(int i,int i2) {
//        System.out.print("请输入参与数字的最大值（建议33）：");
//        number_random = SCANNER.nextInt();
        number_random = i;
//        System.out.print("请输入要生成题目数量：");
//        getExpression(SCANNER.nextInt());
        //getExpression(i2);
    }

    /**
     * 计算公约数（在下面getExpression方法里面已经排除 0 ）
     */
    public static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * 获取随机运算符
     */
    public static char getOperator() {
        char[] signal = {'+', '-', '*', '/'};
        return signal[RANDOM.nextInt(4)];
    }


    /**
     * 生成表达式
     */
    public static String getExpression(int numberRandom,int getNums) {

        List<String>  allData = new ArrayList<>();

        number_random = numberRandom;
//        System.out.print("请输入要生成题目数量：");
//        getExpression(SCANNER.nextInt());


        char operator1, operator2;
        int num1, num2, num3;
        while (getNums > 0) {
            boolean flag = false;
            getNums--;
            operator1 = getOperator();
            operator2 = getOperator();
            //保证两个不同的运算符
            while (operator1 == operator2) {
                operator1 = getOperator();
                operator2 = getOperator();
            }
            // + 1 是避免出现 0
            num1 = RANDOM.nextInt(number_random) + 1;
            num2 = RANDOM.nextInt(number_random) + 1;
            num3 = RANDOM.nextInt(number_random) + 1;

            //以下判断只是为了初步判断是否出现小数和负数
            if (operator1 == '/') {
                if (gcd(num1, num2) != num2) {
                    flag = true;
                }
            }
            if (operator2 == '/') {
                if (gcd(num2, num3) != num3) {
                    flag = true;
                }
            }
            if (operator1 == '-') {
                if (num1 - num2 <= 0) {
                    flag = true;
                }
            }
            if (operator2 == '-') {
                if (num2 - num3 <= 0) {
                    flag = true;
                }
            }
            if (operator1 == '*') {
                if (num1 * num2 > 99) {
                    flag = true;
                }
            }
            if (operator2 == '*') {
                if (num2 * num3 > 99) {
                    flag = true;
                }
            }
            if (operator1 == '+') {
                if (num1 + num2 > 99) {
                    flag = true;
                }
            }
            if (operator2 == '+') {
                if (num2 + num3 > 99) {
                    flag = true;
                }
            }
            if (flag) {
                getNums++;
                continue;
            }
            strTest += num1 + "" + operator1 + "" + num2 + "" + operator2 + num3;
            String str = calculate(strTest);
            strTest = "";
            double res = Double.parseDouble(str);
            int intRes = (int) res;




            if (intRes >= 0 && intRes <= 100) {
                String res2 = "" + num1 + "" + operator1 + "" + num2 + "" + operator2 + "" + num3 + "" + '=' + "" + intRes;
                String replace = res2.replace('/', '÷');
                LogUtils.d("生成的数据："+replace);
                allData.add(replace);
               // System.out.println(replace);
            } else {
                getNums++;
            }

        }

        if (!allData.isEmpty()){
            return  allData.get(10);
        }else {
            return   "";
        }
    }

// -----------------------------核心算法-----------------------------

    /**
     * 计算
     */
    public static String calculate(String expression) {
        List<String> num;
        Stack<Double> stack;
        num = transformEnd(expression);
        stack = new Stack<>();
        double sum;
        while (!num.isEmpty()) {
            String temp = String.valueOf(num.remove(0));
            if (isNumber(temp)) {
                double s = Double.parseDouble(temp);
                stack.push(s);
            } else {
                double a = stack.pop();
                double b = stack.pop();
                double c = calTwo(b, a, temp);
                stack.push(c);
            }
        }
        sum = stack.pop();
        return String.valueOf(sum);
    }

    /**
     * 计算两个值
     */
    private static double calTwo(double a, double b, String opr) {
        double sum = 0.0;
        switch (opr) {
            case "+":
                sum = a + b;
                break;
            case "-":
                sum = a - b;
                break;
            case "*":
                sum = a * b;
                break;
            case "/":
                sum = a / b;
                break;
        }
        return sum;
    }

    /**
     * 将中缀表达式转化为后缀表达式（栈是用来进出运算的符号）
     */
    public static List<String> transformEnd(String expre) {
        List<String> sb = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        expre = expre.replaceAll("(\\D)", "o$1o");
        String[] esp = expre.trim().split("o");
        for (String value : esp) {
            String s = value.trim();
            if (isNumber(s)) {
                // 如果是数字则输出
                sb.add(s);
            } else if (!s.isEmpty()) {
                if (!stack.isEmpty() && !isMaxExp(s.charAt(0), stack.peek().charAt(0))) {
                    while (!stack.isEmpty() && !isMaxExp(s.charAt(0), stack.peek().charAt(0))) {
                        sb.add(stack.pop());
                    }
                }
                stack.push(s);
            }
        }
        while (!stack.isEmpty()) {
            sb.add(stack.pop());
        }
        return sb;
    }

    /**
     * 判断是否是数字
     */
    private static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断两个操作符优先级
     */
    private static boolean isMaxExp(char exp1, char exp2) {
        return transExp(exp1) > transExp(exp2);
    }

    /**
     * 优先级
     */
    private static int transExp(char exp) {
        int re = 0;
        switch (exp) {
            case '*':
            case '/':
                re = 2;
                break;
            case '+':
            case '-':
                re = 1;
                break;
        }
        return re;
    }




}
