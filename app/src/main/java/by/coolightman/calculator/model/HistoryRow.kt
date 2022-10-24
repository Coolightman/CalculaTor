package by.coolightman.calculator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_rows")
data class HistoryRow(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val expression: String,
    val result: String
)
