package ru.intercommunication.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.intercommunication.newsapplication.feature.main.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, MainFragment())
                .commit()
        }


    }
}