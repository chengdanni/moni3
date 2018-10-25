package soexample.umeng.com.moni3.Persenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.Adapter.GouwucheAdapter;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.GouwuBean;
import soexample.umeng.com.moni3.mvp.view.Agete;
import soexample.umeng.com.moni3.net.HttpHelper;

public class ShopFramnerpersenter extends Agete implements View.OnClickListener {

    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private ImageView ivCricle;
    private TextView allPriceTxt;
    private TextView sunPrice;
    private List<GouwuBean.DataBean> list = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GouwucheAdapter mShopSellerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.shop;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        mRecyclerView = (RecyclerView) get(R.id.layout_top1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mShopSellerAdapter = new GouwucheAdapter(context);
        mRecyclerView.setAdapter(mShopSellerAdapter);

        doHttp();

        ivCricle = (ImageView) get(R.id.iv_cricle);
        allPriceTxt = (TextView) get(R.id.all_price);
        sunPrice = (TextView) get(R.id.sum_price_txt);
        get(R.id.layout_all).setOnClickListener(this);
        mShopSellerAdapter.setListener(new GouwucheAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<GouwuBean.DataBean> list) {
                double allPrice = 0;
                int num = 0;
                int numAll = 0;
                for (int a = 0; a < list.size(); a++) {
                    List<GouwuBean.DataBean.ListBean> listAll = list.get(a).getList();//获取商家里商品
                    for (int i = 0; i < listAll.size(); i++) {
                        numAll = numAll + listAll.get(i).getNum();
                        if (listAll.get(i).isCheck()) {//取选中的状态
                            allPrice = allPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                            num = num + listAll.get(i).getNum();
                        }
                    }
                }
                if (num < numAll) {//不是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                } else {
                    //是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;
                }
                //改变价格和个数
                allPriceTxt.setText("合计：" + allPrice);
                sunPrice.setText("去结算(" + num + ")");
            }
        });
    }

    private void doHttp() {
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                GouwuBean bean = new Gson().fromJson(data, GouwuBean.class);
                list = bean.getData();
                list.remove(0);
                mShopSellerAdapter.setList(list);
            }
            @Override
            public void fail(String error) {
            }
        });
    }
    private boolean isClick = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_all://全选
                if (isClick) {
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;
                    checkSeller(true);
                } else {
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                    checkSeller(false);
                }
                break;
        }
    }

    private void checkSeller(boolean bool){
        double allPrice=0;
        int num=0;
        for (int a=0;a<list.size();a++){
            List<GouwuBean.DataBean.ListBean> listAll = list.get(a).getList();
            for (int i = 0; i <listAll.size() ; i++) {
                listAll.get(i).setCheck(bool);
                allPrice=allPrice+(listAll.get(i).getPrice()*listAll.get(i).getNum());
                num=num+listAll.get(i).getNum();
            }
        }
        if(bool){
            allPriceTxt.setText("合计："+allPrice);
            sunPrice.setText("去结算("+num+")");
        }else{
            allPriceTxt.setText("合计：0.00");
            sunPrice.setText("去结算(0)");
        }
        mShopSellerAdapter.notifyDataSetChanged();
    }


}
