package com.freelance.rapaka.tvongo.model.component;

import com.freelance.rapaka.tvongo.model.module.AppModule;
import com.freelance.rapaka.tvongo.model.module.NetModule;




import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}