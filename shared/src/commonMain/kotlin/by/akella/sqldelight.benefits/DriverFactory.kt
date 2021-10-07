package by.akella.sqldelight.benefits

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    expect fun createDriver(): SqlDriver
}