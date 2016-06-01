package utils

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.IOpenWeatherService

/**
 * Created by domin_000 on 01.06.2016.
 */
class AccuWeatherServiceBuilder{
    companion object{
        fun getService(context:Context): IOpenWeatherService? {
            val service = Retrofit.Builder().baseUrl(ConfigUtil.getByKey(context,  Constants.OpenWeatherBaseUrl.value)).addConverterFactory(GsonConverterFactory.create()).build().create(IOpenWeatherService::class.java)
            return service
        }
    }
}