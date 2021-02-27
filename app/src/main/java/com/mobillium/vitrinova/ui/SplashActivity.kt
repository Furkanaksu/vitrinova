package com.mobillium.vitrinova.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.managers.Globals
import com.mobillium.vitrinova.managers.ServiceManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        ServiceManager().Token(
            {
                Globals.shared.StoredData = it
                Globals.shared.StoredDataFeatured = it[0].featured
                Globals.shared.StoredDataProducts = it[1].products
                Globals.shared.StoredDataCategory = it[2].categories
                Globals.shared.StoredDataShop = it[4].shops

                val i  = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            },
            {
            }
        )

    }
}