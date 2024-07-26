package calculatordemo2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator classUnderTest;


    @BeforeAll
    public static void setUp() {
        classUnderTest = new Calculator();
    }

    @DisplayName("Tests the square function")
    @Test
    void testSquare() {
        double num = 5.0;
        assertEquals(num*num, classUnderTest.calcScience(Calculator.singleOperator.square, num));
    }

    @DisplayName("Tests the square root function")
    @Test
    void testSquareRoot() {
        double num = 9.0;
        assertEquals(Math.sqrt(num), classUnderTest.calcScience(Calculator.singleOperator.squareRoot, num));
    }

    @DisplayName("Tests the 1/x function")
    @Test
    void testOneDividedBy() {
        double num = 2.0;
        assertEquals(1/num, classUnderTest.calcScience(Calculator.singleOperator.oneDevidedBy, num));
    }

    @DisplayName("Tests the cosine function in degrees")
    @Test
    void testCos() {
        double num = 45.0;
        assertEquals(Math.cos(num), classUnderTest.calcScience(Calculator.singleOperator.cos, num));
    }

    @DisplayName("Tests the sine function in degrees")
    @Test
    void testSin() {
        double num = 30.0;
        assertEquals(Math.sin(num), classUnderTest.calcScience(Calculator.singleOperator.sin, num));
    }

    @DisplayName("Tests the tangent function in degrees")
    @Test
    void testTan() {
        double num = 60.0;
        assertEquals(Math.tan(num), classUnderTest.calcScience(Calculator.singleOperator.tan, num));
    }

    @DisplayName("Tests the twoOpOperations function's normal mode")
    @Test
    public void testTwoOpOperationsNormalMode() {
        classUnderTest.mode = Calculator.twoOperator.normal;
        classUnderTest.num2 = 5.0;
        assertEquals(5.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's addition")
    @Test
    public void testTwoOpOperationsAddition() {
        classUnderTest.mode = Calculator.twoOperator.add;
        classUnderTest.num1 = 2.0;
        classUnderTest.num2 = 3.0;
        assertEquals(5.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's subtraction")
    @Test
    public void testTwoOpOperationsSubtraction() {
        classUnderTest.mode = Calculator.twoOperator.subtract;
        classUnderTest.num1 = 7.0;
        classUnderTest.num2 = 4.0;
        assertEquals(3.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's multiplication")
    @Test
    public void testTwoOpOperationsMultiplication() {
        classUnderTest.mode = Calculator.twoOperator.multiply;
        classUnderTest.num1 = 2.0;
        classUnderTest.num2 = 3.0;
        assertEquals(6.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's division")
    @Test
    public void testTwoOpOperationsDivision() {
        classUnderTest.mode = Calculator.twoOperator.divide;
        classUnderTest.num1 = 8.0;
        classUnderTest.num2 = 2.0;
        assertEquals(4.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's complex expressions")
    @Test
    public void testTwoOpOperationsComplexExpression1() {
        classUnderTest.mode = Calculator.twoOperator.normal;
        classUnderTest.twoOpCaller(Calculator.twoOperator.add, 2.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.multiply, 3.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.subtract, 1.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.divide, 2.0);
        assertEquals(4.0, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's complex expressions x2")
    @Test
    public void testTwoOpOperationsComplexExpression2() {
        classUnderTest.mode = Calculator.twoOperator.normal;
        classUnderTest.twoOpCaller(Calculator.twoOperator.multiply, 3.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.add, 2.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.divide, 5.0);
        assertEquals(2.4, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpOperations function's complex expressions x3")
    @Test
    public void testTwoOpOperationsComplexExpression3() {
        classUnderTest.mode = Calculator.twoOperator.normal;
        classUnderTest.twoOpCaller(Calculator.twoOperator.subtract, 3.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.multiply, 2.0);
        classUnderTest.twoOpCaller(Calculator.twoOperator.divide, 5.0);
        assertEquals(1.2, classUnderTest.twoOpOperations(), 0.001);
    }

    @DisplayName("Tests the twoOpCaller function in normal mode")
    @Test
    public void testTwoOpCallerNormalMode() {
        classUnderTest.mode = Calculator.twoOperator.normal;
        classUnderTest.num1 = 0.0;
        classUnderTest.num2 = 0.0;

        Double result = classUnderTest.twoOpCaller(Calculator.twoOperator.add, 5.0);

        assertEquals(Double.NaN, result, 0.001);
        assertEquals(0.0, classUnderTest.num2, 0.001);
        assertEquals(5.0, classUnderTest.num1, 0.001);
        assertEquals(Calculator.twoOperator.add, classUnderTest.mode);
    }

    @DisplayName("Tests the twoOpCaller function in non normal mode")
    @Test
    public void testTwoOpCallerNonNormalMode() {
        classUnderTest.mode = Calculator.twoOperator.add;
        classUnderTest.num1 = 2.0;
        classUnderTest.num2 = 3.0;

        Double result = classUnderTest.twoOpCaller(Calculator.twoOperator.subtract, 1.0);

        assertEquals(5.0, result, 0.001);
        assertEquals(1.0, classUnderTest.num2, 0.001);
        assertEquals(5.0, classUnderTest.num1, 0.001);
        assertEquals(Calculator.twoOperator.subtract, classUnderTest.mode);
    }

    @DisplayName("Tests whether an error is thrown if a null mode is passed")
    @Test
    void testThrowError() {
        assertThrows(Error.class, () -> classUnderTest.calcScience(null, 10.0));
    }
}
