package com.mobillium.vitrinova.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.DiscoverModel
import com.mobillium.vitrinova.managers.BaseApplication

class SliderAdapter(
    private var spots: ArrayList<DiscoverModel.FeaturedDataItem>? = null
) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.suggestion_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots?.get(position)


        Glide.with(BaseApplication.getContext())
            .load(spot?.cover?.url)
            .into(holder.photo)

        holder.itemName.text = spot?.title?.toUpperCase()
        holder.subTitle.text = spot?.sub_title

    }

    override fun getItemCount(): Int {
        return spots?.size!!
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo : ImageView = view.findViewById(R.id.bigPhoto)
        var itemName : TextView = view.findViewById(R.id.itemName)
        var subTitle : TextView = view.findViewById(R.id.subTitle)
    }
}