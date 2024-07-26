/**
   An action that repeatedly inserts a greeting into a queue.
*/
public class Producer implements Runnable
{
   // greeting will be set to aGreeting by constructor
   private String greeting;

   // queue will be set to aQueue in constructor
   // greetingCount will set to count in constructor.  It represents
   // the number of times a greeting will be added to queue.
   private BoundedQueue<String> queue;
   private int greetingCount;


   private static final int DELAY = 10;
   /**
      Constructs the producer object.
      @param aGreeting the greating to insert into a queue
      @param aQueue the queue into which to insert greetings
      @param count the number of greetings to produce
   */
   public Producer(String aGreeting, BoundedQueue<String> aQueue, int count)
   {
      greeting = aGreeting;
      queue = aQueue;
      greetingCount = count;
  }

   public void run()
   {
      // While there are still greetings to be created, if the queue is not full, 
      // then add another greeting to the queue and increment the greeting count. 
      // Then sleep to encourage the Consumer thread to run. 
      // The try/catch block is needed when invoking the Thread.sleep() method.
      // The catch block is empty because there is nothing special for the 
      // code to do after sleeping except go through the loop again.
      try
      {
         int i = 1;
         while (i <= greetingCount)
         {
            if (!queue.isFull())
            {
               Thread.currentThread().setName(greeting + ": " + i + " greetings");
               queue.add(i + ": " + greeting);
               i++;
            }
            Thread.sleep((int) (Math.random() * DELAY));         
         }
      }
      catch (InterruptedException exception)
      {
      }
   }

}
