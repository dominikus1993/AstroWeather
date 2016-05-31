package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */

class LocalizationWeatherData(){

    constructor(localization: Localization?, weatherData: WeatherData?):this(){
        this.localization = localization
        this.weatherData = weatherData
    }

    @SerializedName("localization")
    @Expose
    var localization:Localization? = null

    @SerializedName("weatherData")
    @Expose
    var weatherData:WeatherData? = null

}