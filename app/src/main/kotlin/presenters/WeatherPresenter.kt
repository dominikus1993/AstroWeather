package presenters

import model.LocalizationWeatherData
import model.SunData
import model.WeatherData
import retrofit2.Retrofit
import view.IAstroWeatherView

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenter : IWeatherPresenter{
    private val view: IAstroWeatherView<LocalizationWeatherData>;
    private val service:Retrofit

    constructor(view: IAstroWeatherView<LocalizationWeatherData>) {
        this.view = view
        service = Retrofit.Builder().baseUrl()
    }
}