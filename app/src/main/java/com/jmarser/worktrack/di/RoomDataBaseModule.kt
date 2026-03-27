package com.jmarser.worktrack.di

import android.content.Context
import androidx.room.Room
import com.jmarser.worktrack.data.local.database.WorkTrackDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): WorkTrackDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = WorkTrackDataBase::class.java,
            name = WorkTrackDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration(dropAllTables = true).build()
    }

    @Provides
    fun provideCompanyDao(db: WorkTrackDataBase) = db.companyDao()

    @Provides
    fun provideWorkDayDao(db: WorkTrackDataBase) = db.workDayDao()

    @Provides
    fun provideWorkLocation(db: WorkTrackDataBase) = db.workLocationDao()

    @Provides
    fun provideExpanseDao(db: WorkTrackDataBase) = db.expanseDao()
}