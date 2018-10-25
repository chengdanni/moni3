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
import soexample.umeng.com.moni3.bean.GouwuBean;

public class GoushopAdepter extends RecyclerView.Adapter<GoushopAdepter.mViewHolder> {
    private Context context;
    private List<GouwuBean.DataBean.ListBean> list = new ArrayList<>();

    public GoushopAdepter(Context context, List<GouwuBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.goushop, null);
        mViewHolder myViewHolder = new mViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int i) {
        Picasso.with(context).load(list.get(i).getImages().split("\\|")[0]).fit().into(holder.carImage);
        holder.carTitle.setText(list.get(i).getTitle());
        holder.carPrice.setText(list.get(i).getPrice() + "");

        if (list.get(i).isCheck()) {//选中
            holder.carCricle.setImageResource(R.drawable.cricle_yes);
        } else {
            holder.carCricle.setImageResource(R.drawable.cricle_no);
        }

        //点击商品选中
        holder.carCricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                notifyItemChanged(i);
                listener.callBack();
            }

        });

        //设置自定义View里的Edit
        holder.shop.setData(this, list, i);
        holder.shop.setOnCallBack(new ShopCarPriceLayout.CallBackListener() {
            @Override
            public void callBack() {
                listener.callBack();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        private final TextView carTitle;
        private final ImageView carImage;
        private final TextView carPrice;
        private final ImageView carCricle;
        ShopCarPriceLayout shop;

        public mViewHolder(View itemView) {
            super(itemView);
            carImage = (ImageView) itemView.findViewById(R.id.car_image);
            carTitle = (TextView) itemView.findViewById(R.id.car_title);
            carPrice = (TextView) itemView.findViewById(R.id.car_price);
            carCricle = (ImageView) itemView.findViewById(R.id.car_cricle);
            shop = (ShopCarPriceLayout) itemView.findViewById(R.id.shopcarpricelayout);
        }
    }

    private ShopCallBackListener listener;

    public void setListener(ShopCallBackListener listener) {
        this.listener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }
}
