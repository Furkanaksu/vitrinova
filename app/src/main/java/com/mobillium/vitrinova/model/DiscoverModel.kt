package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

class DiscoverModel : ArrayList<DiscoverModel.DiscoverModelItem>()
{
    data class DiscoverModelItem(
        @SerializedName("type") var type: String? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("featured") var featured: ArrayList<FeaturedDataItem> ? = null,
        @SerializedName("products") var products: ArrayList<ProductDataItem> ? = null,
        @SerializedName("categories") var categories: ArrayList<CategoryDataItem> ? = null,
//        @SerializedName("collections") var collections: ArrayList<FeaturedDataItem> ? = null,
        @SerializedName("shops") var shops: ArrayList<ShopDataItem> ? = null,
    )

    data class ShopDataItem(
            @SerializedName("name") var name : String? = null,
            @SerializedName("cover") var cover: CoverItem? = null,
            @SerializedName("definition") var definition: String? = null,
    )

    data class FeaturedDataItem(
        @SerializedName("id") var id: String? = null,
        @SerializedName("type") var type: String? = null,
        @SerializedName("cover") var cover: CoverItem? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("sub_title") var sub_title: String? = null,
    )

    data class ProductDataItem(
        @SerializedName("id") var id: String? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("definition") var definition: String? = null,
        @SerializedName("price") var price: Int? = null,
        @SerializedName("old_price") var old_price: Int? = null,
        @SerializedName("share_url") var share_url: String? = null,
        @SerializedName("images") var images: ArrayList<ImageItem>? = null,
//        @SerializedName("shop") var shop: String? = null,
    )

    data class CategoryDataItem(
        @SerializedName("id") var id: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("cover") var cover: CoverItem? = null
        )

    data class CoverItem(
        @SerializedName("url") var url: String? = null,
    )
    data class ImageItem(
        @SerializedName("url") var url: String? = null,
    )
}
