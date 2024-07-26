import java.io.FileInputStream;
import java.io.IOException;

// this demonstrates many different types of exceptions that may be useful in real java projects

public class ExceptionMain {
    private int intResult;
    private double doubleResult;
    
    public static void main(String[] args) {
		ExceptionMain exceptionGenerator = new ExceptionMain();
        // Divide examples with a divide by zero.
        exceptionGenerator.divideInt(10,0);
       
        exceptionGenerator.divideDouble(10,0);

        // Are there differences in what the exceptions produce?
        exceptionGenerator.handlingACustomExceptionExample();

        try {
            exceptionGenerator.methodWithThrowAndFinally();
        } catch (IOException e) { 
            System.out.println("\nmain: catch: exception thrown by methodWithThrowAndFinally"); 
	    }
    }
      // Example of standard, user provided exception handling for divide by zero
    public void divideInt(int nom, int denom) {
        try {
            intResult = nom / denom;
            System.out.println("\ndivideInt: The result is: " + intResult);
        } catch (ArithmeticException e) {
            System.out.println("\ndivideInt: catch: An ArithmeticException occurred: " + nom + "/" + denom + " is not valid: " + e.getMessage());
        } finally {
            System.out.println("divideInt: finally: This will always be executed, exception or not.");
        }
    }
     // Divide by zero by doubles follows the  IEEE 754 standard for floating point computations. 
    public void divideDouble(double nom, double denom) {
        try {
            doubleResult = nom / denom;
            if (denom == 0)
                throw new ArithmeticException("\ndivideDouble: custom throw: division by zero: "+ nom + "/" + denom);

            System.out.println("\ndivideDouble: The result of " + nom + "/" + denom +" is: " + doubleResult);
        } catch (ArithmeticException e) {
            System.out.println("\ndivideDouble: catch: An ArithmeticException occurred: " + nom + "/" + denom + " is not valid: " + e.getMessage());
        }
    }
    // Example of a method handling a custom exception caused by a sub-method.
    public void handlingACustomExceptionExample() {
        try {
            methodThatThrowsException();
        } catch (MyException myException) {
            System.out.println("\nhandlingACustomeExceptionExample: catch: " + myException.getMessage());
        }
    }
    // The sub-method that throws a custom exception.  
    private void methodThatThrowsException() throws MyException {
        // Do stuff and if something you don't like happens, we do the following: 
        throw new MyException("Yikes!");
    }
    // The custom exception
    private class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }  

    public void methodWithThrowAndFinally() throws IOException {
    FileInputStream fis = null;
    try {
        fis = new FileInputStream("somefile.txt");
        // Do something with the file
    } catch (IOException e) {
        System.out.println("\nmethodWithThrowAndFinally: Exception caught: " + e.getMessage());
        throw e;  // Re-throw the caught exception because I'm throwing an exception and using a try-catch block.
    } finally {
        System.out.println("\nmethodWithThrowAndFinally: finally: Executing finally block.");
        if (fis != null) {
            try {
                fis.close();
            } catch (Exception e) {
                System.out.println("\nmethodWithThrowAndFinally: Error closing file." + e.getMessage());
            }
        }
    }
}

}



