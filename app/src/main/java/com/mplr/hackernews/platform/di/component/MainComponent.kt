package com.mplr.hackernews.platform.di.component

import com.mplr.hackernews.platform.di.module.LoggerModule
import com.mplr.hackernews.platform.di.module.MainActivityModule
import com.mplr.hackernews.platform.di.module.PersistenceModule
import com.mplr.hackernews.platform.di.module.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainActivityModule::class,
        RetrofitModule::class,
        LoggerModule::class,
        PersistenceModule::class
    ]
)
interface MainComponent {}