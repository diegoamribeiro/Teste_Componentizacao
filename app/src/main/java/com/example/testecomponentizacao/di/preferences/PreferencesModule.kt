package com.example.testecomponentizacao.di.preferences

import android.content.Context
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providesDataStorePreferences(@ApplicationContext context: Context) =
        UserPreferencesRepository(context)
}