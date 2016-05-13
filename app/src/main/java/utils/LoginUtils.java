package utils;


import android.content.Context;

import memory.MyApplication;

/**
 * Created by lyd10892 on 2016/5/12.
 */
public class LoginUtils {

    private static LoginUtils sLoginUtils = null;
    private static MyApplication mContext;

    private LoginUtils() {
    }

    public static LoginUtils getInstance(Context context) {
        if (sLoginUtils == null) {
            sLoginUtils = new LoginUtils();
        }
        mContext = (MyApplication) context;
        return sLoginUtils;
    }

    public boolean isLogin() {
        return mContext.getAccount().isLogin();

    }

    public String getName() {

        return mContext.getAccount().getName();
    }

}
