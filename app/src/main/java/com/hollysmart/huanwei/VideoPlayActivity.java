package com.hollysmart.huanwei;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hollysmart.style.CaiSlidingBackActivity;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoPlayActivity extends CaiSlidingBackActivity implements View.OnClickListener {
//    private final String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";


    @Override
    public int layoutResID() {
        return R.layout.activity_video_play;
    }

    @Override
    public void findView() {
        findViewById(R.id.tv_home).setOnClickListener(this);
    }

    @Override
    public void init() {
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoView);
        jzVideoPlayerStandard.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "驾驶室");
        jzVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse( "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }
    }
        @Override
        public void onBackPressed() {
            if (JZVideoPlayer.backPress()) {
                return;
            }
            super.onBackPressed();
        }
        @Override
        protected void onPause() {
            super.onPause();
            JZVideoPlayer.releaseAllVideos();
        }


}
