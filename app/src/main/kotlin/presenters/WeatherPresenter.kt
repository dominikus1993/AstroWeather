package presenters

import android.content.Context
import android.util.Log
import model.WeatherData
import model.WeatherSettings
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
    private val view: IAstroWeatherView<WeatherData?>;
    private val service: IOpenWeatherService

    constructor(view: IAstroWeatherView<WeatherData?>, openWeatherService: IOpenWeatherService) {
        this.view = view
        service = openWeatherService
    }

    override fun getWeatherDataByLocalization(context: Context): (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit {
        return {weatherSettings: WeatherSettings, onSuccess : (WeatherData?) -> Unit, onFailure: (Throwable?) -> Unit ->
            if (AstroCalculatorUtils.isOnline(context) && weatherSettings.chosenCity != null) {
                val data = service.getWeatherForLocalization(weatherSettings.chosenCity as String, ConfigUtil.getByKey(context, Constants.OpenWeatherApiKey.value) as String)
                data.enqueue(object : retrofit2.Callback<WeatherData> {
                    override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                        val res = response?.body()
                        onSuccess(res)
                    }

                    override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                        Log.w("", "No nie udało sie")
                        onFailure(t)
                    }
                })
            }else{
                onSuccess(weatherSettings.weatherData?.filter{it -> it.city?.name?.equals(weatherSettings.chosenCity) ?: false}.firstOrNull())
            }
        }
    }
}