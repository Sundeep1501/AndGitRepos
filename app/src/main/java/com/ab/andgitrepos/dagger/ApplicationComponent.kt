package com.ab.andgitrepos.dagger

import com.ab.andgitrepos.veiwmodel.DetailsViewModel
import com.ab.andgitrepos.veiwmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sunde_000 on 28/02/2018.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ApplicationComponent {
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: DetailsViewModel)
}