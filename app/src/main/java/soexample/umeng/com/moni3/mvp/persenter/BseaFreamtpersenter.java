package soexample.umeng.com.moni3.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.moni3.mvp.view.Agete;

public abstract class BseaFreamtpersenter<T extends Agete> extends Fragment {
    private T delde;

    public abstract Class<T> getClassAgeid();

    public BseaFreamtpersenter() {
        try {
            delde = getClassAgeid().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delde.create(inflater, container, savedInstanceState);
        delde.getContext(getActivity());
        delde.initData();
        return delde.rootView();

    }
}
