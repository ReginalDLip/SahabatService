package com.example.projse.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.projse.Helper.ChangeNumberItemsListener
import com.example.projse.Helper.ManagmentCart
import com.example.projse.Model.ItemsModel
import com.example.projse.databinding.ViewholderCartBinding
import com.example.projse.databinding.ViewholderPicBinding

class CartAdapter(private val listItemSelected:ArrayList<ItemsModel>,
    context: Context ,
    var changeNumberItemsListener: ChangeNumberItemsListener
    ):RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)
    {

    }
    private val managmentcart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
      val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent , false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.tittleTxt.text = item.title
        holder.binding.feeEachTime.text = "$${item.price}"
        holder.binding.totalEACHTime.text = "$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.binding.plusBtn.setOnClickListener{
            managmentcart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()

                }

            })

        }

    }

    override fun getItemCount(): Int =listItemSelected.size
}