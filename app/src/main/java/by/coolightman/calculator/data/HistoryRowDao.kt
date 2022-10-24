package by.coolightman.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.coolightman.calculator.model.HistoryRow
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryRowDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(row: HistoryRow)

    @Query("SELECT * FROM history_rows ORDER BY id DESC")
    fun getAll(): Flow<List<HistoryRow>>

    @Query("DELETE FROM history_rows WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM history_rows")
    suspend fun deleteAll()
}