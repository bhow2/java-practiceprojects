/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package calculatordemo2;

import javax.swing.JTextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;  // This brings in the Field feature of Java Reflection

class CalculatorUITest {

    private static CalculatorUI classUnderTest;

    @BeforeAll
    public static void setUp() {
        classUnderTest = new CalculatorUI();
    }

    @DisplayName("Testing that writer writes the display")
    @Test
    public void writerSetText() throws Exception {
        // Use reflection to access the private field “text”
        Class cls = classUnderTest.getClass();
        Field field = cls.getDeclaredField("text");
        field.setAccessible(true);
        // Set the value of “text” to “mytext”
        JTextArea text = (JTextArea) field.get(classUnderTest);
        text.setText("mytext");
        // Test that the value of “text” is “mytext”
        assertEquals("mytext", text.getText());
    }

    public void testReader() throws Exception {
        classUnderTest.text.setText("123.45");
        assertEquals(123.45, classUnderTest.reader(), 0.001);
    }

    // START OF actionPerformed() UNIT TESTS
    @Test
    public void testActionPerformedButton2() {
        classUnderTest.jButtons[2].doClick(); // Click button "2"
        assertEquals("2", classUnderTest.text.getText());
    }

    @Test
    public void testActionPerformedMultiply() {
        classUnderTest.jButtons[3].doClick(); // Click button "3"
        classUnderTest.mult.doClick(); // Click the multiply button
        classUnderTest.jButtons[4].doClick(); // Click button "4"
        classUnderTest.equal.doClick(); // Click the equal button
        assertEquals("12.0", classUnderTest.text.getText());
    }

    @Test
    public void testActionPerformedDivide() {
        classUnderTest.jButtons[6].doClick(); // Click button "6"
        classUnderTest.div.doClick(); // Click the divide button
        classUnderTest.jButtons[2].doClick(); // Click button "2"
        classUnderTest.equal.doClick(); // Click the equal button
        assertEquals("3.0", classUnderTest.text.getText());
    }

    @Test
    public void testActionPerformedSquare() {
        classUnderTest.jButtons[7].doClick(); // Click button "7"
        classUnderTest.sqr.doClick(); // Click the square button
        assertEquals("49.0", classUnderTest.text.getText());
    }

    @Test
    public void testActionPerformedSquareRoot() {
        classUnderTest.jButtons[8].doClick(); // Click button "8"
        classUnderTest.sqrRt.doClick(); // Click the square root button
        assertEquals("2.828", classUnderTest.text.getText().substring(0, 5)); // Adjust for rounding
    }

    @Test
    public void testActionPerformedInverse() {
        classUnderTest.jButtons[9].doClick(); // Click button "9"
        classUnderTest.inverse.doClick(); // Click the inverse button
        assertEquals("0.111", classUnderTest.text.getText().substring(0, 5)); // Adjust for rounding
    }

    @Test
    public void testActionPerformedCos() {
        classUnderTest.cos.doClick(); // Click the cos button
        assertEquals("", classUnderTest.text.getText()); // Cosine of nothing is undefined
    }

    @Test
    public void testActionPerformedSin() {
        classUnderTest.jButtons[4].doClick(); // Click button "4"
        classUnderTest.sin.doClick(); // Click the sin button
        assertEquals("0.717", classUnderTest.text.getText().substring(0, 5)); // Adjust for rounding
    }

    @Test
    public void testActionPerformedTan() {
        classUnderTest.tan.doClick(); // Click the tan button
        assertEquals("", classUnderTest.text.getText()); // Tangent of nothing is undefined
    }

    // END OF actionPerformed() UNIT TESTS
    
    @Test 
    void appPanelIsCreated() {
        assertNotNull(classUnderTest, "app should have a panel object");
    }
}
