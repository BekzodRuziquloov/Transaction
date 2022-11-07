package space.beka.transaction.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import space.beka.transaction.R
import space.beka.transaction.adapters.MyCardAdapter
import space.beka.transaction.databinding.FragmentCardBinding
import space.beka.transaction.db.AppDatabase
import space.beka.transaction.db.MyCard

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var list: ArrayList<MyCard>
    private lateinit var appDatabase: AppDatabase
    private lateinit var adapter: MyCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(layoutInflater)


        appDatabase = AppDatabase.getInstance(binding.root.context)
        list = ArrayList()
        list.addAll(appDatabase.myDao().getAllCards())
        adapter = MyCardAdapter(list)

        binding.apply {
            rvCard.adapter = adapter
binding.btnSave.setOnClickListener {
    val myCard = MyCard(
        edtName.text.toString(),
        edtNumber.text.toString().toLong()
    )
    appDatabase.myDao().addCard(myCard)
    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
    list.add(myCard)
    adapter.notifyItemInserted(list.size-1)
}
        }

        return binding.root
    }


}