package soexample.umeng.com.moni3.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.LeiBean;

public class nvRigter extends RecyclerView.Adapter<nvRigter.mViewHolder> {
    private List<LeiBean.DataBean> list = new ArrayList<>();
    private Context context;

    public nvRigter(List<LeiBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rigth, null);
        mViewHolder vi = new mViewHolder(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        List<LeiBean.DataBean.ListBean> list = this.list.get(position).getList();
        holder.recyclerView.setAdapter(new Zinvright(list,context));
        //holder.textView.setText(list.get(position).getName());
        //  String images = list.get(position).getIcon();
        //      String[] split = images.split("\\|");
        //  Picasso.with(context).load(list.get(position).getList().get(0).getIcon()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;

//        private final ImageView imageView;
//        private final TextView textView;

        public mViewHolder(View itemView) {
            super(itemView);
//            imageView = (ImageView) itemView.findViewById(R.id.goods_pic);
//            textView = (TextView) itemView.findViewById(R.id.tu_rigth);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.view);
        }
    }

}
