package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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

}