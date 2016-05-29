package services

import model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IOpenWeatherService {
    @GET("/")
    fun getWatherForLocalization(@Query("lat") latitude:Double, @Query("lon") longitude:Double, @Query("appId") appId: String): Call<WeatherData>
}