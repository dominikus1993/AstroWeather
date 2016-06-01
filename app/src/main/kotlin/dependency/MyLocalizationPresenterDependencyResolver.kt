package dependency

import model.AppData
import presenters.IMyLocalizationPresenter
import presenters.MyLocalizationsPresenter
import view.IAstroWeatherView

/**
 * Created by domin_000 on 01.06.2016.
 */
class MyLocalizationPresenterDependencyResolver{
    companion object {
        fun get(view: IAstroWeatherView<AppData>):IMyLocalizationPresenter{
            return MyLocalizationsPresenter(view)
        }
    }
}