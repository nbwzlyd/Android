package Http.utils;

import android.os.Handler;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import Http._interface.IResponseListener;
import Http.cookie.PersistentCookieStore;

/**
 * Created by lyd10892 on 2016/5/13.
 */
public class OkHttpManager extends OkHttpClient{

    private OkHttpClient mOkHttpClient = null;
    private static OkHttpManager mOkHttpManager =null;
    private PersistentCookieStore mCookieStore = null;
    private Handler mHandler = null;
    private Gson mGson = null;


    private OkHttpManager(){
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(3000, TimeUnit.SECONDS);
        mOkHttpClient.setCookieHandler(new CookieManager(mCookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mHandler = new Handler();
        mGson = new Gson();
    }

    public static OkHttpManager getInstance(){
        if (mOkHttpManager==null){
            mOkHttpManager = new OkHttpManager();
        }

        return mOkHttpManager;
    }

    public void getFromUrl(String url, final IResponseListener listener){
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverFailure(request,listener);
            }

            @Override
            public void onResponse(Response Response) throws IOException {
            }
        });

    }

    public void sendPost(){
    }



    private void deliverSuccess(final Response Response , final IResponseListener listener){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener!=null){
//                    listener.onSuccess(request,listener);
                }

            }
        });
    }


    private void deliverFailure(final Request request , final IResponseListener listener){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener!=null){
                    listener.onFailure(request);
                }

            }
        });
    }
}
