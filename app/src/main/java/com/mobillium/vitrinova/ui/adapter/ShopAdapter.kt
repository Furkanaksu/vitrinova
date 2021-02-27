package com.mobillium.vitrinova.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ShopAdapter(
        private var spots: ArrayList<DiscoverModel.ShopDataItem>? = null
) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
                inflater.inflate(
                        R.layout.shop_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots?.get(position)
//        holder.parent.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rcv_enter)


        Glide.with(BaseApplication.getContext())
                .load(spot?.cover?.url)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(holder.photo)

        var str : String = ""

        if (spot?.definition?.length!! > 80)
        {
            str = "${spot.definition?.substring(0, 80)}..."
        }
        else
        {
            str = spot.definition!!
        }

        holder.itemName.text = spot?.name
        holder.desc.text = str

    }

    override fun getItemCount(): Int {
        return spots?.size!!
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo : ImageView = view.findViewById(R.id.bigPhoto)
        var parent : ConstraintLayout = view.findViewById(R.id.parent)
        var screenBlack : ImageView = view.findViewById(R.id.screenBlack)
        var itemName : TextView = view.findViewById(R.id.itemName)
        var desc : TextView = view.findViewById(R.id.desc)
    }
}