package com.generation.telasdesenvolvmed.di

import android.app.Application
import android.content.Context
import com.generation.telasdesenvolvmed.api.Repository
import com.generation.telasdesenvolvmed.data.LoginDao
import com.generation.telasdesenvolvmed.data.LoginDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Singleton
    @Provides
    fun returnRepository(loginDatabase: LoginDatabase): Repository{
        return Repository(loginDatabase.loginDao())
    }

    @Singleton
    @Provides
    fun getAppDatabase(context: Application): LoginDatabase{
        return LoginDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun appDao(loginDatabase: LoginDatabase): LoginDao{
        return loginDatabase.loginDao()
    }

}