package com.example.projse.Activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projse.Adapter.ListFilteredAdapter
import com.example.projse.R
import com.example.projse.ViewModel.MainViewModel
import com.example.projse.databinding.ActivityListItemsBinding
import com.example.projse.databinding.ViewholderPopularBinding

class ListItemsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityListItemsBinding
    private val viewModel=MainViewModel()
    private var id:String=""
    private var title:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            binding.backBtn.setOnClickListener{finish()}
            progressBarList.visibility=View.VISIBLE
            viewModel.popular.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager=GridLayoutManager(this@ListItemsActivity,2)
                viewList.adapter=ListFilteredAdapter(it)
                progressBarList.visibility=View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }

    private fun getBundle(){
        id=intent.getStringExtra("id")!!
        title= intent.getStringExtra("title")!!
        binding.categoryTxt.text=title

    }

}