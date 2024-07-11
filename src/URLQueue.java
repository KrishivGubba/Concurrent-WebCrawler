import java.util.LinkedList;

public class URLQueue {
  private LinkedList<String> queue;
  private int size;

  private int capacity;
  private final Object qLock1;
  public URLQueue(){
    this.queue = new LinkedList<>();
    this.size = 0;
    this.qLock1 = new Object();
    this.capacity = 200;
  }

  public boolean enqueue(String url){
    synchronized (qLock1){
      //cannot enqueue on a full queue, hence implementing a waiting mechanism
      while (size==capacity){
        try {
          qLock1.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      queue.add(url);
      size++;
      qLock1.notifyAll();
      return true;
    }
  }

  public String deque(){
    String res = null;
    synchronized (qLock1){
      while (size==0){
        try {
          qLock1.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      res = queue.removeFirst();
      size--;
      qLock1.notifyAll();
      return res;
    }
  }

  private boolean isEmpty(){return size == 0;}

  public int getSize(){return size;}

  //TODO: implement the following in testing:
//  public static void main(String[] args) {
//    // Create an instance of URLQueue with a custom capacity
//    URLQueue queue = new URLQueue();
//
//    // Create threads to simulate enqueuing and dequeuing URLs
//    Thread producer = new Thread(() -> {
//      for (int i = 0; i < 300; i++) {
//        String url = "http://example.com/page" + i;
//        queue.enqueue(url);
//        System.out.println("Enqueued: " + url);
//      }
//    });
//    Thread another = new Thread(() -> {
//      for (int i = 0; i < 300; i++) {
//        String url = "http://example.com/page" + i;
//        queue.enqueue(url);
//        System.out.println("Enqueued: " + url);
//      }
//    });
//
//    Thread consumer = new Thread(() -> {
//      for (int i = 0; i < 600; i++) {
//        String url = queue.deque();
//        System.out.println("Dequeued: " + url);
//      }
//    });
//
//    // Start the threads
//    producer.start();
//    consumer.start();
//    another.start();
//
//    // Wait for threads to finish
//    try {
//      producer.join();
//      consumer.join();
//      another.join();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//
//    System.out.println("Test completed.");
//  }
}
