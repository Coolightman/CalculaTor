package by.coolightman.calculator.data

import by.coolightman.calculator.model.HistoryRow
import javax.inject.Inject

class HistoryRowRepository @Inject constructor(
    private val historyRowDao: HistoryRowDao
) {

    suspend fun insert(row: HistoryRow) {
        historyRowDao.insert(row)
    }

    fun getAll() = historyRowDao.getAll()

    suspend fun delete(id: Long) {
        historyRowDao.delete(id)
    }

    suspend fun deleteAll() {
        historyRowDao.deleteAll()
    }
}