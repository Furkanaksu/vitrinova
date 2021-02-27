package com.mobillium.vitrinova.ui.adapter

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

class CategoryAdapter(
    private var spots: ArrayList<DiscoverModel.CategoryDataItem>? = null
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots?.get(position)
        holder.parent.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rcv_enter)


        Glide.with(BaseApplication.getContext())
            .load(spot?.cover?.url)
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.photo)

        holder.categoryName.text = spot?.name?.toUpperCase()
    }

    override fun getItemCount(): Int {
        return spots?.size!!
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo : ImageView = view.findViewById(R.id.bigPhoto)
        var categoryName : TextView = view.findViewById(R.id.categoryName)
        var parent : ConstraintLayout = view.findViewById(R.id.parent)
    }
}