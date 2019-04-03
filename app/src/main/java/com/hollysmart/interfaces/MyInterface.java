package com.hollysmart.interfaces;

import android.graphics.Bitmap;
import android.view.View;

import com.hollysmart.views.ObservableScrollView;

/**
 * Created by cai on 16/10/11.
 */

public class MyInterface {

    public interface PopupIF {
        void onListener();

        void item(int position);
    }

    public interface CheckUserIF {
        void isDone(int code);
    }

    public interface CaptchaIF {
        void toResult(String token, Bitmap bitmap);
    }

    public interface ResetPasswordIF {
        void isDone(int code);
    }

    public interface UserTokenIF {
        void isDone(int code);
    }

    public interface UserResetIF {
        void isDone(int code);
    }


    public interface CommentPostIF {
        void isDone(int code);
    }

    public interface CommentDeleteIF {
        void isDone(int code);
    }

    public interface CommentSaygoodIF {
        void isDone(int code);
    }

    public interface CommentDelSaygoodIF {
        void isDone(int code);
    }


    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }



    public interface LiXianDataDownloadIF {
        void onDataDownloadSuccess(String appkey);

        void onDataDownloadFailed(String appkey);
    }

    public interface PicUploadIF {
        void onPicUploadSuccess(int type, boolean zujiFlag);
    }

    public interface OnClickMarker {
        void ocDetail(int index);

        void ocQuanjing(String title);

        void ocPlayer(View view);

        void ocPic(int id);

        void ocKuaiTui();

        void ocKuaiJing();

    }


    public interface RecycleItemClickListener {
        void onItemClick(View view, int position);
    }



    public interface OnMoreClickInterface{
        void onMoreClick();
    }

}






















