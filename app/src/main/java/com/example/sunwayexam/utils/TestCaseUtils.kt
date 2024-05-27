package com.example.sunwayexam.utils

import com.example.sunwayexam.model.attraction.AttractionUiModel
import com.example.sunwayexam.model.attraction.Image

object TestCaseUtils {

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
        url = "https://www.travel.taipei/zh-tw/attraction/details/23"
    )
}