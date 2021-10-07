package com.omelchenkoaleks.thecatapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.omelchenkoaleks.thecatapi.ui.listener.ItemClickListener

class MainActivity : AppCompatActivity(), ItemClickListener {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = this.findNavController(R.id.nav_host_fragment_main)
    }

    override fun onItemClick(url: String?) {
        navController?.navigate(
            R.id.action_fragmentCatGallery_to_catDetailFragment,
            bundleOf(URL to url)
        )
    }

    companion object {
        const val URL = "url"
    }
}
