package com.mobillium.vitrinova.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.databinding.ActivityMainBinding
import com.mobillium.vitrinova.managers.Globals
import com.mobillium.vitrinova.managers.ServiceManager
import com.mobillium.vitrinova.ui.fragments.NotificationFragment
import com.mobillium.vitrinova.ui.fragments.ProductsFragment
import com.mobillium.vitrinova.ui.fragments.SearchFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        loadFragment(ProductsFragment())

        binding.imageViewMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")
            try {
                startActivityForResult(intent, REQ_CODE)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Sorry your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.bottomNavigation.setOnNavigationItemSelectedListener {it->
            when(it.itemId) {
                R.id.ProductsFragment -> {
                    loadFragment(ProductsFragment())
                    true
                }
                R.id.SearchFragment -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.NotificationFragment -> {
                    loadFragment(NotificationFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    binding.editTextSearch.setText(result?.get(0))
                    binding.editTextSearch.setSelection(result?.get(0)?.length!!)
                }
            }
        }
    }

    fun loadFragment(fragment: Fragment):Boolean {
        if (fragment != null)
        {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

}