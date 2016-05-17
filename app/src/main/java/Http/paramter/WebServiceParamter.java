package Http.paramter;

import Http.Interface.IParamter;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public class WebServiceParamter {
    private IParamter mParamter;

    public WebServiceParamter(IParamter paramter){
        mParamter = paramter;
    }
    public String getServiceName(){
        return  mParamter.getServiceName();
    }
    public String getKey(){
        return mParamter.getKey();
    }
}
