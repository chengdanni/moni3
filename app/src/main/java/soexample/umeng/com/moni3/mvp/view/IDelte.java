package soexample.umeng.com.moni3.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IDelte {

    void initData();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View rootView();

    void getContext(Context context);
}
