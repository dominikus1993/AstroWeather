package services


import model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IOpenWeatherService {
    @GET("data/2.5/weather")
    fun getWeatherForLocalization(@Query("lat") latitude:String, @Query("lon") longitude:String, @Query("appId") appId: String): Call<WeatherData>
}