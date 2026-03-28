package com.jmarser.worktrack.di

import android.content.Context
import androidx.room.Room
import com.jmarser.worktrack.data.local.database.WorkTrackDataBase
import com.jmarser.worktrack.data.local.database.dao.CompanyDao
import com.jmarser.worktrack.data.local.database.dao.ExpanseDao
import com.jmarser.worktrack.data.local.database.dao.WorkDayDao
import com.jmarser.worktrack.data.local.database.dao.WorkLocationDao
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
    @Singleton
    fun provideCompanyDao(db: WorkTrackDataBase): CompanyDao{
        return db.companyDao()
    }

    @Provides
    @Singleton
    fun provideWorkDayDao(db: WorkTrackDataBase): WorkDayDao{
        return db.workDayDao()
    }

    @Provides
    @Singleton
    fun provideWorkLocationDao(db: WorkTrackDataBase): WorkLocationDao{
        return db.workLocationDao()
    }

    @Provides
    @Singleton
    fun provideExpanseDao(db: WorkTrackDataBase): ExpanseDao{
        return db.expanseDao()
    }
}