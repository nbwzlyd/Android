package Http.Interface;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public enum  NewsParamter implements  IParamter {

    NEWS_PARAMTER("http://api.avatardata.cn/GuoNeiNews/Query","f96a4ae7bc36496198471c7490d5be19");

    String serviceName;
    String key;
    NewsParamter(String serviceName,String key){
        this.serviceName = serviceName;
        this.key = key;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getKey() {
        return key;
    }
}
