import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class WebCrawler {
  private ExecutorService executorService;
  private URLQueue urlQueue;
  private VisitedURLs visitedUrls;
  private HTMLParser htmlParser;
  private DataStorage dataStorage;
  private RateLimiter rateLimiter;
  private RobotsChecker robotsChecker;
  private List<String> startingUrls;

  public WebCrawler(int poolCapacity, URLQueue queue, VisitedURLs seen, HTMLParser parser,
      DataStorage storage, RateLimiter ratelimiter, RobotsChecker robotsChecker,
      List<String> startingUrls) {
    this.executorService = Executors.newFixedThreadPool(poolCapacity);
    this.urlQueue = queue;
    this.visitedUrls = seen;
    this.htmlParser = parser;
    this.dataStorage = storage;
    this.rateLimiter = ratelimiter;
    this.robotsChecker = robotsChecker;
    this.startingUrls = startingUrls;
  }

  public static void main(String[] args) {

  }
}
