package Http._interface;

import com.squareup.okhttp.Request;

/**
 * Created by lyd10892 on 2016/5/10.
 */
public interface IResponseListener {
    void onSuccess(String code, String reason, JsonResponse jsonResponse);

    void onFailure(Request request);
}
