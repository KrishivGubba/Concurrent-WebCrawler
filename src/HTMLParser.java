import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Array;
import java.util.ArrayList;

public class HTMLParser {

  public static ArrayList<String> getText(String HTML){
    //converting the String into a document object
    Document doc = Jsoup.parse(HTML);
    Elements urls = doc.select("a[href]");
    ArrayList<String> output = new ArrayList<>();
    for (Element url : urls){
      output.add(url.attr("href"));
      System.out.println(url.attr("href"));
    }
    return output;
  }

  public static void main(String[] args) {
    ArrayList<String> res = HTMLParser.getText("<html><body><a href='https://example.com'>Example" +
        "</a></body></html>");
    System.out.println(res);
  }
}
