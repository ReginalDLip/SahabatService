package com.example.projse.Adapter

import android.content.Intent
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projse.Activity.ListItemsActivity
import com.example.projse.Model.CategoryModel
import com.example.projse.R
import com.example.projse.databinding.ActivityMainBinding
import com.example.projse.databinding.ViewholderCategoryBinding
import com.google.firebase.database.Transaction.Handler
import kotlinx.coroutines.delay

class CategoryAdapter(val items:MutableList<CategoryModel>):
RecyclerView.Adapter<CategoryAdapter.Viewholder>()
{

        private var selectedPosition=-1
    private var lastSelectedPosition=-1

    class Viewholder (val binding: ViewholderCategoryBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text=item.title

        if(selectedPosition==position){
            holder.binding.titleTxt.setBackgroundResource(R.drawable.blue_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }else{
            holder.binding.titleTxt.setBackgroundResource(R.drawable.light_blue_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )
        }
        holder.binding.root.setOnClickListener{
            val position=position
            if (position!=RecyclerView.NO_POSITION){
                lastSelectedPosition=selectedPosition
                selectedPosition=position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

            }
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(holder.itemView.context,ListItemsActivity::class.java).apply {
                putExtra("id",item.id.toString())
                putExtra("title",item.title)
            }
                ContextCompat.startActivity(holder.itemView.context,intent,null )
            },1000  )
        }
    }

    override fun getItemCount(): Int =items.size

}