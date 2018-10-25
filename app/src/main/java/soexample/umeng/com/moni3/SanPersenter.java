package soexample.umeng.com.moni3;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import soexample.umeng.com.moni3.mvp.view.Agete;

public class SanPersenter extends Agete {
    private static final int MAG = 123;
    private int i = 3;
    private TextView textView;
    private MyHanlder myHanlder = new MyHanlder();


    @Override
    public int getLayoutId() {
        return R.layout.san;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        textView = (TextView) get(R.id.miao);
        myHanlder.sendEmptyMessageDelayed(0, 1000);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(SanPersenter.this, deng.class));

            }
        });

    }

    class MyHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i--;
            textView.setText(i + "s");
            if (i == 0) {
                myHanlder.removeCallbacksAndMessages(null);
                context.startActivity(new Intent(context, deng.class));

            } else {
                myHanlder.sendEmptyMessageDelayed(0, 1000);
            }
        }

    }
}
