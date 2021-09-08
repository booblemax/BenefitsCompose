package by.akella.benefits.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.akella.benefits.data.datasource.local.BenefitEntity
import by.akella.benefits.data.datasource.local.UserEntity
import by.akella.benefits.data.datasource.local.dao.BenefitsDao
import by.akella.benefits.data.datasource.local.dao.UsersDao
import by.akella.benefits.data.datasource.local.db.BenefitsDb.Companion.DB_VERSION

@Database(entities = [BenefitEntity::class, UserEntity::class], version = DB_VERSION)
abstract class BenefitsDb : RoomDatabase() {

    abstract val benefitsDao: BenefitsDao

    abstract val usersDao: UsersDao

    companion object {

        const val DB_VERSION = 1
    }
}