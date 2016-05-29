package presenters

import android.content.Context
import model.LocalizationWeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.IOpenWeatherService
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenter : IWeatherPresenter{
    private val view: IAstroWeatherView<LocalizationWeatherData>;
    private val service:IOpenWeatherService

    constructor(view: IAstroWeatherView<LocalizationWeatherData>, context:Context) {
        this.view = view
        service = Retrofit.Builder().baseUrl(ConfigUtil.getByKey(context,  Constants.OpenWeatherBaseUrl.value)).addConverterFactory(GsonConverterFactory.create()).build().create(IOpenWeatherService::class.java)
    }
}