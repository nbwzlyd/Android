package memory;

import android.app.Application;

/**
 * Created by lyd10892 on 2016/5/12.
 */
public class MyApplication extends Application {
    private Account mAccount = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        mAccount = new Account();
    }

    public Account getAccount() {
        return mAccount;
    }
}
