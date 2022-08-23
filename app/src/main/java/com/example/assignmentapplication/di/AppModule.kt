package com.example.assignmentapplication.di

import com.example.assignmentapplication.useCase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesLoginUseCase(): LoginUseCase {
        return LoginUseCase()
    }

}