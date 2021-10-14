package by.akella.shared

import by.akella.sqldelight.benefits.Benefits
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Benefits {
    val driver = driverFactory.createDriver()
    return Benefits(driver)
}