package com.hollysmart.views;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hollysmart.adapters.DetailVPAdapter;
import com.hollysmart.huanwei.R;
import com.hollysmart.values.ImageInfo;

import java.util.List;

/**
 * Created by cai on 15/10/21
 */
public class DetailPicViewPager {

    private Context mContext;
    private boolean isLoction;
    private ImageView iv_def;
    private ViewPager vp_pic;
    private LinearLayout ll_point;
    private List<ImageInfo> imageInfos;
    private OnItemOnClickListener onItemOnClickListener;

    public DetailPicViewPager(Context mContext, boolean isLoction, ImageView iv_def, LinearLayout ll_point, ViewPager vp_pic, List<ImageInfo> imageInfos) {
        this.mContext = mContext;
        this.isLoction = isLoction;
        this.iv_def = iv_def;
        this.ll_point = ll_point;
        this.vp_pic = vp_pic;
        this.imageInfos = imageInfos;
        initPics();
    }

    public DetailPicViewPager(Context mContext, ImageView iv_def, LinearLayout ll_point, ViewPager vp_pic, List<ImageInfo> imageInfos) {
        this.mContext = mContext;
        this.iv_def = iv_def;
        this.ll_point = ll_point;
        this.vp_pic = vp_pic;
        this.imageInfos = imageInfos;
        initPics();
    }

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    //  初始化题图
    private int index;
    private ImageView iv[];

    private void initPics() {
        if (imageInfos.size() != 0)
            iv_def.setVisibility(View.GONE);
        iv = new ImageView[imageInfos.size()];
        ll_point.removeAllViews();
        for (int i = 0; i < imageInfos.size(); i++) {
            iv[i] = new ImageView(mContext);
            if (i == 0) {
                iv[i].setBackgroundResource(R.mipmap.pic_point_on);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.rightMargin = 8;
                iv[i].setLayoutParams(layout);
            } else {
                iv[i].setBackgroundResource(R.mipmap.pic_point_off);
                if (i != imageInfos.size() - 1) {
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layout.rightMargin = 8;
                    iv[i].setLayoutParams(layout);
                }
            }
            ll_point.addView(iv[i]);
        }
        vp_pic.setAdapter(new DetailVPAdapter(mContext, isLoction, imageInfos, new DetailVPAdapter.DetailVPIF() {
            @Override
            public void position(int position) {
//                Intent intent = new Intent(mContext, BigPicActivity.class);
//                intent.putExtra("index", position);
//                intent.putExtra("isLoction", isLoction);
//                intent.putExtra("infos", (Serializable) imageInfos);
//                intent.putExtra("animType", 4);
//                mContext.startActivity(intent);
                if (onItemOnClickListener != null)
                    onItemOnClickListener.position(position);
            }
        }));
        vp_pic.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int pos) {
                index = pos;
                if (iv != null) {
                    for (int i = 0; i < iv.length; i++) {
                        if (i == pos % imageInfos.size()) {
                            iv[i].setBackgroundResource(R.mipmap.pic_point_on);
                        } else {
                            iv[i].setBackgroundResource(R.mipmap.pic_point_off);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    public void notyfyDataChange() {
        initPics();
    }

    private boolean zidong;

    public boolean isZidong() {
        return zidong;
    }

    public void setZidong(boolean zidong) {
        this.zidong = zidong;
        xunHuan();
    }

    public void xunHuan() {
        if (zidong) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    vp_pic.setCurrentItem(index++);
                    xunHuan();
                }
            }, 3000);
        }
    }

    public interface OnItemOnClickListener {
        void position(int position);
    }

}
