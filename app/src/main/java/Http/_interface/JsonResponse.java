package Http._interface;

import java.util.ArrayList;

/**
 * Created by lyd10892 on 2016/5/10.
 */
public class JsonResponse<T> {
    public String resultCode;
    public String reason;
    public ArrayList<T> result;
}
