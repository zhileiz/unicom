import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        Target tgt = new Target("http://www.dianping.com/search/around/1/10_2602612");
        tgt.crawl();
        tgt.printToCSV("output.csv");
    }

}
