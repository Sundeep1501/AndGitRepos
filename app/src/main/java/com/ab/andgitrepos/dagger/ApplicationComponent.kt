package com.ab.andgitrepos.dagger

import dagger.Component
import javax.inject.Singleton

/**
 * Created by sunde_000 on 28/02/2018.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ApplicationComponent {

}