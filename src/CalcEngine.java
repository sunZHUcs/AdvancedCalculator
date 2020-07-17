import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CalcEngine {

    private static String equation;
    private static final ArrayList<Character> calcs = new ArrayList<>();
    private static final ArrayList<Character> funcs = new ArrayList<>();
    private static final ArrayList<Double> nums = new ArrayList<>();
    private static final ArrayList<Integer> numerator = new ArrayList<>();
    private static final ArrayList<Integer> denominator = new ArrayList<>();
    private static final ArrayList<Integer> fracplace = new ArrayList<>();
    private static final ArrayList<Integer> place = new ArrayList<>();
    private static final ArrayList<Integer> allplace = new ArrayList<>();


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
        } else if (equation.toLowerCase().contains("stop") || equation.toLowerCase().contains("stop")) {
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
                    funcs.remove(funcs.indexOf('/'));
                } else if (funcs.contains('*')) {
                    y = funcs.indexOf('*');
                    nums.set(y, nums.get(y) * nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove(funcs.indexOf('*'));
                } else if (funcs.contains('+')) {
                    y = funcs.indexOf('+');
                    nums.set(y, nums.get(y) + nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove(funcs.indexOf('+'));
                } else if (funcs.contains('-')) {
                    y = funcs.indexOf('-');
                    nums.set(y, nums.get(y) - nums.get(y + 1));
                    nums.remove(y + 1);
                    funcs.remove(funcs.indexOf('-'));
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
        System.out.println("");

        funcs.clear();
        nums.clear();
        numerator.clear();
        denominator.clear();
        fracplace.clear();
        place.clear();
        allplace.clear();

    }

    public static void calcFunc() {
        equation = equation.toLowerCase().substring(5, equation.length() - 1);

        calcs.remove("/");
        equation.replaceAll("[a-zA-Z]", "");

        for (int i = 0; i < equation.length(); i++) {

            if ("/".equalsIgnoreCase(String.valueOf(equation.charAt(i)))) {
                fracplace.add(i);
                allplace.add(i);
            } else if (calcs.contains(equation.charAt(i))) {
                for (Character calc : calcs) {
                    if (equation.charAt(i) == calc) {
                        funcs.add(calc);
                        place.add(i);
                        allplace.add(i);
                    }
                }
            }

        }

        int currentplace = 0;
        boolean arrayfill = false;
        while (!arrayfill) {

            for(double i = 0; i < allplace.size(); i++){

                if(i/2 == Math.rint(i/2)){
                    numerator.add(Integer.parseInt(equation.substring(currentplace, allplace.get((int) i))));
                }else{
                    denominator.add(Integer.parseInt(equation.substring(currentplace, allplace.get((int) i))));
                }
                currentplace = allplace.get((int) i) +1;
            }

            denominator.add(Integer.parseInt(equation.substring(currentplace)));
            arrayfill = true;

        }

        boolean finished = false;
        double calc = 0;
        int cd;
        String ans = "";
        while(!finished){

           int z = place.size();
           for(int i = 0; i < z; i++){

               int y = 0;

               if(funcs.contains('+')){
                   y= funcs.indexOf('+');

                   if(i == 0){
                       cd = gcd(denominator.get(i), denominator.get(i+1));
                       cd = (denominator.get(i) * denominator.get(i+1)) / cd;
                       int num3 = (numerator.get(i)) * (cd/denominator.get(i)) + (numerator.get(i+1)) * (cd/denominator.get(i+1));
                       ans = lowest(cd, num3);
                   }else {
                       cd = gcd(denominator.get(i +1), denominator.get(i+2));
                       cd = (denominator.get(i+1) * denominator.get(i+2)) / cd;
                       int num3 = (numerator.get(i+1)) * (cd/denominator.get(i+1)) + (numerator.get(i+2)) * (cd/denominator.get(i+2));
                       ans = lowest(cd, num3);
                   }

                   finished = true;
               }
           }

        }

        System.out.println(ans);
        System.out.println(numerator.toString());
        System.out.println(denominator.toString());

        funcs.clear();
        nums.clear();
        numerator.clear();
        denominator.clear();
        fracplace.clear();
        place.clear();
        allplace.clear();

    }

    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b%a, a);
    }
    
    static String lowest(int cd, int num3){
        int common_factor = gcd(num3, cd);
        
        cd = cd/common_factor;
        num3 = num3/common_factor;

        if(num3/cd == Math.rint(num3/cd)){
            return String.valueOf(num3/cd);
        }

        return num3 + "/" + cd;
    }
}
