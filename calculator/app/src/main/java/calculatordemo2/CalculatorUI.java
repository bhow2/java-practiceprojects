package calculatordemo2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * CalculatorUI class that creates and adds buttons, event handling for the buttons, and calls calculator
 * methods and functions for logic when necessary
 * @author Soria, Steckler, Tesic, Metsis
 */

public class CalculatorUI implements ActionListener {
	public final JFrame frame;
    public final JPanel panel;
    public final JTextArea text;
    public JButton jButtons[], equal, cancel, add, sub, mult, div, sqr, sqrRt, inverse, cos, sin, tan;
	public final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    public final Calculator calc;

    // Separate button groups
    private final OperatorButtonGroup primitiveOperators;
    private final OperatorButtonGroup trigonometricOperators;
    private final OperatorButtonGroup commonFunctionOperators;
	

    /**
     * The main top level GUI of the calculator and it's frame, button, and text area for # display
     */
    public CalculatorUI() {
        frame = new JFrame("Calculator");
        frame.setResizable(true);
        panel = new JPanel(new FlowLayout());
        text = new JTextArea(2, 25);
        jButtons = new JButton[10];
        calc = new Calculator();

		

        // Initialize button groups
        primitiveOperators = new OperatorButtonGroup();
        trigonometricOperators = new OperatorButtonGroup();
        commonFunctionOperators = new OperatorButtonGroup();

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 10; i++) {
            jButtons[i] = new JButton(String.valueOf(i));
            jButtons[i].addActionListener(this);
        }
		

        // Initialize button groups
        primitiveOperators.addButton("+");
        primitiveOperators.addButton("-");
        primitiveOperators.addButton("*");
        primitiveOperators.addButton("/");

        trigonometricOperators.addButton("Cos");
        trigonometricOperators.addButton("Sin");
        trigonometricOperators.addButton("Tan");

        commonFunctionOperators.addButton("√");
        commonFunctionOperators.addButton("x*x");
        commonFunctionOperators.addButton("1/x");

        // Other buttons
        equal = new JButton("=");
        cancel = new JButton("C");

        // Add event listeners
        equal.addActionListener(this);
        cancel.addActionListener(this);
    }

    /**
     * Initializes and sets the frame size, buttons, panels. The main runner method of the UI class.
     */
    public void init() {
        frame.setSize(300, 340);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.add(text);
        for (int i = 0; i < 10; i++) {
            panel.add(jButtons[i]);
        }

        // Add button groups to the panel
        panel.add(primitiveOperators.getPanel());
        panel.add(trigonometricOperators.getPanel());
        panel.add(commonFunctionOperators.getPanel());

        // Add other buttons
        panel.add(equal);
        panel.add(cancel);

        frame.setVisible(true);
    }

	/**
	 * Event handling implementation for Calculator button pressing
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		// check 0-9 and update textfield
		for (int i = 0; i < 10; i++) {
			if (source == jButtons[i]) {
				text.replaceSelection(buttonValue[i]);
				return;
			}
		}
		if (source == add) {
			writer(calc.twoOpCaller(Calculator.twoOperator.add, reader()));
		}
		if (source == sub) {
			writer(calc.twoOpCaller(Calculator.twoOperator.subtract, reader()));
		}
		if (source == mult) {
			writer(calc.twoOpCaller(Calculator.twoOperator.multiply,
					reader()));
		}
		if (source == div) {
			writer(calc.twoOpCaller(Calculator.twoOperator.divide, reader()));
		}
		if (source == sqr) {
			writer(calc.calcScience(Calculator.singleOperator.square,
					reader()));
		}
		if (source == sqrRt) {
			writer(calc.calcScience(Calculator.singleOperator.squareRoot,
					reader()));
		}
		if (source == inverse) {
			writer(calc.calcScience(
					Calculator.singleOperator.oneDevidedBy, reader()));
		}
		if (source == cos) {
			writer(calc.calcScience(Calculator.singleOperator.cos,
					reader()));
		}
		if (source == sin) {
			writer(calc.calcScience(Calculator.singleOperator.sin,
					reader()));
		}

		if (source == tan) {
			writer(calc.calcScience(Calculator.singleOperator.tan,
					reader()));
		}
		if (source == equal) {
			writer(calc.calculateEqual(reader()));
		}
		if (source == cancel) {
			writer(calc.reset());
		}
		// for easy continued calculations/copy
		text.selectAll();
	}

	/**
	 * Helper function that gets the texfield area and updates the number input
	 * @return the updated number
	 */
	public Double reader() {
		Double num;
		String str;
		str = text.getText();
		num = Double.valueOf(str);

		return num;
	}

	/**
	 * Helper function that sets the textfield with the number, and avoids NaN issues
	 * @param num
	 */
	public void writer(final Double num) {
		if (Double.isNaN(num)) {
			text.setText("");
		} else {
			text.setText(Double.toString(num));
		}
	}
	private class OperatorButtonGroup {
        private final JPanel groupPanel;

        public OperatorButtonGroup() {
            groupPanel = new JPanel(new FlowLayout());
        }

        public void addButton(String label) {
            JButton button = new JButton(label);
            button.addActionListener(CalculatorUI.this);
            groupPanel.add(button);
        }

        public JPanel getPanel() {
            return groupPanel;
        }
	}
}
