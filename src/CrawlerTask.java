import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class CrawlerTask implements Runnable{
  private URLQueue queue;
  public CrawlerTask(URLQueue queue){
    this.queue = queue;
  }
  @Override
  public void run() {
    //should first deque from the queue
    String target = queue.deque();
    //TODO: check to see if the target is in the VisitedURL class

    //TODO: Use the RobotsChecker to ensure that crawling the URL is allowed by the
    // website's robots.txt file.

    //TODO: Respect the rate limiting rules defined in the RateLimiter to avoid overwhelming the
    // server (not sure about this)

    //fetch HTML content
    try{
      URL url = new URL(target);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(
            connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
        Document doc = Jsoup.parse(content.toString());
        String text = doc.body().text();
        //TODO: have to save this text somewhere. also should check if the text is valid
        System.out.println(text);
        // TODO: enqueue the result URLs back to the URLQueue (only if the text above is valid)
        ArrayList<String> res = HTMLParser.getLinks(content.toString(),target);
        for (String thing: res){
          //links are enqueued back to the URLQueue
          queue.enqueue(thing);
        }
      } else {
        //TODO: implement logging mechanism to keep track of failures
        System.out.println("GET request failed");
      }
    }catch (Exception e){
      System.out.println();
    }
  }

  public static void main(String[] args) {
    try {
      URL url = new URL("https://en.wikipedia.org/wiki/Nitrogen");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");



      connection.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
