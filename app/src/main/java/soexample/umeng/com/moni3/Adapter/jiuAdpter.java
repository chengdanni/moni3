package soexample.umeng.com.moni3.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.JiuBean;

public class jiuAdpter extends RecyclerView.Adapter<jiuAdpter.Mviewhpler> {
    private List<JiuBean.DataBean> list = new ArrayList<>();
    private Context context;

    public jiuAdpter(List<JiuBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Mviewhpler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.jiu, null);
        Mviewhpler vi = new Mviewhpler(view);
        return vi;

    }

    @Override
    public void onBindViewHolder(Mviewhpler holder, int position) {
        holder.textView.setText(list.get(position).getName());
        String images = list.get(position).getIcon();
        String[] split = images.split("\\|");
        Picasso.with(context).load(split[0]).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Mviewhpler extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;

        public Mviewhpler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imng);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
