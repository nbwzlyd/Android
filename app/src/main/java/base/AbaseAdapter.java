package base;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

/**
 * Created by lyd10892 on 2016/5/17.
 */
public abstract class AbaseAdapter<T> extends BaseAdapter {

    private List<T> mList = new ArrayList<>();

    public void setData(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Utils.isEmpty(mList) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return Utils.isEmpty(mList) ? "" : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
