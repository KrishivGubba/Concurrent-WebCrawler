import java.util.LinkedList;

public class URLQueue {
  private LinkedList<String> queue;
  private int size;

  private Object qLock1;
  public URLQueue(){
    this.queue = new LinkedList<>();
    this.size = 0;
    this.qLock1 = new Object();

  }

  public boolean enqueue(String url){
    synchronized (qLock1){
      //will implement the enqueuing stuff here
    }
    return true;
  }

  public String deque(){
    String res = "";
    synchronized (qLock1){
      if (size==0){
        try {
          qLock1.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      //will implement the stuff to deque and return from the linked list here.
    }
    return res;
  }

  private boolean isEmpty(){return size == 0;}

  public int getSize(){return size;}
}
