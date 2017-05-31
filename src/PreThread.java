import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.Set;

/**
 * Created by zhileiz on 5/30/17.
 */
public class PreThread implements Runnable {
    String url = "http://www.dianping.com/search/keyword/1/0_联通营业厅/p";
    int num;
    Set<String> urls;

    public PreThread(int num, Set<String> urls){
        this.num = num;
        this.urls = urls;
    }

    @Override
    public void run() {
        String link = url + num;
        try {
            Document doc = Jsoup.connect(link).get();
            if (doc != null) {
                Elements shops = doc.select("div.shop-all-list").first().select("div.tit");
                for (Element shop : shops) {
                    Element linking = shop.select("a").first();
                    String st = linking.attr("href");
                    urls.add("http://www.dianping.com/search/around/1/0_" + st.substring(6,st.length()));
                }
            }
        } catch (Exception e){
            System.out.println("no such url:" + link);
        }

    }


    public Set<String> getUrls(){
        return urls;
    }
}
