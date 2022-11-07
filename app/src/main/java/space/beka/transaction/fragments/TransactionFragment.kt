package space.beka.transaction.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import space.beka.transaction.R
import space.beka.transaction.adapters.MyTransactionRvAdapter
import space.beka.transaction.databinding.FragmentTransactionBinding
import space.beka.transaction.db.AppDatabase
import space.beka.transaction.db.MyCard
import space.beka.transaction.db.MyTransaction


class TransactionFragment : Fragment() {
    private lateinit var cardList: ArrayList<MyCard>
    private lateinit var cardNameList: ArrayList<String>
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var listTransaction: ArrayList<MyTransaction>
    private lateinit var myTransactionAdapter: MyTransactionRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        loadCard()
        setTransaction()
        showAllTransactions()
        return binding.root
    }

    private fun setTransaction() {
        binding.btnSave.setOnClickListener {
            val myTransaction = MyTransaction(
                cardList[binding.spinnerFromCard.selectedItemPosition].id,
                cardList[binding.spinnerToCard.selectedItemPosition].id,
                binding.edtNumber.text.toString().toDouble()

            )
            appDatabase.myDao().addTransaction(myTransaction)
            showAllTransactions()
        }
    }

    fun showAllTransactions() {
        listTransaction = ArrayList()
        listTransaction.addAll(
            appDatabase.myDao().getTransactions()
        )
        myTransactionAdapter = MyTransactionRvAdapter(listTransaction, appDatabase)
        binding.rvTrans.adapter = myTransactionAdapter
    }

    private fun loadCard() {
        cardList = ArrayList()
        cardList.addAll(appDatabase.myDao().getAllCards())
        cardNameList = ArrayList()
        cardList.forEach {
            cardNameList.add(it.name!!)
        }
        val spinner = ArrayAdapter<String>(
            binding.root.context,
            android.R.layout.simple_list_item_1,
            cardNameList
        )
        binding.spinnerToCard.adapter = spinner
        binding.spinnerFromCard.adapter = spinner


    }


}