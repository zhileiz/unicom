import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhileiz on 5/30/17.
 */
public class CrawlThread implements Runnable {
    String url;
    int threadNum;
    Set<String> strs;

    public CrawlThread(String url, int num, Set<String> strs){
        this.url = url;
        this.threadNum = num;
        this.strs = strs;
    }

    @Override
    public void run() {
        try {
            Target tgt = new Target(url);
            tgt.crawl();
            try {
                tgt.writeTo(strs,threadNum);
            } catch(Exception e) {
                System.out.println("*** " + threadNum+"需要提高姿势水平！！ ***");
            }
        } catch (Exception e) {
            System.out.println("*** 苟! ***");
        }
        System.out.println("××××× ！ "+threadNum +"续了！ ××××");
    }
}
