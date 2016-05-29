package dependency

import android.content.Context
import model.LocalizationWeatherData
import presenters.IWeatherPresenter
import presenters.WeatherPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.IOpenWeatherService
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenterDependencyResolver{
    companion object{
        fun get(view: IAstroWeatherView<LocalizationWeatherData>, context: Context):IWeatherPresenter{
            val service = Retrofit.Builder().baseUrl(ConfigUtil.getByKey(context,  Constants.OpenWeatherBaseUrl.value)).addConverterFactory(GsonConverterFactory.create()).build().create(IOpenWeatherService::class.java)
            return WeatherPresenter(view, service, context)
        }
    }
}