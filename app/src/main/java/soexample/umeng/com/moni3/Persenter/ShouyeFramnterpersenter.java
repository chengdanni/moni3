package soexample.umeng.com.moni3.Persenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.Adapter.ShangAdapter;
import soexample.umeng.com.moni3.Adapter.jiuAdpter;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.JiuBean;
import soexample.umeng.com.moni3.bean.LunBean;
import soexample.umeng.com.moni3.bean.shangBean;
import soexample.umeng.com.moni3.mvp.view.Agete;
import soexample.umeng.com.moni3.net.HttpHelper;

public class ShouyeFramnterpersenter extends Agete {

    private ViewPager view;
    private RecyclerView recyclerView;
    private RecyclerView liebiao;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int i = view.getCurrentItem();
                if (i < list.size() - 1) {
                    i++;
                } else {
                    i = 0;
                }
                view.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.shouye;
    }

    @Override
    public void initData() {
        super.initData();

        view = (ViewPager) get(R.id.view);
        recyclerView = (RecyclerView) get(R.id.Review);
        liebiao = (RecyclerView) get(R.id.Reviewliebiao);

        doHttp();
        Jiugong();
         Shangping();
        handler.sendEmptyMessageDelayed(0, 2000);
    }



    private MyAdate myAdate;
    private List<LunBean.DataBean> list = new ArrayList<>();

    //轮播适配
    class MyAdate extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            String icon = list.get(position).getIcon();
            String replace = icon.replace("https", "http");
            Picasso.with(context).load(replace).fit().into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    private void Shangping() {
        String shang = "http://www.zhaoapi.cn/product/getCarts?uid=71";
        new HttpHelper().get(shang).result(new HttpHelper.HttpListener() {
            private List<shangBean.DataBean.ListBean> list1 = new ArrayList<>();
            @Override
            public void success(String data) {
                shangBean tiao = new Gson().fromJson(data, shangBean.class);
                List<shangBean.DataBean> da = tiao.getData();
                for (int i = 0; i < da.size(); i++) {
                    if (da.get(i).getList() == null || da.get(i).getList().size() == 0) {

                    } else {
                        list1.addAll(da.get(i).getList());
                    }
                }

                ShangAdapter shangAdapter = new ShangAdapter(context, list1);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                liebiao.setLayoutManager(s);
                liebiao.setAdapter(shangAdapter);
            }
            @Override
            public void fail(String error) {
            }
        });

    }

    private void Jiugong() {
        String jiu = "http://www.zhaoapi.cn/product/getCatagory";
        new HttpHelper().get(jiu).result(new HttpHelper.HttpListener() {
            private jiuAdpter myAdapler;
            private List<JiuBean.DataBean> data2 = new ArrayList<>();
            @Override
            public void success(String data) {
                JiuBean tiao = new Gson().fromJson(data, JiuBean.class);
                data2 = tiao.getData();
                myAdapler = new jiuAdpter(data2, context);
                recyclerView.setAdapter(myAdapler);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(s);

            }

            @Override
            public void fail(String error) {
            }
        });
    }


    //上下文
    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    private void doHttp() {
        String url = "http://www.zhaoapi.cn/ad/getAd";
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                LunBean bean = new Gson().fromJson(data, LunBean.class);
                list = bean.getData();
                myAdate = new MyAdate();
                view.setAdapter(myAdate);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
