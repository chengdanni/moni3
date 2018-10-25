package soexample.umeng.com.moni3.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Agete implements IDelte {

    private View rootView;

    @Override
    public void initData() {

    }


    private SparseArray<View> views = new SparseArray<>();

    public <T extends View> T get(int id) {
        T view = (T) views.get(id);
        if (view == null) {
            view = rootView().findViewById(id);
            views.put(id, view);

        }
        return view;
    }

    public void setCilck(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View rootView() {
        return rootView;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public abstract int getLayoutId();

}
