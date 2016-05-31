package utils

/**
 * Created by domin_000 on 27.05.2016.
 */
public enum class Constants(val value:String){
    Location("Location"),
    Longitude("longitude"),
    Latitude("latitude"),
    Interval("interval"),
    OpenWeatherBaseUrl("OpenWeatherBaseUrl"),
    OpenWeatherApiKey("OpenWeatherApiKey"),
    WeatherData("WeatherData")
}

 class AppConstants{
    companion object{
        public val REQUEST_NETWORK_STATE = 0
    }
}