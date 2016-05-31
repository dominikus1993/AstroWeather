package utils

import android.content.SharedPreferences
import com.astrocalculator.AstroCalculator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.AppData
import model.WeatherData

/**
 * Created by domin_000 on 27.05.2016.
 */
class PreferencesUtils{
    companion object{
        private val gson:Gson
        get (){
            val builder = GsonBuilder();
            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            builder.excludeFieldsWithoutExposeAnnotation();
            return builder.create();
        }

        fun getPreferences(getSharedPreferences : (String, Int) -> SharedPreferences): AppData {
            val locationPreferences = getSharedPreferences(Constants.Location.value, 0)
            val intervalPreferences = getSharedPreferences(Constants.Interval.value, 0)
            val weatherPreferences = getSharedPreferences(Constants.WeatherData.value, 0)

            val longitude = locationPreferences.getFloat(Constants.Longitude.value, 0F).toDouble()
            val latitude = locationPreferences.getFloat(Constants.Latitude.value, 0F).toDouble()

            val location = AstroCalculator.Location(latitude, longitude)
            val interval = intervalPreferences.getInt(Constants.Interval.value, 1)

            val weatherData = gson.fromJson(weatherPreferences.getString(Constants.WeatherData.value, "{}"), WeatherData::class.java)

            return AppData(location, interval, weatherData)
        }
        fun setPreferences(getSharedPreferences : (String, Int) -> SharedPreferences, appData: AppData) {
            val locationPreferencesEditor = getSharedPreferences(Constants.Location.value, 0).edit()
            val intervalPreferencesEditor = getSharedPreferences(Constants.Interval.value, 0).edit()
            val weatherPreferences = getSharedPreferences(Constants.WeatherData.value, 0).edit()

            locationPreferencesEditor.putFloat(Constants.Latitude.value, appData.location.latitude.toFloat())
            locationPreferencesEditor.putFloat(Constants.Longitude.value, appData.location.longitude.toFloat())
            locationPreferencesEditor.commit()

            intervalPreferencesEditor.putInt(Constants.Interval.value, appData.interval)
            intervalPreferencesEditor.commit()

            weatherPreferences.putString(Constants.WeatherData.value, gson.toJson(appData.weatherData))
            weatherPreferences.commit()

        }
    }

}