import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhileiz on 5/30/17.
 */
public class PreThread implements Runnable {
    String url = "http://www.dianping.com/search/keyword/1/0_联通营业厅/p";
    int num;
    ConcurrentHashMap<String,String> tgts;

    public PreThread(int num, ConcurrentHashMap<String,String> tgts){
        this.num = num;
        this.tgts = tgts;
    }

    @Override
    public void run() {
        String link = url + num;
        try {
            Document doc = Jsoup.connect(link).get();
            if (doc != null) {
                Elements shops = doc.select("div.shop-all-list").first().select("div.txt");
                for (Element shop : shops) {
                    Element linking = shop.select("div.tit").first().select("a").first();
                    Element addr = shop.select("span.addr").first();
                    String st = linking.attr("href");
                    String address = addr.text();
                    tgts.put(address, "http://www.dianping.com/search/around/1/0_" + st.substring(6, st.length()));
                }
            }
        } catch (Exception e) {
            System.out.println("no such url:" + link);
        }

    }
}
