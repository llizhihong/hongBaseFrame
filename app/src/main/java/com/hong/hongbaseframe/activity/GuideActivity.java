package com.hong.hongbaseframe.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hong.hongbaseframe.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/7/27 11:00
 * 描述：
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.viewpage)
    ViewPager viewPager;
    @Bind(R.id.bt_guide_start)
    Button startButton;
    @Bind(R.id.indicator)
    LinearLayout indicatorLayout;
    private PagerAdapter pagerAdapter;
    private ArrayList<View> views;
    private ImageView[] indicators = null;
    private Bitmap[] bitmaps;
    private EdgeEffectCompat rightEdge;

    @Override
    protected int setContentView() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        // 设置引导图片
        bitmaps = new Bitmap[] { BitmapFactory.decodeResource(getResources(), R.mipmap.guide1), BitmapFactory.decodeResource(
                getResources(), R.mipmap.guide2), BitmapFactory.decodeResource(getResources(), R.mipmap.guide3)};
        // 实例化视图控件
        views = new ArrayList<View>();
        indicators = new ImageView[bitmaps.length]; // 定义指示器数组大小
        for (int i = 0; i < bitmaps.length; i++) {
            LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT,10);
            params_linear.setMargins(0, 0, 5, 0);
            // 循环加入图片
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(bitmaps[i]);
            views.add(imageView);
            // 循环加入指示器
            indicators[i] = new ImageView(context);
            indicators[i].setBackgroundResource(R.mipmap.image_undian);
            indicators[i].setLayoutParams(params_linear);
            if (i == 0) {
                indicators[i].setBackgroundResource(R.mipmap.image_selectdian);
            }
            indicatorLayout.addView(indicators[i]);
        }

        pagerAdapter = new Adapter_Guide(views);
        viewPager.setAdapter(pagerAdapter); // 设置适配器
        viewPager.setOnPageChangeListener(this);
        setviewPager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_guide_start:
                startActivity(LoginActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if(rightEdge!=null&&!rightEdge.isFinished()){//到了最后一张并且还继续拖动，出现蓝色限制边条了
            startActivity(LoginActivity.class);
            finish();
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    // 监听viewpage
    @Override
    public void onPageSelected(int arg0) {
        // 显示最后一个图片时显示按钮
        if (arg0 == indicators.length - 1) {
            startButton.setVisibility(View.VISIBLE);
        } else {
            startButton.setVisibility(View.INVISIBLE);
        }
        // 更改指示器图片
        for (int i = 0; i < indicators.length; i++) {
            indicators[arg0].setBackgroundResource(R.mipmap.indicators_now);
            if (arg0 != i) {
                indicators[i].setBackgroundResource(R.mipmap.indicators_default);
            }
        }
    }

    public class Adapter_Guide extends PagerAdapter{
        private List<View> views = new ArrayList<View>();

        public Adapter_Guide(List<View> views){
            this.views=views;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position));
            if(position == views.size() - 1){
                views.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(LoginActivity.class);
                        finish();
                    }
                });
            }
            return views.get(position);
        }
    }

    /**
     * @描述:滑动到ViewPager末尾时，右滑进入登录
     */
    private void setviewPager(){
        try {
            Field leftEdgeField = viewPager.getClass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = viewPager.getClass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);

                /*最左边EdgeEffectCompat leftEdge = (EdgeEffectCompat) leftEdgeField.get(viewPager);*/
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(viewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListner() {

    }

    @Override
    public void onSuccuss(String tag, String code, String msg, String resultData) {

    }

    @Override
    public void onFailure(String tag, String code, String msg) {

    }

    @Override
    public void onProgress(float progress) {

    }
}
