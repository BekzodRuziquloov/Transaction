package space.beka.transaction.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MyDao {
    @Insert
    fun addCard(card: MyCard)
    @Insert
    fun addTransaction(transaction: MyTransaction)
    @Query("select * from mycard")
    fun getAllCards():List<MyCard>
    @Query("select * from mytransaction")
    fun getTransactions():List<MyTransaction>
    @Query("select * from mycard where id  = :id")
    fun getCardById(id:Int):MyCard
}