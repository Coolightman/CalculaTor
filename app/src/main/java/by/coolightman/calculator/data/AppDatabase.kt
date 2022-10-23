package by.coolightman.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import by.coolightman.calculator.model.HistoryRow

@Database(
    entities = [
        HistoryRow::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyRowDao(): HistoryRowDao
}