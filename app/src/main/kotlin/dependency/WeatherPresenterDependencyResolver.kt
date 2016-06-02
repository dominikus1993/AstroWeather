package dependency

import presenters.IWeatherPresenter
import presenters.WeatherPresenter

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherPresenterDependencyResolver{
    companion object{
        fun get():IWeatherPresenter{
            return WeatherPresenter()
        }
    }
}