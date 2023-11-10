public class CalculatorApp {
    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();

        try {
            int resultAdd = calculator.add(10, 5);
            System.out.println("Result of addition: " + resultAdd);

            int resultSubtract = calculator.subtract(10, 5);
            System.out.println("Result of subtraction: " + resultSubtract);

            int resultMultiply = calculator.multiply(10, 5);
            System.out.println("Result of multiplication: " + resultMultiply);

            int resultDivide = calculator.divide(10, 5);
            System.out.println("Result of division: " + resultDivide);
        } catch (ArithmeticException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}