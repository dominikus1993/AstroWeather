package presenters

import android.os.Handler
import model.Localization

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IWeatherPresenter{
    fun getWeatherDataByLocalization(localization: Localization, handler: Handler): Runnable
}