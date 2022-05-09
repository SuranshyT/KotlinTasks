package kz.home.newsapp

import android.content.res.Resources
import java.io.Serializable

class News (val header:String, val author:String, val newsBody:String) : Serializable

object NewsArray{
    val news: Array<News> = arrayOf(News("Made in Abyss: Season 2", "Today Anime News", "MADE IN ABYSS: The Golden City of the Scorching Sun Season 2 is Scheduled for this July. Trailer is out"),
        News("Toei will release 3 movies this year", "Today Anime News", "Toei Animation has three films in the works for 2022.\n" +
                "1. One piece Film Red\n" +
                "2. Dragon Ball Super: Super Hero\n" +
                "3. Slam Dunk Movie"),
        News("Fire force is getting a season 3", "Today Anime News", "According to a reliable Weibo user, \"Fire Force season 3 is production . Official announcement will be made soon"),
        News("Bubble by WIT studio is now streaming worldwide", "Today Anime News", "Bubble\" is now streaming worldwide, only on Netflix.\n" +
                "\n" +
                "- Director: Tetsuro Araki\n" +
                "- Screenplay: Gen Urobuchi\n" +
                "- Character designs: Takeshi Obata\n" +
                "- Music: Hiroyuki Sawano\n" +
                "- Animation production: WIT STUDIO\n" +
                "- Opening theme song: \"Bubble (feat. Uta)\" by Eve"),
        News("Hyouka anime finally returns", "Today Anime News", "It's been ten years since the anime first aired, and now Hyouka\" TV anime is getting a new project for the 10th Year Anniversary"),
        News("Black clover manga will enter it's final arc", "Today Anime News", "The creator (Yuki Tabata) of Black Clover will take a three-month break to prepare for the final arc!"),
        News("","",""))
}