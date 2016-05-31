package presenters

import android.content.Context
import android.util.Log
import model.AppData
import model.Localization
import model.LocalizationWeatherData
import model.WeatherData
import retrofit2.Call
import retrofit2.Response
import services.IOpenWeatherService
import utils.AstroCalculatorUtils
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenter : IWeatherPresenter {
    private val view: IAstroWeatherView<LocalizationWeatherData>;
    private val service: IOpenWeatherService

    constructor(view: IAstroWeatherView<LocalizationWeatherData>, openWeatherService: IOpenWeatherService) {
        this.view = view
        service = openWeatherService
    }

    override fun getWeatherDataByLocalization(localization: Localization, context: Context, settings: AppData) {
        if (AstroCalculatorUtils.isOnline(context)) {
            val data = service.getWeatherForLocalization(localization.cityName, ConfigUtil.getByKey(context, Constants.OpenWeatherApiKey.value) as String)
            data.enqueue(object : retrofit2.Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                    val res = response?.body()
                    view.showData(LocalizationWeatherData(localization, res))
                }

                override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                    Log.w("", "No nie udało sie")

                }
            })
        }else{
        }
    }
}