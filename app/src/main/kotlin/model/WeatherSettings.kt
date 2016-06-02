package model

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import utils.Constants

/**
 * Created by domin_000 on 02.06.2016.
 */
class WeatherSettings {

    @SerializedName("chosenCity")
    @Expose
    var chosenCity: String? = null

    @SerializedName("cities")
    @Expose
    var cities: List<String>? = null

    @SerializedName("weatherData")
    @Expose
    var weatherData: List<WeatherData>? = null


    companion object{
        private val gson: Gson
            get (){
                val builder = GsonBuilder();
                builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                builder.excludeFieldsWithoutExposeAnnotation();
                return builder.create();
            }

        fun getFromSettings(getSharedPreferences : (String, Int) -> SharedPreferences) : WeatherSettings{
            val weatherPreferences = getSharedPreferences(Constants.WeatherData.value, 0)
            return gson.fromJson(weatherPreferences.getString(Constants.WeatherData.value, "{}"), WeatherSettings::class.java)
        }

        fun setSettings(weatherSettings: WeatherSettings,getSharedPreferences : (String, Int) -> SharedPreferences){
            val weatherPreferences = getSharedPreferences(Constants.WeatherData.value, 0).edit()
            weatherPreferences.putString(Constants.WeatherData.value, gson.toJson(weatherSettings))
            weatherPreferences.commit()
        }
    }
}