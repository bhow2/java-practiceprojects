/**
   An action that repeatedly removes a greeting from a queue.
*/
public class Consumer implements Runnable
{
   // queue will be set to aQueue in constructor
   // greetingCount will set to count in constructor.  It represents
   // the number of times a greeting will be added to queue.
   private BoundedQueue<String> queue;
   private int greetingCount;

   // DELAY is used to have the thread that uses this runnable to 
   // sleep for long enough to have another thread run.
   private static final int DELAY = 10;
   /**
      Constructs the consumer object.
      @param aQueue the queue from which to retrieve greetings
      @param count the number of greetings to consume
   */
   public Consumer(BoundedQueue<String> aQueue, int count)
   {
      queue = aQueue;
      greetingCount = count;
   }

   public void run()
   {
      // While there are still greetings to be created by Producer
      // If the queue is not empty, then remove the greeting from 
      // the queue and print it to the console. 
      // Then sleep to encourage additional greetings to be added to queue.
      // The try/catch block is needed when invoking the Thread.sleep() method.
      // The catch block is empty because there is nothing special for the 
      // code to do after sleeping except go through the loop again.
      try
      {
         int i = 1;
         while (i <= greetingCount)
         {
            if (!queue.isEmpty())
            {
               String greeting = queue.remove();
               System.out.println(greeting);
               i++;
            }
            Thread.sleep((int)(Math.random() * DELAY));
         }
      }
      catch (InterruptedException exception)
      {
      }
   }


}
