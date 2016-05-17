package base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import Http.callBack.ResponseCallBack;
import Http.paramter.WebServiceParamter;
import Http.utils.OkHttpManager;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public abstract  class BaseActivity extends AppCompatActivity {

    private OkHttpManager mOkManager = null;
    public Context mContext;
    public LayoutInflater mInflater = null;

//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(this);
        initData();
    }

    private void initData() {
        mOkManager = OkHttpManager.getInstance();
    }

    public void sendRequest(final WebServiceParamter parameter, Object requestBody, final ResponseCallBack callBack){
        mOkManager.sendPost(parameter,requestBody,callBack);
    }

}
