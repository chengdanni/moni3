package soexample.umeng.com.moni3.Fragment;

import soexample.umeng.com.moni3.Persenter.ShopFramnerpersenter;
import soexample.umeng.com.moni3.mvp.persenter.BseaFreamtpersenter;

public class ShopFramner extends BseaFreamtpersenter<ShopFramnerpersenter> {
    @Override
    public Class<ShopFramnerpersenter> getClassAgeid() {
        return ShopFramnerpersenter.class;
    }
}
