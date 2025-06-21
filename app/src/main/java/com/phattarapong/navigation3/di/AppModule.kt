package com.phattarapong.navigation3.di

import com.phattarapong.navigation3.data.repository.FakeProductRepositoryImpl
import com.phattarapong.navigation3.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        fakeProductRepositoryImpl: FakeProductRepositoryImpl
    ): ProductRepository
}
