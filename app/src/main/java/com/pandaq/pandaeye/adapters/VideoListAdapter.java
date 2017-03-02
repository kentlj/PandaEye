package com.pandaq.pandaeye.adapters;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pandaq.pandaeye.R;
import com.pandaq.pandaeye.entity.movie.RetDataBean;
import com.pandaq.pandaeye.utils.DensityUtil;
import com.pandaq.pandaeye.utils.LogWritter;
import com.pandaq.pandaqlib.magicrecyclerView.BaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PandaQ on 2017/3/1.
 * 视频主界面 recyclerView 的 adapter
 */

public class VideoListAdapter extends BaseRecyclerAdapter<RetDataBean.ListBean> {

    private Context mContext;
    private int image_width;
    private int image_height;

    public VideoListAdapter(Fragment fragment) {
        mContext = fragment.getContext();
        float height = fragment.getResources().getDimension(R.dimen.video_type_image_width);
        image_height = DensityUtil.dip2px(mContext, height);
        image_width = image_height * 4 / 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, RetDataBean.ListBean data) {
        ViewHolder holder = (ViewHolder) viewHolder;
        String pic = data.getChildList().get(0).getPic();
        Picasso.with(mContext)
                .load(pic) //加载第一张图
                .centerCrop()
                .resize(image_width, image_height)
                .into(holder.mIvVideoType);
        holder.mTvVideoType.setText(data.getTitle());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_video_type)
        ImageView mIvVideoType;
        @BindView(R.id.tv_video_type)
        TextView mTvVideoType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}