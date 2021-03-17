package com.oke.player.app

import android.content.Context
import androidx.room.Room
import com.oke.player.model.dao.AppDB
import com.oke.player.model.dao.Dao
import com.oke.player.ui.main.MainRepository
import com.oke.player.ui.main.MainRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    internal fun provideAppDB(context: Context): AppDB = Room.databaseBuilder(context, AppDB::class.java, "_dataset.db").build()

    @Provides
    @ApplicationScope
    fun provideDao(database: AppDB): Dao = database.dao()

    @Provides
    @ApplicationScope
    fun provideMainRepository(dao: Dao): MainRepository = MainRepositoryImpl(dao)
}