import java.io.IOException;
import java.util.logging.*;

public class SimpleCalculator implements Calculator {
    private static final Logger logger = Logger.getLogger(SimpleCalculator.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("calculator.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(int a, int b) throws ArithmeticException {
        try {
            logger.log(Level.INFO, "Adding {0} and {1}", new Object[]{a, b});
            return a + b;
        } catch (ArithmeticException e) {
            logger.log(Level.WARNING, "Error occurred during addition: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int subtract(int a, int b) throws ArithmeticException {
        try {
            logger.log(Level.INFO, "Subtracting {0} from {1}", new Object[]{b, a});
            return a - b;
        } catch (ArithmeticException e) {
            logger.log(Level.WARNING, "Error occurred during subtraction: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int multiply(int a, int b) throws ArithmeticException {
        try {
            logger.log(Level.INFO, "Multiplying {0} and {1}", new Object[]{a, b});
            return a * b;
        } catch (ArithmeticException e) {
            logger.log(Level.WARNING, "Error occurred during multiplication: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int divide(int a, int b) throws ArithmeticException {
        try {
            logger.log(Level.INFO, "Dividing {0} by {1}", new Object[]{a, b});
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        } catch (ArithmeticException e) {
            logger.log(Level.WARNING, "Error occurred during division: " + e.getMessage());
            throw e;
        }
    }
}
