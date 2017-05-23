
public class Shop {
    private String name; //店名
    private double[] scores; //评分
    private String category; //子分类
    private String parent; //大分类
    private String address; //地址

    public Shop(String name, String category, String parent, double[] scores, String address){
        this.name = name;
        this.category = category;
        this.parent = parent;
        this.scores = scores;
        this.address = address;
    }


    public String print(){
        return this.name + ","+
               this.category + "," +
               this.parent + "," +
               scores[0] + "," +
               scores[1] + "," +
               scores[2] + "," +
               this.address + "\n";
    }
}
