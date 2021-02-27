package com.mobillium.vitrinova.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.vitrinova.databinding.ActivitySeeallBinding
import com.mobillium.vitrinova.managers.Globals
import com.mobillium.vitrinova.ui.adapter.CategoryAdapter
import com.mobillium.vitrinova.ui.adapter.ProductAdapter
import com.mobillium.vitrinova.ui.adapter.ShopAdapter
import com.mobillium.vitrinova.ui.adapter.SliderAdapter

class SeeAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeallBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeallBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val type = intent.getStringExtra("type")

        if (type == "1")
        {
            binding.rcySeeAll.layoutManager = GridLayoutManager(this, 2)
            binding.rcySeeAll.adapter = ProductAdapter(Globals.shared.StoredDataProducts)
        }
        else if(type == "2")
        {
            binding.rcySeeAll.layoutManager =  GridLayoutManager(this, 2)
            binding.rcySeeAll.adapter = CategoryAdapter(Globals.shared.StoredDataCategory)
        }
        else if(type == "3")
        {
            binding.rcySeeAll.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rcySeeAll.adapter = ShopAdapter(Globals.shared.StoredDataShop)
        }
    }
}