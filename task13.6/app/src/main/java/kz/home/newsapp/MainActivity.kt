package kz.home.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transaction1 = supportFragmentManager
            .beginTransaction()
            .add(R.id.newsContainer, NewsMenuFragment()).commit()

        val transaction2 = supportFragmentManager
            .beginTransaction()
            .add(R.id.detailsContainer, NewsBodyFragment.newInstance(NewsArray.news[6])).commit()
    }
}