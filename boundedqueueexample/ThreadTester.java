/**
   This program runs two threads in parallel.
*/
public class ThreadTester
{
   public static void main(String[] args)
   {
      // Create a BoundedQueue with a capacity designated in the constructor.
      // A BoundedQueue is a FIFO queue defined in BoundedQueue.java
      // queue.Debug(true) will cause queue status to printed to console.
      BoundedQueue<String> queue = new BoundedQueue<String>(10);

      // GREETING_COUNT represents how many times a greeting will be generated.
      final int GREETING_COUNT = 100;

      // Create two Producer runnables with different greeetings
      // Then create a single consumer runnable which will consume and
      // print out the greetings if queue.setDebug() is set to true.
      Runnable run1 = new Producer("Hello, World!", 
            queue, GREETING_COUNT);
      Runnable run2 = new Producer("Buenos Dias!", 
            queue, GREETING_COUNT);
      Runnable run3 = new Consumer(queue, 2 * GREETING_COUNT);
      
      Thread thread1 = new Thread(run1,"Producer HW");
      Thread thread2 = new Thread(run2,"Producer BD");
      Thread thread3 = new Thread(run3,"Consumer");

      thread1.start();
      thread2.start();
      thread3.start();
      try {
         thread1.join();
         thread2.join();
         thread3.join();
      } catch (InterruptedException e) {
         System.out.println("Unable to join all threads: " + e);
      } finally {
         System.out.println("Done. \n" + thread1.getName() + "\n" + thread2.getName());
      }
   }
}

