package memory;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by lyd10892 on 2016/5/12.
 */
public class MyApplication extends Application {
    private Account mAccount = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        ImageLoaderConfiguration configuration =ImageLoaderConfiguration.createDefault(this);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);
    }

    private void initData() {
        mAccount = new Account();
    }

    public Account getAccount() {
        return mAccount;
    }
}
