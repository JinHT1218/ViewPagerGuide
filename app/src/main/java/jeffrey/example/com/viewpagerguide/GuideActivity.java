package jeffrey.example.com.viewpagerguide;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private List<View> mViews = new ArrayList<>();
    private List<ImageView> mImageViews = new ArrayList<>();
    private int[]  imgRes = new int[]{
            R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4
    };
    private Button mButton;
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_guide);
        initpoint();
        initImg();
        mViewPager =  findViewById(R.id.guide_viewpager);
        mViewPager.setAdapter(new viewpagerAdapter());
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mButton =  findViewById(R.id.guide_start);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this,Start.class));
                finish();
            }
        });
    }
    private void initImg() {
        for (int i = 0; i < imgRes.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.viewpager_item_view,null);
            ImageView imageView = view.findViewById(R.id.guide_imageview);
            imageView.setBackgroundResource(imgRes[i]);
            mViews.add(view);
        }
    }
    private void initpoint() {
        //获取layout
        mLayout = findViewById(R.id.point_ly);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置每一个view即圆点的对左的偏移量
        params.setMargins(15,0,0,0);
        //根据图片多少来确定个数
        for (int i = 0; i < imgRes.length; i++) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dot_select);
            imageView.setLayoutParams(params); //把上面的控件属性设置到LinearLayout中
            if (i == 0){ //默认第一张为红色圆点
                imageView.setSelected(true);
            }else{
                imageView.setSelected(false);
            }
            mLayout.addView(imageView);
            mImageViews.add(imageView);//跟着viewpager变换颜色
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动时改变圆点的状态
        for (int i = 0; i < mImageViews.size(); i++) {
            if (i == position){
                mImageViews.get(i).setSelected(true);
            }else{
                mImageViews.get(i).setSelected(false);
            }
        }
        //当为最后一个时，显示button，并隐藏圆点
        if (position == mImageViews.size() -1){
            mLayout.setVisibility(View.GONE);
            mButton.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(mButton,"alpha",0f,1f);
            animator.setDuration(1000);
            animator.start();
        }else{
            mLayout.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.GONE);
        }
    }
    @Override
    public void onPageSelected(int position) {
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    class viewpagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mViews.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            container.addView(mViews.get(position));
            return  mViews.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView(mViews.get(position));
        }
    }
}
