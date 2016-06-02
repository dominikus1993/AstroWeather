package presenters

import android.content.Context
import model.WeatherData
import model.WeatherSettings
import services.IOpenWeatherService

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IWeatherPresenter{
    fun getWeatherDataByLocalization(context: Context, service: IOpenWeatherService): (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit
}