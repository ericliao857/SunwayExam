package com.example.sunwayexam.utils

import com.example.sunwayexam.model.ApiBean
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel
import com.example.sunwayexam.model.attraction.Category
import com.example.sunwayexam.model.attraction.Image
import com.example.sunwayexam.model.attraction.Service
import java.util.concurrent.atomic.AtomicInteger

class AttractionFactory {
    private val counter = AtomicInteger(0)

    fun createApiBean(attractions: List<Attraction>): ApiBean<List<Attraction>> {
        return ApiBean(
            total = attractions.size,
            data = attractions
        )
    }

    fun createFakeAttraction(): Attraction {
        val id = counter.incrementAndGet()
        return Attraction(
            id = id,
            name = "國立中正紀念堂_$id",
            nameZh = null,
            openStatus = 1,
            introduction = """
        中正紀念堂是為了紀念中華民國第一任總統蔣介石，他於1975年逝世，次年行政院就破土開建中正紀念堂，設計師是設計圓山大飯店的楊卓成。

        紀念堂原址是陸軍總部，當時是臺北市區內最大的軍區，據說從大陸運過來的黃金，就曾經存放在這個地方。紀念堂採用的藍白兩色，是國旗上面主要的顏色，紀念堂頂部天穹的裝飾是青天白日12道光芒，銅像朝西面對總統府和中國大陸，其中都有特殊的意義。隨著民主演進思想開放，紀念堂前的廣場已成了民主運動的集會場。

        位於中正紀念堂左右兩側的國家音樂廳與國家戲劇院。戲劇院為戲劇、舞蹈等表演活動的場地，經常舉辦大型的表演活動，包括京劇、戲劇、兒童節目等；音樂廳是臺灣最具水準的音樂展演空間，經常舉辦室內樂，交響樂等大型的音樂活動。而兩廳院前廣場也會不定時舉辦戲劇、音樂活動，吸引眾多遊客前往欣賞。

        白牆藍瓦，70公尺高聳立在紀念公園的中央，不論從哪個角度看過去，都很宏偉。國外觀光客來到臺灣，不論行程如何排，都一定會到這裡來參觀一下。紀念堂的下方有陳列和展覽，也有紀念品販售。
    """.trimIndent(),
            openTime = """
        紀念堂開放時間：am09:00-pm06:00（星期一不休館）
        紀念公園開放時間：am05:00-pm12:00
        (休館日：除年度機電保養日、二二八紀念日、春節除夕及大年初一休館外，幾乎全年不休館)
    """.trimIndent(),
            zipcode = "100",
            distric = "中正區",
            address = "100 臺北市中正區中山南路21號",
            tel = "+886-2-23431100",
            fax = "",
            email = "",
            months = "01,07,02,08,03,09,04,10,05,11,06,12",
            nlat = 25.03619,
            elong = 121.51868,
            officialSite = "http://www.cksmh.gov.tw/",
            facebook = "https://www.facebook.com/cksmhfb/?locale=zh_TW",
            ticket = "",
            remind = """
        升降旗時間：
        夏令時間：4/1~9/30
        *升旗：6:00AM    降旗：6:10 PM
        冬令時間：10/1~3/31
        *升旗：6:30AM    降旗：5:10 PM
        
        三軍儀隊每日交接時間：
        上午：9:00、10:00、11:00、12:00
        下午：1:00、2:00、3:00、4:00、5:00
        (開館期間每日整點交接一次)
    """.trimIndent(),
            staytime = "",
            modified = "2024-05-26 05:01:57 +08:00",
            url = "https://www.travel.taipei/zh-tw/attraction/details/23",
            category = listOf(
                Category(id = 13, name = "歷史建築"),
                Category(id = 15, name = "藝文館所"),
                Category(id = 25, name = "無障礙旅遊推薦景點")
            ),
            target = listOf(
                com.example.sunwayexam.model.attraction.Target(id = 61, name = "親子共學"),
                com.example.sunwayexam.model.attraction.Target(id = 62, name = "校外教學"),
                com.example.sunwayexam.model.attraction.Target(id = 65, name = "賞鳥族")
            ),
            service = listOf(
                Service(id = 141, name = "無障礙設施"),
                Service(id = 143, name = "場地租用")
            ),
            friendly = emptyList(),
            images = listOf(
                Image(src = "https://www.travel.taipei/image/222951", subject = "", ext = ".jpg"),
                Image(src = "https://www.travel.taipei/image/222952", subject = "", ext = ".jpg")
            ),
            files = emptyList(),
            links = emptyList()
        )
    }

    val testAttractionUiModel = AttractionUiModel(
        id = 23,
        name = "國立中正紀念堂",
        introduction = """
        中正紀念堂是為了紀念中華民國第一任總統蔣介石，他於1975年逝世，次年行政院就破土開建中正紀念堂，設計師是設計圓山大飯店的楊卓成。

        紀念堂原址是陸軍總部，當時是臺北市區內最大的軍區，據說從大陸運過來的黃金，就曾經存放在這個地方。紀念堂採用的藍白兩色，是國旗上面主要的顏色，紀念堂頂部天穹的裝飾是青天白日12道光芒，銅像朝西面對總統府和中國大陸，其中都有特殊的意義。隨著民主演進思想開放，紀念堂前的廣場已成了民主運動的集會場。

        位於中正紀念堂左右兩側的國家音樂廳與國家戲劇院。戲劇院為戲劇、舞蹈等表演活動的場地，經常舉辦大型的表演活動，包括京劇、戲劇、兒童節目等；音樂廳是臺灣最具水準的音樂展演空間，經常舉辦室內樂，交響樂等大型的音樂活動。而兩廳院前廣場也會不定時舉辦戲劇、音樂活動，吸引眾多遊客前往欣賞。

        白牆藍瓦，70公尺高聳立在紀念公園的中央，不論從哪個角度看過去，都很宏偉。國外觀光客來到臺灣，不論行程如何排，都一定會到這裡來參觀一下。紀念堂的下方有陳列和展覽，也有紀念品販售。
    """.trimIndent(),
        address = "100 臺北市中正區中山南路21號",
        images = listOf(
            Image(src = "https://www.travel.taipei/image/222951", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222952", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222953", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222954", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222955", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222956", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222957", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222958", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222959", subject = "", ext = ".jpg"),
            Image(src = "https://www.travel.taipei/image/222960", subject = "", ext = ".jpg")
        ),
        modified = "2024-05-26 05:01:57 +08:00",
        officialSite = "https://www.travel.taipei/zh-tw/attraction/details/23"
    )
}