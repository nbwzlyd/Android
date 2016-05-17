package utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public class Utils {


    public static void startActivity(AppCompatActivity activity, Class cls){
        Intent intent = new Intent();
        intent.setClass(activity,cls);
        activity.startActivity(intent);
    }

    public static<T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
