package soexample.umeng.com.moni3.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import soexample.umeng.com.moni3.mvp.view.Agete;

public abstract class BseaAcivitypersenter<T extends Agete> extends AppCompatActivity {

    private T delde;

    public abstract Class<T> getClassAgeid();

    public BseaAcivitypersenter() {
        try {
            delde = getClassAgeid().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delde.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(delde.rootView());
        delde.getContext(this);
        delde.initData();
    }

}
