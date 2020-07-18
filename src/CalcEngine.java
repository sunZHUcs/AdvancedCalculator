import java.util.ArrayList;
import java.util.Scanner;

public class CalcEngine {

    private static final ArrayList<Character> calcs = new ArrayList<>();
    private static final ArrayList<Character> funcs = new ArrayList<>();
    private static final ArrayList<Double> nums = new ArrayList<>();
    private static final ArrayList<Double> numerator = new ArrayList<>();
    private static final ArrayList<Double> denominator = new ArrayList<>();
    private static final ArrayList<Integer> place = new ArrayList<>();
    private static final ArrayList<Integer> allplace = new ArrayList<>();
    private static String equation;

    public static void arrayFill() {
        calcs.add('*');
        calcs.add('/');
        calcs.add('+');
        calcs.add('-');
    }

    public static void methodCall() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        equation = scanner.nextLine();
        equation = equation.replaceAll(" ", "");

        int numfunc = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (calcs.contains(equation.charAt(i))) {
                numfunc++;
            }
        }

        if (equation.toLowerCase().contains("frac")) {
            calcFunc();
        } else if (equation.toLowerCase().contains("stop") || equation.toLowerCase().contains("quit")) {
            System.out.println("Program Closing...");
            Thread.sleep(200);
            System.exit(0);
        } else if (numfunc == 0) {
            System.out.println("Invalid Input! Enter an equation with no characters");
            System.out.println("other than 'frac' and functions!");
            System.out.println();
            methodCall();
        } else {
            basicFunc();
        }
    }

    public static void basicFunc() throws InterruptedException {
        if (equation.contains("[a-zA-Z]")) {
            System.out.println("Invalid Input! Enter an equation with no characters");
            System.out.println("other than 'frac' and functions!");
            System.out.println();
            methodCall();
        }

        boolean determined = false;

        for (int i = 0; i < equation.length(); i++) {
            if (calcs.contains(equation.charAt(i))) {
                for (Character calc : calcs) {
                    if (equation.charAt(i) == calc) {
                        funcs.add(calc);
                        place.add(i);
                    }
                }
            }
        }

        while (!determined) {
            int currentplace = 0;
            for (Integer integer : place) {
                nums.add(Double.parseDouble(equation.substring(currentplace, integer)));
                currentplace = integer + 1;
            }
            nums.add(Double.parseDouble(equation.substring(currentplace)));

            determined = true;
        }


        boolean finished = false;
        double calc = 0;
        while (!finished) {

            int z = funcs.size();
            for (int i = 0; i < z; i++) {

                int y = 0;
                if (funcs.contains('/')) {
                    y = funcs.indexOf('/');
                    nums.set(y, nums.get(y) / nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove('/');
                } else if (funcs.contains('*')) {
                    y = funcs.indexOf('*');
                    nums.set(y, nums.get(y) * nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove('*');
                } else if (funcs.contains('+')) {
                    y = funcs.indexOf('+');
                    nums.set(y, nums.get(y) + nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove('+');
                } else if (funcs.contains('-')) {
                    y = funcs.indexOf('-');
                    nums.set(y, nums.get(y) - nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove('-');
                }

                calc = nums.get(y);

            }
            finished = true;
        }

        if (calc == Math.rint(calc)) {
            int intcalc = (int) calc;
            System.out.println(intcalc);
        } else {
            System.out.println(calc);
        }
        System.out.println();

        funcs.clear();
        nums.clear();
        numerator.clear();
        denominator.clear();
        place.clear();
        allplace.clear();

    }

    public static void calcFunc() throws InterruptedException {
        equation = equation.toLowerCase().substring(5, equation.length() - 1);

        calcs.remove('/');
        equation = equation.replaceAll("[a-zA-Z]", "");

        for (int i = 0; i < equation.length(); i++) {

            if ("/".equalsIgnoreCase(String.valueOf(equation.charAt(i)))) {
                allplace.add(i);
            } else if (calcs.contains(equation.charAt(i))) {
                for (Character calc : calcs) {
                    if (equation.charAt(i) == calc) {
                        funcs.add(calc);
                        allplace.add(i);
                    }
                }
            }

        }

        int currentplace = 0;
        boolean arrayfill = false;
        while (!arrayfill) {

            for (double i = 0; i < allplace.size(); i++) {

                if (i / 2 == Math.rint(i / 2)) {
                    numerator.add(Double.parseDouble(equation.substring(currentplace, allplace.get((int) i))));
                } else {
                    denominator.add(Double.parseDouble(equation.substring(currentplace, allplace.get((int) i))));
                }
                currentplace = allplace.get((int) i) + 1;
            }

            denominator.add(Double.parseDouble(equation.substring(currentplace)));
            arrayfill = true;

        }

        boolean finished = false;
        double calc = 0;
        while (!finished) {

            if (numerator.size() == denominator.size()) {

                for (int x = 0; x < numerator.size(); x++) {
                    nums.add((numerator.get(x) / denominator.get(x)));
                }
                int z = funcs.size();
                for (int i = 0; i < z; i++) {

                    int y = 0;
                    if (funcs.contains('/')) {
                        y = funcs.indexOf('/');
                        nums.set(y, nums.get(y) / nums.get(y + 1));
                        nums.remove(y + 1);
                        funcs.remove('/');
                    } else if (funcs.contains('*')) {
                        y = funcs.indexOf('*');
                        nums.set(y, nums.get(y) * nums.get(y + 1));
                        nums.remove(y + 1);
                        funcs.remove('*');
                    } else if (funcs.contains('+')) {
                        y = funcs.indexOf('+');
                        nums.set(y, nums.get(y) + nums.get(y + 1));
                        nums.remove(y + 1);
                        funcs.remove('+');
                    } else if (funcs.contains('-')) {
                        y = funcs.indexOf('-');
                        nums.set(y, nums.get(y) - nums.get(y + 1));
                        nums.remove(y + 1);
                        funcs.remove('-');
                    }

                    calc = nums.get(y);

                }
                finished = true;
            } else {
                System.out.println("A critical error has occurred. Program will now shutdown...");
                Thread.sleep(200);
                System.exit(0);
            }
        }

        if (calc == Math.rint(calc)) {
            int intcalc = (int) calc;
            System.out.println(intcalc);
        } else {
            System.out.println(calc);
        }


        String y = String.valueOf(calc);
        y = y.replaceAll("[-]", "");
        String[] num = y.split("[.]");
        int numdec = Integer.parseInt(String.valueOf(num[1].length()));
        numdec = (int) Math.pow(10, numdec);

        calc = calc * numdec;
        int intcalc = (int) calc;

        int gcd = getGCD(intcalc, numdec);

        System.out.println(intcalc / gcd + "/" + numdec / gcd);

        funcs.clear();
        nums.clear();
        numerator.clear();
        denominator.clear();
        place.clear();
        allplace.clear();
    }

    public static int getGCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return getGCD(n2, n1 % n2);
    }

}

