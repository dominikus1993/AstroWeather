package presenters

import android.content.Context
import android.os.Handler
import model.Localization
import model.LocalizationWeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    constructor(view: IAstroWeatherView<LocalizationWeatherData>, context:Context) {
        this.view = view
        this.context = context
        service = Retrofit.Builder().baseUrl(ConfigUtil.getByKey(context,  Constants.OpenWeatherBaseUrl.value)).addConverterFactory(GsonConverterFactory.create()).build().create(IOpenWeatherService::class.java)
    }

    fun getWeatherDataByLocalization(localization: Localization, handler:Handler): Runnable {
        return object : Runnable {
            override fun run() {

                if(AstroCalculatorUtils.isOnline(context)){
                    val data = service.getWatherForLocalization(localization.latitude, localization.longitude, ConfigUtil.getByKey(context, Constants.OpenWeatherApiKey.value) as String)
                    val response = data.execute()
                    view.showData(LocalizationWeatherData(localization, response.body()))
                }


                handler.postDelayed(this, 3600000);
            }
        }
    }
}