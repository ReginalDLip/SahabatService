package com.example.projse.Adapter



import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.PixelCopy.Request
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.projse.Model.SliderModel
import com.example.projse.databinding.SliderItemContainerBinding


class SliderAdapter(
    private var sliderItems:List<SliderModel>,
    private var viewPager2: ViewPager2

):RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {

    private lateinit var context: Context
    private var runnable= Runnable {
        sliderItems=sliderItems
        notifyDataSetChanged()
    }


    class SliderViewholder(private val binding: SliderItemContainerBinding):
    RecyclerView.ViewHolder(binding.root){
        fun setImage(sliderModel: SliderModel,context: Context){
            Glide.with(Context)
                .load(sliderModel.url)
                .apply{RequestOptions().transform(CenterInside())}
                .into(binding.imageSlide)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewholder {
        context=parent.context
        val binding = SliderItemContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderViewholder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewholder, position: Int) {
        holder.setImage(sliderItems[position],context)
        if (position==sliderItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int =sliderItems.size
}
