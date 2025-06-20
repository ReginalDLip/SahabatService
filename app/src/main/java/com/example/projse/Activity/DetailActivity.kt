package com.example.projse.Activity

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.projse.Adapter.PicAdapter
import com.example.projse.Helper.ManagmentCart
import com.example.projse.Model.ItemsModel
import com.example.projse.R
import com.example.projse.databinding.ActivityDetailBinding
import com.example.projse.databinding.ViewholderPicBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart:ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        managmentCart=ManagmentCart(this)
        getBundles()
        initList()
    }

    private fun initList() {
        val picList=ArrayList<String>()
            for (imageurl in item.picUrl){
                picList.add(imageurl)

            }
        Glide.with(this)
            .load(picList[0])
            .into(binding.picdetail)

        binding.picList.adapter=PicAdapter(picList){
            Glide.with(this)
                .load(it)
                .into(binding.picdetail)

        }
        binding.picList.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun getBundles(){
        item=(intent.getSerializableExtra("object")as ItemsModel?)!!
        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart=Integer.valueOf(
                binding.numberItemTxt.text.toString()
            )
            managmentCart.insertItem(item)

        }
        binding.backBtn.setOnClickListener{finish()}
        binding.plusBtn.setOnClickListener{
            binding.numberItemTxt.text = (item.numberInCart+1).toString()
            item.numberInCart++
        }
        binding.minusBtn.setOnClickListener{
            if(item.numberInCart>0){
                binding.numberItemTxt.text=(item.numberInCart-1).toString()
                item.numberInCart--
            }
        }
    }
}