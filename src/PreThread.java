import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;

/**
 * Created by zhileiz on 5/30/17.
 */
public class PreThread implements Runnable {
    String url = "http://www.dianping.com/search/keyword/1/0_%E8%81%94%E9%80%9A%E8%90%A5%E4%B8%9A%E5%8E%85/p";
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
            Elements shops = doc.select("div.shop-all-list").first().select("div.tit");
            for(Element shop : shops){
                String[] splitted = shop.attr("abs.href").split("/shop/");
                System.out.println(splitted[1]);
                try{
                    System.out.println(Integer.parseInt(splitted[1]));
                    urls.add("http://www.dianping.com/search/around/1/0_"+splitted[1]);
                } catch (Exception e){
                    System.out.println("can't parse!");
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
