package com.mobillium.vitrinova.managers
import com.google.gson.Gson
import com.mobillium.vitrinova.model.DiscoverModel

import java.util.*
import kotlin.collections.ArrayList

class ServiceManager {

    fun Token(callbackSuccess: (response: DiscoverModel) -> Unit, callbackFailure: (response: ArrayList<String>) -> Unit)
    {
        val call = ServiceConnector().Run().Discover()
        call.enqueue(callback({ response ->

            response?.body()?.let {

                val plainBody: String = it

                val responseConvert: DiscoverModel = Gson().fromJson(plainBody, DiscoverModel::class.java)

                callbackSuccess(responseConvert)
                return@callback
            }
        },
                { throwable ->
                    throwable?.let {
                        var errors = ArrayList<String>()
                        errors.add(it.localizedMessage)
                        callbackFailure(errors)
                        return@callback
                    }
                }
        ))
    }
}
