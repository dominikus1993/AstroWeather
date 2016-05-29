package services

import model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IOpenWeatherService {
    @GET( "?lat={lat}&lon={lon}&appid={appId}")
    fun getWatherForLocalization(@Path("lat") latitude:Double, @Path("lon") longitude:Double, @Path("appId") appId: String): Call<WeatherData>
}