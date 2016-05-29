package presenters

import android.content.Context
import android.os.Handler
import android.util.Log
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
class WeatherPresenter : IWeatherPresenter{
    private val view: IAstroWeatherView<LocalizationWeatherData>;
    private val context:Context
    private val service:IOpenWeatherService

    constructor(view: IAstroWeatherView<LocalizationWeatherData>, openWeatherService :IOpenWeatherService, context:Context) {
        this.view = view
        this.context = context
        service = openWeatherService
    }

    override fun getWeatherDataByLocalization(localization: Localization, handler:Handler): Runnable {
        return object : Runnable {
            override fun run() {
                    if(AstroCalculatorUtils.isOnline(context)){
                        val data = service.getWeatherForLocalization(localization.latitude, localization.longitude, ConfigUtil.getByKey(context, Constants.OpenWeatherApiKey.value) as String)
                        val runnable = this
                        data.enqueue(object : retrofit2.Callback<WeatherData>{
                            override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                                val res = response?.body()
                                view.showData(LocalizationWeatherData(localization, res ))
                                handler.postDelayed(runnable, 3600000);
                            }

                            override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                                Log.w("", "No nie udało sie")
                                handler.postDelayed(runnable, 3600000);
                            }
                        })
                    }
            }
        }
    }
}