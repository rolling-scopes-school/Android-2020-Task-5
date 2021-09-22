package ng_designs.android_2021_task_5.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ng_designs.android_2021_task_5.R
import ng_designs.android_2021_task_5.presentation.screens.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}