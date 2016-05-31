package presenters

import android.content.Context
import model.AppData
import model.Localization

/**
 * Created by domin_000 on 29.05.2016.
 */
interface IWeatherPresenter{
    fun getWeatherDataByLocalization(localization: Localization, context: Context, settings: AppData)
}