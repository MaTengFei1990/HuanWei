package com.hollysmart.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.hollysmart.values.ImageInfo;

import java.util.List;

public class DetailVPAdapter extends PagerAdapter {
    private List<ImageInfo> imageInfos;
    private Context context;
    private boolean isLoction;
    private DetailVPIF detailVPIF;
    public DetailVPAdapter(Context context, boolean isLoction, List<ImageInfo> imageInfos, DetailVPIF detailVPIF) {
        this.context = context;
        this.isLoction = isLoction;
        this.imageInfos = imageInfos;
        this.detailVPIF = detailVPIF;
    }

    @Override
    public int getCount() {
        return imageInfos.size() == 0 ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

//        View arg1 = LayoutInflater.from(context).inflate(R.layout.view_detailgallery_item, null);
//        final ImageView iv_item_gallery = (ImageView) arg1.findViewById(R.id.cai_iv_gallery);
//        if (imageInfos.get(position % imageInfos.size()).getThumb_url() != null) {
//            if (isLoction){
////                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(imageInfos.get(position % imageInfos.size()).getThumb_url()), iv_item_gallery, options);
//                Glide.with(context).load(new File(imageInfos.get(position % imageInfos.size()).getThumb_url()))
//                        .placeholder(R.mipmap.morentu402)
//                        .error(R.mipmap.morentu402)
//                        .crossFade()
//                        .into(iv_item_gallery);
//            }else {
////                ImageLoader.getInstance().displayImage(PicDictToll.getUrl(imageInfos.get(position % imageInfos.size()).getThumb_url(),PicDictToll.PIC_405), iv_item_gallery, options);
//                Glide.with(context).load(PicDictToll.getUrl(imageInfos.get(position % imageInfos.size()).getThumb_url(), PicDictToll.PIC_405))
//                        .placeholder(R.mipmap.morentu402)
//                        .error(R.mipmap.morentu402)
//                        .crossFade()
//                        .into(iv_item_gallery);
//            }
//        } else {
//            iv_item_gallery.setImageResource(R.mipmap.morentu402);
//        }
//        arg1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                detailVPIF.position(position % imageInfos.size());
//            }
//        });
//
//        container.addView(arg1, 0);
//        return arg1;
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public interface DetailVPIF {
        void position(int position);
    }
}