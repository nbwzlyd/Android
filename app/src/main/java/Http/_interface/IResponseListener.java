package Http._interface;

/**
 * Created by lyd10892 on 2016/5/10.
 */
public interface IResponseListener {
    void onSuccess(String code, String reason, JsonResponse jsonResponse);

    void onFailuer(String code, String reason, JsonResponse jsonResponse);
}
