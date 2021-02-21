package com.demoapp.weddingprofile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demoapp.weddingprofile.model.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ResultsItem::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {

    abstract val profileDatabaseDao: ProfileDatabaseDao

    private class ProfileDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val profileDatabaseDao = database.profileDatabaseDao
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): ProfileDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "profile_database"
                    )
                        .addCallback(ProfileDatabaseCallback(coroutineScope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}