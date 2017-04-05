package com.freelance.rapaka.tvongo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.freelance.rapaka.tvongo.model.component.NetComponent;
import com.freelance.rapaka.tvongo.model.module.AppModule;
import com.freelance.rapaka.tvongo.model.module.NetModule;

/**
 * Created by Rakesh on 4/3/2017.
 */

public class TvOnGo extends Application {

    private NetComponent mNetComponent,mNetComponentC;
    public static final String TAG = TvOnGo.class.getSimpleName();


    private static TvOnGo mInstance;

    //fragment state hash map
    //Map<String, Fragment.SavedState> savedStateMap;

    @Override
    public void onCreate() {
        //savedStateMap = new HashMap<String, Fragment.SavedState>();
        super.onCreate();

        mInstance = this;

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.DATAMW_SERVER_URL,this))
                .build();

    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized TvOnGo getInstance() {
        return mInstance;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public NetComponent getDynamicNetcomponent(String endpoint) {
        if (mNetComponentC == null){
            mNetComponentC = DaggerNetComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule(endpoint, this))
                    .build();
        }
        return mNetComponentC;
    }

}
