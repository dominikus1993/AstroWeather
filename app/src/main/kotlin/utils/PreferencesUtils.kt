package utils

import android.content.SharedPreferences
import com.astrocalculator.AstroCalculator
import model.AppData

/**
 * Created by domin_000 on 27.05.2016.
 */
class PreferencesUtils{
    companion object{
        fun getPreferences(getSharedPreferences : (String, Int) -> SharedPreferences): AppData {
            val locationPreferences = getSharedPreferences(Constants.Location.value, 0)
            val intervalPreferences = getSharedPreferences(Constants.Interval.value, 0)

            val longitude = locationPreferences.getFloat(Constants.Longitude.value, 0F).toDouble()
            val latitude = locationPreferences.getFloat(Constants.Latitude.value, 0F).toDouble()

            val location = AstroCalculator.Location(latitude, longitude)
            val interval = intervalPreferences.getInt(Constants.Interval.value, 1)
            return AppData(location, interval)
        }
        fun setPreferences(getSharedPreferences : (String, Int) -> SharedPreferences, appData: AppData) {
            val locationPreferencesEditor = getSharedPreferences(Constants.Location.value, 0).edit()
            val intervalPreferencesEditor = getSharedPreferences(Constants.Interval.value, 0).edit()

            locationPreferencesEditor.putFloat(Constants.Latitude.value, appData.location.latitude.toFloat())
            locationPreferencesEditor.putFloat(Constants.Longitude.value, appData.location.longitude.toFloat())
            locationPreferencesEditor.commit()

            intervalPreferencesEditor.putInt(Constants.Interval.value, appData.interval)
            intervalPreferencesEditor.commit()

        }
    }

}