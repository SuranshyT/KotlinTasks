package kz.home.newsapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsMenuFragment : Fragment(R.layout.news_menu){

    lateinit var menu1: TextView
    lateinit var menu2: TextView
    lateinit var menu3: TextView
    lateinit var menu4: TextView
    lateinit var menu5: TextView
    lateinit var menu6: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu1 = view.findViewById<TextView>(R.id.news1)
        menu1.text = getString(R.string.header1)
        menu2 = view.findViewById<TextView>(R.id.news2)
        menu2.text = getString(R.string.header2)
        menu3 = view.findViewById<TextView>(R.id.news3)
        menu3.text = getString(R.string.header3)
        menu4 = view.findViewById<TextView>(R.id.news4)
        menu4.text = getString(R.string.header4)
        menu5 = view.findViewById<TextView>(R.id.news5)
        menu5.text = getString(R.string.header5)
        menu6 = view.findViewById<TextView>(R.id.news6)
        menu6.text = getString(R.string.header6)

        menu1.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[0])).addToBackStack(null).commit()
            //changeTextStyle(0)
        }

        menu2.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[1])).addToBackStack(null).commit()
            //changeTextStyle(1)
        }

        menu3.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[2])).addToBackStack(null).commit()
            //changeTextStyle(2)
        }

        menu4.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[3])).addToBackStack(null).commit()
            //changeTextStyle(3)
        }

        menu5.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[4])).addToBackStack(null).commit()
            //changeTextStyle(4)
        }

        menu6.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.detailsContainer,
                    NewsBodyFragment.newInstance(NewsArray.news[5])).addToBackStack(null).commit()
            //changeTextStyle(5)
        }

        view.findViewById<TextView>(R.id.buttonPrev).setOnClickListener {
            fragmentManager?.popBackStack()
        }

        view.findViewById<TextView>(R.id.buttonNext).setOnClickListener {
            val fragment = parentFragmentManager.findFragmentById(R.id.detailsContainer)
            val info = fragment?.arguments?.getSerializable(ARG_NEWS) as News
            for(index in NewsArray.news.indices){
                if(info.header == NewsArray.news[index].header) {
                    if (index == 5 || index == 6){
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.detailsContainer,
                                NewsBodyFragment.newInstance(NewsArray.news[0])
                            ).addToBackStack(null).commit()
                    }else{
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.detailsContainer,
                                NewsBodyFragment.newInstance(NewsArray.news[index+1])
                            ).addToBackStack(null).commit()
                    }
                }
            }
        }
    }

    fun changeTextStyle(index: Int){
        val headerArray = arrayOf(menu1, menu2, menu3, menu4, menu5, menu6)
        for(i in headerArray.indices){
            headerArray[i].setTypeface(null, Typeface.NORMAL)
        }
        headerArray[index].setTypeface(null, Typeface.BOLD)
    }
}