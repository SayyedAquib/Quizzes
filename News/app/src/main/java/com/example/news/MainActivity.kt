package com.example.news

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private var articles = mutableListOf<Article>()
    lateinit var adapter: NewsAdapter
    var pageNum = 1
    var totalResults = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController!!.hide(
                android.view.WindowInsets.Type.statusBars()
            )
        }

        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        newsList.layoutManager = layoutManager

        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                listrv.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                if (totalResults > layoutManager.itemCount &&
                    layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5
                ) {
                    pageNum++
                    getNews()
                }
            }
        })
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyItemChanged(pageNum)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(applicationContext, "Error in fetching news check your network", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


