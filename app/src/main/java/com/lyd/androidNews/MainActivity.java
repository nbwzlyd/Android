package com.lyd.androidNews;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

import Http.Interface.NewsParamter;
import Http.callBack.ResponseCallBack;
import Http.entity.NewsReqBody;
import Http.entity.NewsResBody;
import Http.paramter.WebServiceParamter;
import base.BaseActivity;
import utils.Utils;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView = null;
    //
    private NewsAdapter mNewsAdapter = null;

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
//        requestNewsData();

    }

    private void initData() {
        mNewsAdapter = new NewsAdapter();
        mRecyclerView.setAdapter(mNewsAdapter);

    }


    private void requestNewsData() {
        final NewsReqBody reqBody = new NewsReqBody();
        reqBody.page = 1;
        sendRequest(new WebServiceParamter(NewsParamter.NEWS_PARAMTER), reqBody, new ResponseCallBack<NewsResBody>() {
            @Override
            public void onSuccess(NewsResBody response) {
                mNewsAdapter.setData(response.result);
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });

    }

    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemViewHolder> {

        private List<NewsResBody.Data> mList;
        public void setData(List<NewsResBody.Data> list){
            mList = list;
            notifyDataSetChanged();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.cardview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.titleView.setText(mList.get(position).title);
            holder.timeView.setText(mList.get(position).ctime);
        }


        @Override
        public int getItemCount() {
            return Utils.isEmpty(mList) ? 0 : mList.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView = null;
            public TextView titleView = null;
            public TextView timeView = null;

            public ItemViewHolder(View itemView) {
                super(itemView);
                initItemView(itemView);
            }

            private void initItemView(View itemView) {
                imageView = (ImageView) itemView.findViewById(R.id.news_iv);
                titleView = (TextView) itemView.findViewById(R.id.news_title);
                timeView = (TextView) itemView.findViewById(R.id.news_time);
            }
        }
    }


}
