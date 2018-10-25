package soexample.umeng.com.moni3.Persenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.Adapter.nvAdapter;
import soexample.umeng.com.moni3.Adapter.nvRigter;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.JiuBean;
import soexample.umeng.com.moni3.bean.LeiBean;
import soexample.umeng.com.moni3.mvp.view.Agete;
import soexample.umeng.com.moni3.net.HttpHelper;

public class LeibiaoFreamerpersenter extends Agete {

    private RecyclerView listView;
    private RecyclerView xlistview;
    private nvAdapter myAdapler;

    @Override
    public int getLayoutId() {
        return R.layout.lei;
    }


    @Override
    public void initData() {
        super.initData();
        listView = (RecyclerView) get(R.id.listview);
        xlistview = (RecyclerView) get(R.id.xlistview);
        okHttp();

    }

    private void okHttp() {
        String lei = "http://www.zhaoapi.cn/product/getCatagory";
        new HttpHelper().get(lei).result(new HttpHelper.HttpListener() {
            List<JiuBean.DataBean> data2 = new ArrayList<>();
            @Override
            public void success(String data) {
                JiuBean tiao = new Gson().fromJson(data, JiuBean.class);
                data2 = tiao.getData();
                myAdapler = new nvAdapter(data2, context);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                listView.setLayoutManager(s);
                listView.setAdapter(myAdapler);
                myAdapler.result(new nvAdapter.Tablistener() {
                    @Override
                    public void setTabList(int cid) {
                        new HttpHelper().get("http://www.zhaoapi.cn/product/getProductCatagory?cid="+cid).result(new HttpHelper.HttpListener() {
                            @Override
                            public void success(String data) {
                                LeiBean bean = new Gson().fromJson(data, LeiBean.class);
                                List<LeiBean.DataBean> data1 = bean.getData();
                                Toast.makeText(context, "==", Toast.LENGTH_SHORT).show();
                                nvRigter nvRigter = new nvRigter(data1,context);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                                xlistview.setLayoutManager(gridLayoutManager);
                                xlistview.setAdapter(nvRigter);
                            }

                            @Override
                            public void fail(String error) {

                            }
                        });
                    }
                });

            }

            @Override
            public void fail(String error) {
            }
        });
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

}
