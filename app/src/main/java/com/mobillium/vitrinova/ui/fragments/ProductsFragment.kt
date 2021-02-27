package com.mobillium.vitrinova.ui.fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.databinding.FragmentNewsBinding
import com.mobillium.vitrinova.managers.Globals
import com.mobillium.vitrinova.managers.ServiceManager
import com.mobillium.vitrinova.model.DiscoverModel
import com.mobillium.vitrinova.ui.SeeAllActivity
import com.mobillium.vitrinova.ui.adapter.CategoryAdapter
import com.mobillium.vitrinova.ui.adapter.ProductAdapter
import com.mobillium.vitrinova.ui.adapter.ShopAdapter
import com.mobillium.vitrinova.ui.adapter.SliderAdapter


class ProductsFragment : Fragment() {

    val NUM_PAGES = 2

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!
    lateinit var listShops: ArrayList<DiscoverModel.ShopDataItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val page = inflater.inflate(R.layout.fragment_news, container, false)
        val view = binding.root

        listShops = Globals.shared.StoredDataShop!!

        binding.contentHeader1.text = Globals.shared.StoredData?.get(1)?.title
        binding.contentHeader2.text = Globals.shared.StoredData?.get(2)?.title
        binding.contentHeader3.text = Globals.shared.StoredData?.get(4)?.title

        binding.contentAll1.setOnClickListener {
            val i = Intent(context, SeeAllActivity::class.java)
            i.putExtra("type", "1")
            startActivity(i)
        }

        binding.contentAll2.setOnClickListener {
            val i = Intent(context, SeeAllActivity::class.java)
            i.putExtra("type", "2")
            startActivity(i)
        }

        binding.contentAll3.setOnClickListener {
            val i = Intent(context, SeeAllActivity::class.java)
            i.putExtra("type", "3")
            startActivity(i)
        }

        layoutManagers()

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemSwipeToRefresh.setOnRefreshListener {
            swipeRefresh()
        }

    }

    fun layoutManagers()
    {
        binding.slider.onFlingListener = null
        binding.slider4.onFlingListener = null


        binding.slider.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.slider.adapter = SliderAdapter(Globals.shared.StoredDataFeatured)

        binding.slider2.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.slider2.adapter = ProductAdapter(Globals.shared.StoredDataProducts)

        binding.slider3.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.slider3.adapter = CategoryAdapter(Globals.shared.StoredDataCategory)

        binding.slider4.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.slider4.adapter = ShopAdapter(Globals.shared.StoredDataShop)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.slider)
        pagerSnapHelper.attachToRecyclerView(binding.slider4)

        binding.slider4.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                val snapView = pagerSnapHelper.findSnapView(layoutManager)
                val snapPosition = snapView?.let {
                    layoutManager?.getPosition(it)
                }
                val bgImage = listShops[snapPosition!!].cover?.url
                Glide.with(context!!)
                    .load(bgImage)
                    .fitCenter()
                    .into(binding.editorsChoiceField)
                binding.editorsChoiceField.setColorFilter(
                    R.color.gray_filter,
                    PorterDuff.Mode.SCREEN
                )
            }
        })



        binding.indicator.attachToRecyclerView(binding.slider4, pagerSnapHelper)
        binding.indicator.attachToRecyclerView(binding.slider, pagerSnapHelper)
    }

    fun swipeRefresh(){

        ServiceManager().Token(
            {
                Globals.shared.StoredData = it
                Globals.shared.StoredDataFeatured = it[0].featured
                Globals.shared.StoredDataProducts = it[1].products
                Globals.shared.StoredDataCategory = it[2].categories
                Globals.shared.StoredDataShop = it[4].shops

                layoutManagers()
                binding.itemSwipeToRefresh.isRefreshing = false
            },
            {
            }
        )

    }

}