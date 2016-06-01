package dependency

import android.content.Context
import model.LocalizationWeatherData
import presenters.IWeatherPresenter
import presenters.WeatherPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.IOpenWeatherService
import utils.AccuWeatherServiceBuilder
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenterDependencyResolver{
    companion object{
        fun get(view: IAstroWeatherView<LocalizationWeatherData>, context: Context):IWeatherPresenter{
            return WeatherPresenter(view, AccuWeatherServiceBuilder.getService(context) as IOpenWeatherService)
        }
    }
}