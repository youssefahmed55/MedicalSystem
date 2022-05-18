package com.example.medicalsystem.apiui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class MyRetrofitModule {

    @Provides
    @Singleton
    public static MyApiInterface myApiInterface(){
        return new Retrofit.Builder().baseUrl("http://api.instant-ss.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MyApiInterface.class);



    }
}
