package ng_designs.android_2021_task_5.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ng_designs.android_2021_task_5.R
import ng_designs.android_2021_task_5.databinding.MainActivityBinding
import ng_designs.android_2021_task_5.presentation.screens.main.MainFragment

class MainActivity : AppCompatActivity() {

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}