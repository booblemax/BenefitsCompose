package by.akella.shared

import android.content.Context
import by.akella.sqldelight.benefits.Benefits
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(Benefits.Schema, context, "benefits.db")
}