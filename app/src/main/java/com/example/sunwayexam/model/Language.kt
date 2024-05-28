package com.example.sunwayexam.model

/**
 * zh-tw -正體中文
 * zh-cn -簡體中文
 * en -英文
 * ja -日文
 * ko -韓文
 * es -西班牙文
 * id -印尼文
 * th -泰文
 * vi -越南文
 */
enum class Language(
    val languageCode: String,
    val languageName: String
) {
    ZH_TW("zh-tw", "繁體中文"),
    ZH_CN("zh-cn", "zh-cn"),
    EN("en", "en"),
    JA("ja", "ja"),
    KO("ko", "ko"),
    ES("es", "es"),
    ID("id", "id"),
    TH("th", "th"),
    VI("vi", "vi");

}