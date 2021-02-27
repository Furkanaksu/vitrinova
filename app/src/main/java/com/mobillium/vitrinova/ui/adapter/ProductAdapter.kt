package com.mobillium.vitrinova.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.DiscoverModel
import com.mobillium.vitrinova.managers.BaseApplication

class ProductAdapter(
    private var spots: ArrayList<DiscoverModel.ProductDataItem>? = null
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.slider2_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots?.get(position)
        holder.parent.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rcv_enter)


        Glide.with(BaseApplication.getContext())
            .load(spot?.images?.get(0)?.url)
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.photo)

        holder.itemName.text = spot?.title?.toUpperCase()
        holder.subTitle.text = spot?.title
        holder.price.text = "${spot?.price} TL"
        if (spot?.old_price  != null)
        {
            holder.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.oldPrice.text = "${spot?.old_price} TL"
        }

    }

    override fun getItemCount(): Int {
        return spots?.size!!
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo : ImageView = view.findViewById(R.id.bigPhoto)
        var itemName : TextView = view.findViewById(R.id.itemName)
        var subTitle : TextView = view.findViewById(R.id.subTitle)
        var price : TextView = view.findViewById(R.id.price)
        var oldPrice : TextView = view.findViewById(R.id.oldPrice)
        var parent : ConstraintLayout = view.findViewById(R.id.parent)
    }
}