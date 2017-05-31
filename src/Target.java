import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Target {
    public String url;
    private HashSet<Shop> surroundings; //商家集
    private BufferedWriter writer; //输出

    public Target(String url){
        this.url = url;
        surroundings = new HashSet<Shop>();
    }

    // 爬取
    public void crawl() throws IOException {
        Document dc = Jsoup.connect(url).get();
        Elements links = dc.select("div.nc-items").first().select("a");
        for (Element e:links){
            try {
                Document sub = Jsoup.connect(e.attr("abs:href")).get();
                String category = sub.select("a.current-category").first().text();
                Elements sublinks = sub.select("div.nc-items").first().select("a");
                for (Element sube:sublinks){
                    Document end = Jsoup.connect(sube.attr("abs:href")).get();
                    String parent = end.select("a.cur").first().text();
                    parsePage(end,category,parent);
                }
            } catch (Exception f){
                //输出警告
                System.out.println("\n##### 发生错误：#####\n"
                                   + e.attr("abs:href")
                                   + "\n当前页面不存在"
                                   + "\n###################");
            }
        }
    }

    // 分析页面
    private void parsePage(Document dc, String category,String parent){
        Elements shops = dc.select("div.shop-list").first().select("div.txt");
        for (Element sh:shops){
            String name = getShopName(sh);
            double[] scores = getShopScore(sh);
            String address = getShopAddress(sh);
            System.out.println(name + " " + scores[1] + " " + address);
            surroundings.add(new Shop(name,category,parent,scores,address));
        }
        System.out.println("\n");
    }

    // 获得商家名
    private String getShopName(Element e){
        return e.select("h4").first().text();
    }

    // 获得商家地址
    private String getShopAddress(Element e){
        return e.select("span.addr").first().text();
    }

    // 获得商家评分
    private double[] getShopScore(Element e){
        Elements comment = e.select("span.comment-list");
        if (comment.isEmpty()){
            double[] zeros = new double[3];
            return zeros;
            // 若无评分信息，则返回全0
        } else {
            Elements scores = comment.select("b");
            double[] zeros = new double[3];
            int i = 0;
            for (Element k:scores) {
                zeros[i] = Double.parseDouble(k.text());
                i++;
            }
            return zeros;
        }
    }


    public void writeTo(Set<String> strs, int num){
        Iterator<Shop> it = surroundings.iterator();
        while (it.hasNext()){
            String s = it.next().print();
            strs.add(s);
            System.out.println("Added from " + num + ": " + s);
        }
    }



}
