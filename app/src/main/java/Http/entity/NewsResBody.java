package Http.entity;

import java.util.ArrayList;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public class NewsResBody {
    public String code;
    public String reason;
    public ArrayList<Data> result;

    public class Data {
        public String ctime;//": "2015-10-21 12:56",
        public String title;//: "李宝荣任国管局局长、党组书记 焦焕成到龄卸任",
        public String description;//"//: 10月20日，国管局召开领导干部会议，宣布中共中央关于国管局主要领导调整的决定，李宝荣同志任国管局局长、党组书记，焦焕成同志已到任职年龄，不再担任国管局局长、党组书记职务。焦焕成同志表示，衷心拥护中央的决定。在国管局工作的30多年，是人生经",
        public String picUrl;//: "http://s.cimg.163.com/cnews/2015/10/21/2015102111454712c3a_550.jpg.119x83.jpg",
        public String url;//: "http://news.163.com/15/1021/12/B6F0FNPP00014AED.html#f=dlist"
    }
}
