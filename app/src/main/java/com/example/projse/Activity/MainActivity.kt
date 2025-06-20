package com.example.projse.Activity

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.projse.Adapter.CategoryAdapter
import com.example.projse.Adapter.PopularAdapter
import com.example.projse.Adapter.SliderAdapter
import com.example.projse.Model.SliderModel
import com.example.projse.R
import com.example.projse.ViewModel.MainViewModel
import com.example.projse.databinding.ActivityMainBinding

class   MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        initBanner()
        initCategory()
        initRecommended()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartbtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))

        }
    }

    private fun initRecommended() {
        binding.progressBarmostAquired.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.ViewAquiredService.layoutManager = GridLayoutManager(this@MainActivity,2)
            binding.ViewAquiredService.adapter = PopularAdapter(it.toMutableList())
            binding.progressBarmostAquired.visibility=View.GONE
        })
        viewModel.loadPopular()

    }

    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this,Observer {
            binding.ViewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.ViewCategory.adapter=CategoryAdapter(it.toMutableList())
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initBanner(){
        binding.progressBarSlider.visibility= View.VISIBLE
        viewModel.banners.observe(this  , Observer {
            banners(it)
            binding.progressBarSlider.visibility= View.GONE
        })
        viewModel.loadBanners()
    }
    private fun banners(image: List<SliderModel>){
        binding.viewPager22.adapter=SliderAdapter(image,binding.viewPager22)
        binding.viewPager22.clipToPadding=false
        binding.viewPager22.clipChildren=false
        binding.viewPager22.offscreenPageLimit=3
        binding.viewPager22.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager22.setPageTransformer(compositePageTransformer)

        if (image.size>1){
            binding.dotsindicator.visibility=View.VISIBLE
            binding.dotsindicator.attachTo(binding.viewPager22)
        }
    }
}