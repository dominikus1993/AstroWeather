package services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by domin_000 on 03.06.2016.
 */
interface IIconService {
    @GET("img/w/{icon}")
    fun getWeatherForLocalization(@Path("icon") icon:String):Call<ResponseBody>
}