/** 
    A first-in, first-out, wrap-around, bounded queue of objects.  
*/ 
public class BoundedQueue<E>
{ 
   // Create a queue of a bounded size (hence name BoundedQueue) of
   // elements.  The type of the elements are to be declared at
   // constructor call time. 
   // Queues have a head, tail.  
   // size represents the current number of elements in queue.
   private Object[] elements; 
   private int head; 
   private int tail; 
   private int size;
   /** 
       Constructs an empty queue. 
       @param capacity the maximum capacity of the queue 
   */ 
   public BoundedQueue(int capacity) 
   { 
      elements = new Object[capacity]; 
      head = 0; 
      tail = 0; 
      size = 0;
   } 

   /** 
       Appends an object at the tail. 
       @param newValue the object to be appended 
       @precondition !isFull();
   */ 
   public synchronized void add(E newValue) 
   { 
      // Add the new element to the tail of the queue.
      // Then update the tail index and the size of the queue.

      // If tail equals the length of the queue, then set tail to 0

      // This may cause old data to be overwritten on the next 
      // invocation if elements are not removed from queue or
      // caller does not check if queue is full.
      elements[tail] = newValue; 
      tail++;
      size++;
      if (tail == elements.length) 
      {
            tail = 0; 
      }
   } 
   /** 
       Removes the object at the head. 
       @return the object that has been removed from the queue
       @precondition !isEmpty()
   */ 
   public synchronized E remove() 
   { 
      // Add an element from the head of the queue.
      // Then update the head index and the size of the queue.

      // If head equals the length of the queue, then set head to 0.

      // This may cause old data to be overwritten on the next 
      // invocation if elements are not removed from queue or
      // caller does not check if queue is full.
      E r = (E) elements[head]; 
      head++;
      size--;
      if (head == elements.length) 
      {
         head = 0; 
      }

      return r; 
   } 

   public boolean isFull() 
   { 
      return size == elements.length;
   } 

   public boolean isEmpty() 
   { 
      return size == 0; 
   } 


}
