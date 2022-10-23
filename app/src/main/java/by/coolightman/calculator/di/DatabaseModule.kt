package by.coolightman.calculator.di

import android.content.Context
import androidx.room.Room
import by.coolightman.calculator.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideHistoryDao(appDatabase: AppDatabase) = appDatabase.historyRowDao()


    companion object {
        private const val DB_NAME = "app_database.db"
    }
}