package space.beka.transaction.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.beka.transaction.databinding.ItemCardBinding
import space.beka.transaction.db.MyCard

class MyCardAdapter(var list: List<MyCard>) : RecyclerView.Adapter<MyCardAdapter.Vh>() {
    inner  class Vh(var itemTvBinding: ItemCardBinding):RecyclerView.ViewHolder(itemTvBinding.root) {
        fun onBind(myCard: MyCard, position: Int) {
itemTvBinding.tvName.text = myCard.name
            itemTvBinding.tvNumber.text = myCard.cardNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] ,position)
    }

    override fun getItemCount(): Int =list.size



}