package utils

import model.AppData
import model.LocalizationWeatherData
import model.WeatherSettingsData

/**
 * Created by domin_000 on 31.05.2016.
 */
class WeatherUtils {
    companion object{
        fun changeWeatherByLocalization(settings:AppData, data:LocalizationWeatherData): WeatherSettingsData {
            val weatherData = WeatherSettingsData()
            weatherData.chosenCity = data.localization?.cityName
            weatherData.localizationWeatherData = settings.weatherData.localizationWeatherData?.map {it -> if(it.localization?.equals(weatherData.chosenCity) ?: false) data else it }
            return weatherData
        }
    }
}