package presenters

import android.content.Context
import model.Localization
import model.WeatherData
import model.WeatherSettings

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IWeatherPresenter{
    fun getWeatherDataByLocalization(context: Context): (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit
}