package presenters

import android.content.SharedPreferences
import model.WeatherSettings
import view.IAstroWeatherView

/**
 * Created by domin_000 on 01.06.2016.
 */
class MyLocalizationsPresenter : IMyLocalizationPresenter{
    private val view: IAstroWeatherView<WeatherSettings>;

    constructor(view: IAstroWeatherView<WeatherSettings>) {
        this.view = view
    }


    override fun showAllMyCities(getSharedPreferences : (String, Int) -> SharedPreferences){
        val settings = WeatherSettings.getFromSettings { s, i -> getSharedPreferences(s, i) }
        view.showData(settings)
    }
}