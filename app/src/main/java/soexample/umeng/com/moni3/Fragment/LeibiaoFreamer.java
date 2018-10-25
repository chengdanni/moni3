package soexample.umeng.com.moni3.Fragment;


import soexample.umeng.com.moni3.Persenter.LeibiaoFreamerpersenter;
import soexample.umeng.com.moni3.mvp.persenter.BseaFreamtpersenter;

public class LeibiaoFreamer extends BseaFreamtpersenter<LeibiaoFreamerpersenter> {
    @Override
    public Class<LeibiaoFreamerpersenter> getClassAgeid() {
        return LeibiaoFreamerpersenter.class;
    }

}
