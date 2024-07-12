import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;

public class HTMLParser {

  public static ArrayList<String> getText(String HTML, String URL){
    //converting the String into a document object
    String baseURL = HTMLParser.getBase(URL);
    Document doc = Jsoup.parse(HTML);
    Elements urls = doc.select("a[href]");
    ArrayList<String> output = new ArrayList<>();
    for (Element url : urls){ //now, to handle relative, absolute and protocol-relative URLs
      String toAdd = url.attr("href");
      if (toAdd.startsWith("/")){ //protocol-relative
        toAdd = baseURL+toAdd;
      }
      output.add(toAdd);
    }
    return output;
  }

  public static String getBase(String URL){
    try {
      URL url = new URL(URL);
      String protocol = url.getProtocol();
      String host = url.getHost();
      return protocol + "://" + host;
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args) {
    System.out.println(getBase("https://www.basketball-reference.com/players/f/fultzma01.html"));
  }
}
