package services


import model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IOpenWeatherService {
    @GET("data/2.5/forecast")
    fun getWeatherForLocalization(@Query("q") cityName:String, @Query("appId") appId: String): Call<WeatherData>

    @GET("locations/v1/335315.json")
    fun getAllCities(@Query("apikey") apiKey:String)
}