public class Main {

    @SuppressWarnings({"InfiniteLoopStatement"})
    public static void main(String[] args) throws InterruptedException {

        //Messages.beginPrompt();
        //Messages.infoSpout();
        CalcEngine.arrayFill();

        while (true) {
            CalcEngine.methodCall();
        }

    }
}
