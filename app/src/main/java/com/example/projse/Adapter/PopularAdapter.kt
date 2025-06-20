package com.example.projse.Adapter

import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projse.Activity.DetailActivity
import com.example.projse.Model.ItemsModel
import com.example.projse.databinding.ViewholderPopularBinding

class PopularAdapter(val items:MutableList<ItemsModel>):
RecyclerView.Adapter<PopularAdapter.Viewholder>(){
    class Viewholder (val binding: ViewholderPopularBinding)
        :RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
       val binding=ViewholderPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
       val item = items[position]

        with(holder.binding){
            titleTxt.text=item.title
            priceTxt.text = "$${item.price}"
            ratingTxt.text=item.rating.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(picdetail)
            root.setOnClickListener{
                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra("object",item)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            }
        }
    }

    override fun getItemCount(): Int =items.size

}