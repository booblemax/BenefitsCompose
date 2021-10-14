package by.akella.shared

import by.akella.sqldelight.benefits.Benefits
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {

    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(Benefits.Schema, "benefits.db")
}