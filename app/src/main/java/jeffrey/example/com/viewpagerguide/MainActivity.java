package jeffrey.example.com.viewpagerguide;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;

import static android.R.attr.start;
import static android.R.attr.x;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyVideoView vv_video;
    private TextView tv_view;
    private FrameLayout btn_next;
    private int startTime=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();
        initView();
        initData();
    }
    private void initData() {
        vv_video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.media));
        vv_video.start();
//        tv_view.setText(startTime + "");
//        new Thread() {
//            @Override
//            public void run() {
//                while (startTime>0) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    startTime--;
//                    mHandler.sendEmptyMessage(0);
//                }
//            }
//        }.start();
    }
    private void initView() {
        vv_video = findViewById(R.id.vv_video);
        tv_view = findViewById(R.id.tv_view);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }
//    @SuppressLint("HandlerLeak")
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    tv_view.setText(startTime+"");
////                    if(startTime==0){
////                       startActivity(new Intent(MainActivity.this,GuideActivity.class));
////                       finish();
////                    }
//                    break;
//            }
//        }
//    };
    @Override
    public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GuideActivity.class));
                finish();

        }
    }
