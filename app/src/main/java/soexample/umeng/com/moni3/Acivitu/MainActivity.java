package soexample.umeng.com.moni3.Acivitu;

import soexample.umeng.com.moni3.Persenter.MainActivitypersenter;
import soexample.umeng.com.moni3.mvp.persenter.BseaAcivitypersenter;

public class MainActivity extends BseaAcivitypersenter<MainActivitypersenter> {


    @Override
    public Class<MainActivitypersenter> getClassAgeid() {
        return MainActivitypersenter.class;
    }

}
