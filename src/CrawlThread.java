import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhileiz on 5/30/17.
 */
public class CrawlThread implements Runnable {
    String url;
    String address;
    int threadNum;
    Set<String> strs;

    public CrawlThread(String url, int num, String address, Set<String> strs){
        this.url = url;
        this.threadNum = num;
        this.strs = strs;
        this.address = address;
    }

    @Override
    public void run() {
        try {
            Target tgt = new Target(url,address);
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
