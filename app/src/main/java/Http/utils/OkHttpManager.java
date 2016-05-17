package Http.utils;

import android.content.Entity;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import Http.callBack.ResponseCallBack;
import Http.cookie.PersistentCookieStore;
import Http.paramter.WebServiceParamter;

/**
 * Created by lyd10892 on 2016/5/13.
 */
public class OkHttpManager extends OkHttpClient {

    private OkHttpClient mOkHttpClient = null;
    private static OkHttpManager mOkHttpManager = null;
    private PersistentCookieStore mCookieStore = null;
    private Handler mHandler = null;
    private Gson mGson = null;


    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(3000, TimeUnit.SECONDS);
        mOkHttpClient.setCookieHandler(new CookieManager(mCookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mHandler = new Handler();
        mGson = new Gson();
    }

    public static OkHttpManager getInstance() {
        if (mOkHttpManager == null) {
            mOkHttpManager = new OkHttpManager();
        }

        return mOkHttpManager;
    }

    public void getFromUrl(WebServiceParamter parameter, final ResponseCallBack callBack) {
        Request request = new Request.Builder().url(parameter.getServiceName()).build();
        handleRequest(request, callBack);
    }

    public void sendPost(final WebServiceParamter parameter, Object requestBody, final ResponseCallBack callBack) {
        //http://api.avatardata.cn/GuoNeiNews/Query?key=f96a4ae7bc36496198471c7490d5be19&page=1&rows=1

        JsonObject object = (JsonObject) mGson.toJsonTree(requestBody);
        Set<Map.Entry<String, JsonElement>> set = object.entrySet();
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, JsonElement> entry : set) {
            builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(parameter.getServiceName() + "?key=" + parameter.getKey() + builder.toString()).build();
                handleRequest(request, callBack);
            }
        }).start();

    }


    private void handleRequest(final Request request, final ResponseCallBack callBack) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverFailure(request, callBack, e);
            }

            @Override
            public void onResponse(Response Response) throws IOException {
                String responseBody = Response.body().string();
                Object object = mGson.fromJson(responseBody, callBack.mType);
                deliverSuccess(object, callBack);

            }
        });
    }

    private void deliverSuccess(final Object object, final ResponseCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(object);
                }
            }
        });
    }


    private void deliverFailure(final Request request, final ResponseCallBack callBack, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(request, e);
                }

            }
        });
    }
}
