package com.ab.andgitrepos

import android.app.Application
import com.ab.andgitrepos.dagger.ApplicationComponent
import com.ab.andgitrepos.dagger.DaggerApplicationComponent
import com.ab.andgitrepos.dagger.NetworkModule

/**
 * Created by sunde_000 on 28/02/2018.
 */
class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .networkModule(NetworkModule(BuildConfig.HOST_API))
                .build()
    }
}