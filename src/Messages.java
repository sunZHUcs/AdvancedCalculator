import java.util.Scanner;

@SuppressWarnings("StringOperationCanBeSimplified")
public class Messages {


    public static void beginPrompt() throws InterruptedException {
        System.out.println("");
        System.out.println("Welcome to Advanced Calculator v0.01");
        System.out.println("Once complete, this calculator will be able to do the following:");
        System.out.println("• Basic Functions (Add/Sub/Div/Mul)");
        System.out.println("• Order of Operations");
        System.out.println("• Factor Quadratics");
        System.out.println("• Fraction Functions");
        System.out.println("");
        System.out.println("However, currently the only function(s) working are:");
        System.out.println("• Basic Functions (Add/Sub/Div/Mul)");
        System.out.println("• Order of Operations");
        System.out.println("• Fraction Functions");
        System.out.println("You can exit this application at any time by entering either 'Stop' or 'Quit'.");
        System.out.println("Would you like to continue? Enter either 'Yes' or 'No'. Case does not matter.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean tf = true;

        while (tf) {
            switch (input.toLowerCase()) {
                case "yes" -> {
                    System.out.println("Proceeding...");
                    Thread.sleep(200);
                    tf = false;
                }
                case "no", "stop", "quit" -> {
                    System.out.println("Program Closing...");
                    Thread.sleep(200);
                    System.exit(0);
                    tf = false;
                }
                default -> {
                    System.out.println("Invalid input! Enter either 'Yes' or 'No'. Case does not matter.");
                    input = scanner.nextLine();
                    tf = true;
                }
            }
        }

    }

    public static void infoSpout() throws InterruptedException {
        System.out.println("");
        System.out.println("");
        System.out.println("To do basic functions, type in an equation. Currently,");
        System.out.println("Order of Operations is not supported, so the input equation will be");
        System.out.println("calculated in the order it was entered.");
        System.out.println("");
        System.out.println("To calculate fraction functions, wrap the equation with 'frac(x)'");
        System.out.println("e.g. 'frac(3/7*8/2)");
        System.out.println("");
        System.out.println("Do you understand? Enter either 'Yes' or 'No'. Case does not matter.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean tf = true;

        while (tf) {
            switch (input.toLowerCase()) {
                case "yes" -> {
                    System.out.println("Launching CalcEngine...");
                    Thread.sleep(200);
                    System.out.println("CalcEngine initiated. Type in an equation to begin.");
                    System.out.println("");
                    tf = false;
                }
                case "no", "stop", "quit" -> {
                    System.out.println("Program Closing...");
                    Thread.sleep(200);
                    System.exit(0);
                    tf = false;
                }
                default -> {
                    System.out.println("Invalid input! Enter either 'Yes' or 'No'. Case does not matter.");
                    input = scanner.nextLine();
                    tf = true;
                }
            }
        }
    }
}
