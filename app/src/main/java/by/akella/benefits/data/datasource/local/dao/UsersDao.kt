package by.akella.benefits.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import by.akella.benefits.data.datasource.local.UserEntity

@Dao
interface UsersDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM users WHERE uid = :uid")
    suspend fun getUserData(uid: String): UserEntity?
}