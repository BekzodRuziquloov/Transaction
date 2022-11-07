package space.beka.transaction.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.beka.transaction.databinding.ItemTransactionRvBinding
import space.beka.transaction.db.AppDatabase
import space.beka.transaction.db.MyTransaction

class MyTransactionRvAdapter(var list: List<MyTransaction>, val appDatabase: AppDatabase) :
    RecyclerView.Adapter<MyTransactionRvAdapter.Vh>() {
    inner class Vh(var itemTvBinding: ItemTransactionRvBinding) :
        RecyclerView.ViewHolder(itemTvBinding.root) {
        fun onBind(myTransaction: MyTransaction, position: Int) {
            itemTvBinding.apply {
                tvSumma.text = myTransaction.summa.toString() + " so'm o'tkazildi"
                tvDate.text = myTransaction.date +" vaqti"
                tvCardTo.text = appDatabase.myDao().getCardById(myTransaction.toCardId!!).name.toString() +" nomli kartaga"
                tvCardFrom.text = appDatabase.myDao().getCardById(myTransaction.fromCardId!!).name.toString()+" nomli kartadan "
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemTransactionRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size


}