package com.bizzan.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.bizzan.ui.home.MainActivity;
import com.bizzan.R;
import com.bizzan.utils.SharedPreferenceInstance;

import java.util.ArrayList;


public class LeadActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager vPager;
    private VpAdapter vpAdapter;
    private ImageView btnStartExperience;
//    private static int [] imgs = {R.drawable.ic_cm_start,R.drawable.ic_cm_two,R.drawable.ic_cm_three};//要显示的图片资源
    private static int [] imgs = {};//要显示的图片资源
    private ArrayList<ImageView> imageViews;//用于包含引导页要显示的图片
    private ImageView[] dotViews;//用于包含底部小圆点的图片，只要设置每个imageview的图片资源为刚刚写的dot_selector，选择和没选中就会有不同的效果，实现导航的功能。

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LeadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        vPager = (ViewPager)findViewById(R.id.my_viewpager);
        btnStartExperience = (ImageView) findViewById(R.id.btn_guide_start_experience);
        initImages();
        initDots();

        vpAdapter = new VpAdapter(imageViews);
        vPager.setAdapter(vpAdapter);
        vPager.setOnPageChangeListener(this);
        btnStartExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intoApp();
            }
        });
    }

    private void intoApp() {
        SharedPreferenceInstance.getInstance().saveIsFirstUse(false);
        MainActivity.actionStart(LeadActivity.this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 把引导页要显示的图片添加到集合中，以传递给适配器，用来显示图片。
     */

    private void initImages()
    {
        LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);//设置每一张图片都填充窗口
        imageViews = new ArrayList<ImageView>();

        for(int i = 0; i < imgs.length; i++)
        {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);//设置布局
            iv.setImageResource(imgs[i]);//为Imageview添加图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片拉伸效果
            imageViews.add(iv);
            if(i== imgs.length - 1)//为最后一张添加点击事件
            {
                iv.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(MainActivity.this, "跳转。。。", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * 根据引导页的数量，动态生成相应数量的导航小圆点，并添加到LinearLayout中显示。
     */
    private void initDots()
    {
        LinearLayout layout = findViewById(R.id.dot_layout);
        LayoutParams mParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔

        dotViews = new ImageView[imgs.length];

        for(int i = 0; i < imageViews.size(); i++)
        {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.dot_selector);
            if(i== 0)
            {
                imageView.setSelected(true);//默认启动时，选中第一个小圆点
            }
            else {
                imageView.setSelected(false);
            }
            dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            layout.addView(imageView);//添加到布局里面显示
        }

    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    /**
     * arg0：当前滑动显示页面的索引值，可以根据这个值，来设置相应小圆点的状态。
     */
    @Override
    public void onPageSelected(int arg0) {
        for(int i = 0; i < dotViews.length; i++)
        {
            if(arg0 == i)
            {
                dotViews[i].setSelected(true);
            }
            else {
                dotViews[i].setSelected(false);
            }
        }
        if (arg0 == dotViews.length - 1) {
            btnStartExperience.setVisibility(View.VISIBLE);// 显示
        } else {
            btnStartExperience.setVisibility(View.GONE);// 隐藏
        }
    }


    class VpAdapter extends PagerAdapter {

        private ArrayList<ImageView> imageViews;

        public VpAdapter(ArrayList<ImageView> imageViews) {
            this.imageViews = imageViews;
        }

        /**
         * 获取当前要显示对象的数量
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断是否用对象生成界面
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 从ViewGroup中移除当前对象（图片）
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

        /**
         * 当前要显示的对象（图片）
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

    }
}
