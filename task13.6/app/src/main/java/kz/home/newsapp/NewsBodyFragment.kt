package kz.home.newsapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

const val ARG_NEWS = "news"

class NewsBodyFragment : Fragment(R.layout.news_body) {

    companion object{
        fun newInstance(news: News): NewsBodyFragment {
            val args = Bundle()
            args.putSerializable(ARG_NEWS, news)

            val fragment = NewsBodyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = arguments?.getSerializable(ARG_NEWS) as News
        view.findViewById<TextView>(R.id.header).text = news.header
        view.findViewById<TextView>(R.id.author).text = news.author
        view.findViewById<TextView>(R.id.details).text = news.newsBody
    }
}