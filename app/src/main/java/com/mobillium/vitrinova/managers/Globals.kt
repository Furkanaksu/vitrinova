package com.mobillium.vitrinova.managers

import android.graphics.Bitmap
import com.mobillium.vitrinova.model.DiscoverModel

public class Globals {

    companion object {
        private var instance: Globals? = null

        val shared: Globals
            get() {
                if (instance == null) {
                    instance = Globals()
                }

                return instance!!
            }
    }

    val BaseWebservice = "https://www.vitrinova.com/api/v2/"
    var StoredData : ArrayList<DiscoverModel.DiscoverModelItem>? = null
    var StoredDataFeatured : ArrayList<DiscoverModel.FeaturedDataItem>? = null
    var StoredDataProducts : ArrayList<DiscoverModel.ProductDataItem>? = null
    var StoredDataCategory : ArrayList<DiscoverModel.CategoryDataItem>? = null
    var StoredDataShop: ArrayList<DiscoverModel.ShopDataItem>? = null

}