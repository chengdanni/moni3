package soexample.umeng.com.moni3.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.GouwuBean;

public class GouwucheAdapter extends RecyclerView.Adapter<GouwucheAdapter.mViewHolder> {
    private List<GouwuBean.DataBean> list = new ArrayList<>();
    private Context context;

    public GouwucheAdapter(Context context) {
        this.context = context;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gouwuche, null);
        mViewHolder myViewHoler = new mViewHolder(view);
        return myViewHoler;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int i) {
        holder.sellerName.setText(list.get(i).getSellerName());//设置商家的名字
        GoushopAdepter gouwuche = new GoushopAdepter(context, list.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(gouwuche);
        gouwuche.setListener(new GoushopAdepter.ShopCallBackListener() {
            @Override
            public void callBack() {
                listener.callBack(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GouwuBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        private TextView sellerName;
        private RecyclerView mRecyclerView;

        public mViewHolder(View itemView) {
            super(itemView);
            sellerName = (TextView) itemView.findViewById(R.id.seller_name);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.seller_recyclerview);
        }
    }

    //传递list集合
    private ShopCallBackListener listener;

    public void setListener(ShopCallBackListener listener) {
        this.listener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GouwuBean.DataBean> list);
    }


}
