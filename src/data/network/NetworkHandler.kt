package com.manqana.data.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Exception


object NetworkHandler {

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }


    suspend fun makeRequest(
        url: String,
        model: String = "A4",
        brand: String = "Audi",
        lotYearFrom: String = "2010",
        lotYearTo: String = "2017"
    ): Any {
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("sort", " auction_date_type desc,auction_date_utc asc")
            .addFormDataPart("defaultSort", " true")
            .addFormDataPart(
                "filter[MISC]",
                " #MakeCode:${brand.toUpperCase()} OR #MakeDesc:$brand,#LotModel:$model,#VehicleTypeCode:VEHTYPE_V,#LotYear:[$lotYearFrom TO $lotYearTo]"
            )
            .addFormDataPart("size", " 200")
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .method("POST", body)
            .addHeader(
                "Cookie",
                "visid_incap_242093=GOsIy1z1QxG3NoqrkwlxVC2KO18AAAAAQUIPAAAAAABgzuw88GISqAGKXqeDdl39; s_fid=251196FB2C34A164-2631E7469A3A5153; _gcl_au=1.1.1196621938.1597737531; _ga=GA1.2.1549712344.1597737531; s_vi=[CS]v1|2F9DC51D8515C24B-6000060432BC38AB[CE]; __cfduid=d4afa1a2d866ed4fb3dfa9f196cdf37951597737534; g2usersessionid=12863a5a8f22c4bc666d76b0ca01d386; userLang=en; copartTimezonePref=%7B%22displayStr%22%3A%22GST%22%2C%22offset%22%3A4%2C%22dst%22%3Afalse%2C%22windowsTz%22%3A%22Asia%2FDubai%22%7D; timezone=Asia%2FDubai; s_cc=true; _gid=GA1.2.502710606.1599382801; G2JSESSIONID=FD0232348004FB1719D472AF9A81DF8A-n1; incap_ses_535_242093=4e5fCztiTQiqU6AAy7NsB+jVVF8AAAAAIrYJIQkq8qrFUrHkrO3N8A==; s_sq=%5B%5BB%5D%5D; incap_ses_467_242093=ex/nO/kjPXMbCgAyLh57BlTuVF8AAAAAG8RlE5C6ugfVX5JskWuB2g==; s_vnum=1600329530533%26vn%3D7; s_invisit=true; s_lv_s=Less%20than%201%20day; _uetsid=389efaa9f424327eb8345cc13829ecf4; _uetvid=ed79eeedbbe2348f34d5aa6900fb6e9f; s_depth=3; s_pv=public%3Avehiclefinder; s_ppvl=member%253AsearchResults%2C30%2C26%2C835%2C887%2C835%2C1680%2C1050%2C2%2CP; s_ppv=public%253Avehiclefinder%2C56%2C56%2C835%2C887%2C835%2C1680%2C1050%2C2%2CP; s_nr=1599402050197-Repeat; s_lv=1599402050199; g2usersessionid=12863a5a8f22c4bc666d76b0ca01d386"
            )
            .build()
        val response: Response = client.newCall(request).execute()
        return try {
            response.body?.string()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        } ?: "null"
    }
}