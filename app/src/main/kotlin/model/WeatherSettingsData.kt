package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 31.05.2016.
 */
class WeatherSettingsData(){

    @SerializedName("localizationWeatherData")
    @Expose
    var localizationWeatherData:List<LocalizationWeatherData>? = null

    @SerializedName("chosenCity")
    @Expose
    var chosenCity:String? = null

}