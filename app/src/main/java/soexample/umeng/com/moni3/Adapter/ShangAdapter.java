package soexample.umeng.com.moni3.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.bean.shangBean;

public class ShangAdapter extends RecyclerView.Adapter<ShangAdapter.viewHolder> {
    private Context context;
    private List<shangBean.DataBean.ListBean> list = new ArrayList<>();


    public ShangAdapter(Context context, List<shangBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shang, null);
        viewHolder vi = new viewHolder(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        holder.textView.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        //String replace = images.replace("https", "http");

        String[] split = images.split("\\|");
        Log.i("aaaaa", split[0]);
        Glide.with(context).load(split[0]).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public viewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.textshang);
        }
    }
}
