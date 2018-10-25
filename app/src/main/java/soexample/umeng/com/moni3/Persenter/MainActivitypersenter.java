package soexample.umeng.com.moni3.Persenter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.Fragment.LeibiaoFreamer;
import soexample.umeng.com.moni3.Acivitu.MainActivity;
import soexample.umeng.com.moni3.Fragment.MiFramnter;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.Fragment.ShopFramner;
import soexample.umeng.com.moni3.Fragment.ShouyeFramnter;
import soexample.umeng.com.moni3.mvp.view.Agete;

public class MainActivitypersenter extends Agete {
    private ViewPager viewPager;
    private ImageView img1, img2, img3, img4;
    private List<Fragment> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        viewPager = get(R.id.pager);
        img1 = get(R.id.iv_img1_main);
        img2 = get(R.id.iv_img2_main);
        img3 = get(R.id.iv_img3_main);
        img4 = get(R.id.iv_img4_main);
        list.add(new ShouyeFramnter());
        list.add(new LeibiaoFreamer());
        list.add(new ShopFramner());
        list.add(new MiFramnter());
        MyAdapterFragment myAdapterFragment = new MyAdapterFragment(((MainActivity) context).getSupportFragmentManager());
        viewPager.setAdapter(myAdapterFragment);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                img1.setImageResource(R.drawable.index_yes);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
            }
        }, R.id.iv_img1_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_yes);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
            }
        }, R.id.iv_img2_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_yes);
                img4.setImageResource(R.drawable.me_no);
            }
        }, R.id.iv_img3_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_yes);
            }
        }, R.id.iv_img4_main);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    img1.setImageResource(R.drawable.index_yes);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                } else if (1 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_yes);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                } else if (2 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_yes);
                    img4.setImageResource(R.drawable.me_no);
                } else if (3 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_yes);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private class MyAdapterFragment extends FragmentPagerAdapter {

        public MyAdapterFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }
}
