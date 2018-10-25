package soexample.umeng.com.moni3.Persenter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import soexample.umeng.com.moni3.Acivitu.Deng;
import soexample.umeng.com.moni3.Acivitu.MainActivity;
import soexample.umeng.com.moni3.R;
import soexample.umeng.com.moni3.SanPersenter;
import soexample.umeng.com.moni3.deng;
import soexample.umeng.com.moni3.mvp.view.Agete;

public class MiFramnterpersenter extends Agete {
    private static final int MAG = 123;
    private int i = 3;
    private TextView textView;
    private MyHanlder myHanlder = new MyHanlder();

    @Override
    public int getLayoutId() {
        return R.layout.mi;
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
        Deng view = get(R.id.yuan);
        myHanlder.sendEmptyMessageDelayed(0, 1000);
        int width = ((MainActivity) context).getWindowManager().getDefaultDisplay().getWidth();

        int height = ((MainActivity) context).getWindowManager().getDefaultDisplay().getHeight();

        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, width);

        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, height);

        AnimatorSet set = new AnimatorSet();
        set.play(translationXAnimator).with(translationYAnimator);

        set.setDuration(3000);

        set.start();


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