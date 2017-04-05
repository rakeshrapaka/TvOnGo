package com.freelance.rapaka.tvongo.model.component;

import android.content.SharedPreferences;

import com.freelance.rapaka.tvongo.model.module.AppModule;

import javax.inject.Singleton;


import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    SharedPreferences prefs();

}