import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws IOException{
        Set<String> allStr = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        String[] urls = new String[2];
        urls[0] = "http://www.dianping.com/search/around/1/0_2602612";
        urls[1] = "http://www.dianping.com/search/around/1/0_8872865";
        Set<String> allUrls = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        ArrayList<Thread> preThreads = new ArrayList<Thread>();
        for (int i = 1; i < 4; i++) {
            preThreads.add(new Thread(new PreThread(i,allUrls)));
        }
        for (int i = 0; i < 3; i++) {
            preThreads.get(i).start();
        }
        for (int i = 0; i < 3; i++) {
            try {
                preThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        ArrayList<Thread> threads = new ArrayList<Thread>();
//        for (int i = 0; i < 2; i++) {
//            threads.add(new Thread(new CrawlThread(urls[i],i+1,allStr)));
//        }
//        for (int i = 0; i < 2; i++) {
//            threads.get(i).start();
//        }
//        System.out.println("\n\n##########################################################################" +
//                "\n################################## Threading!!! ##########################\n\n\n");
//        for (int i = 0; i < 2; i++) {
//            try {
//                threads.get(i).join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("\n\n\n##########################################################################" +
//                "\n################################## Finishing!!! ##########################\n\n\n");
//        printToCSV("output.csv",allStr);
    }

    public static void printToCSV(String file, Set<String> strs) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Iterator<String> it = strs.iterator();
        while (it.hasNext()){
            writer.write(it.next());
        }
    }

}
