package com.elramady.bostatask.di

import com.elramady.bostatask.api.UserInterface
import com.elramady.bostatask.repo.UserRepositary

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideUserApi(@Named("retrofit") retrofit: Retrofit): UserInterface {
        return retrofit.create(UserInterface::class.java)
    }

    @Singleton
    @Provides
    fun providesUserRepository(api: UserInterface): UserRepositary {
        return UserRepositary(api)
    }

}