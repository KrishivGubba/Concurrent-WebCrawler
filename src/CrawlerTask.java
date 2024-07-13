import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

  }

  public static void main(String[] args) {
    try {
      URL url = new URL("https://en.wikipedia.org/wiki/Nitrogen");
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
        System.out.println(text);
        // print result
        ArrayList<String> res = HTMLParser.getLinks(content.toString(),"https://www.espncricinfo.com/");
        for (String thing: res){
          System.out.println(thing);
        }
      } else {
        System.out.println("GET request failed");
      }

      connection.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
