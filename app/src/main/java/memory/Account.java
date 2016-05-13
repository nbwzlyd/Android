package memory;

import android.text.TextUtils;

/**
 * Created by lyd10892 on 2016/5/12.
 */
public class Account {

    public static final String sLogin = "1";

    private String mIsLogin = null;

    private String mName = null;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isLogin() {
        return TextUtils.equals(sLogin, mIsLogin);
    }
}
