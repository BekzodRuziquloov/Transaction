package space.beka.transaction.db

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
class MyTransaction {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var fromCardId: Int? = null
    var toCardId: Int? = null
    var summa: Double? = null

    @SuppressLint("SimpleDateFormat")
    var date = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())

    constructor(fromCardId: Int?, toCardId: Int?, summa: Double?) {
        this.fromCardId = fromCardId
        this.toCardId = toCardId
        this.summa = summa
    }

    constructor()
}