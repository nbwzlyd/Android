package com.lyd.androidNews;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import Http.Interface.NewsParamter;
import Http.callBack.ResponseCallBack;
import Http.entity.NewsReqBody;
import Http.entity.NewsResBody;
import Http.paramter.WebServiceParamter;
import adapter.AminTouchHelperCallBack;
import base.BaseActivity;
import utils.Utils;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView = null;
    //
    private NewsAdapter mNewsAdapter = null;

    private ImageLoader mImageLoader = null;
    private DisplayImageOptions mOptions = null;
    private List<NewsResBody.Data> mDatas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.newsList_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration(mContext,10));
        mNewsAdapter = new NewsAdapter();
        mNewsAdapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onClickListener(int position) {
                Utils.startActivity(MainActivity.this, DescriptionActivity.class, DescriptionActivity.getBundle(mDatas.get(position).url));
            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);
        ItemTouchHelper.Callback callback = new AminTouchHelperCallBack(mNewsAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
        //显示图片的配置
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mImageLoader = ImageLoader.getInstance();
        requestNewsData();

    }

    private void initData() {


    }


    private void requestNewsData() {
        final NewsReqBody reqBody = new NewsReqBody();
        reqBody.page = 1;
        sendRequest(new WebServiceParamter(NewsParamter.NEWS_PARAMTER), reqBody, new ResponseCallBack<NewsResBody>() {
            @Override
            public void onSuccess(NewsResBody response) {
                mDatas = response.result;
                mNewsAdapter.setData(mDatas);
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });

    }


    public class NewsAdapter extends RecyclerView.Adapter<ItemViewHolder> implements AminTouchHelperCallBack.moveItemListener {

        private List<NewsResBody.Data> mList;
        private onItemClickListener listener;

//        private List<Integer> tempList = null;


        public void setData(List<NewsResBody.Data> list) {
            mList = list;
//            tempList = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                tempList.add(i);
//            }
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.cardview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, final int position) {
            NewsResBody.Data data = mList.get(position);
            holder.titleView.setText(data.title);
            holder.timeView.setText(data.ctime);
            holder.sourceView.setText(data.description);
            mImageLoader.displayImage(data.picUrl, holder.imageView, mOptions);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickListener(position);
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
//            return 10;
            return Utils.isEmpty(mList) ? 0 : mList.size();
        }

        @Override
        public void onMove(int fromPos, int targetPos) {
            Collections.swap(mList, fromPos, targetPos);
            Log.i("==fromPos==", "" + fromPos + "|" + targetPos);
            notifyItemMoved(fromPos, targetPos);
        }

        @Override
        public void onSwiped(int position) {
            mList.remove(position);
//            notifyItemRemoved(position); 这个源position不会发生改变
            notifyDataSetChanged();//重新会绑定数据

        }


    }

    public interface onItemClickListener {

        void onClickListener(int position);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements AminTouchHelperCallBack.ItemSelectChangedListener {
        public ImageView imageView = null;
        public TextView titleView = null;
        public TextView timeView = null;
        public TextView sourceView = null;
        public CardView cardView = null;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            initItemView(itemView);
        }

        private void initItemView(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.news_iv);
            titleView = (TextView) itemView.findViewById(R.id.news_title);
            timeView = (TextView) itemView.findViewById(R.id.news_time);
            sourceView = (TextView) itemView.findViewById(R.id.news_source_view);
        }

        @Override
        public void onSelectChanged() {
            cardView.setCardBackgroundColor(getResources().getColor(R.color.bg_cell_button));

        }

        @Override
        public void onSelectChangedFinish() {
            cardView.setCardBackgroundColor(getResources().getColor(R.color.white));

        }
    }

}
