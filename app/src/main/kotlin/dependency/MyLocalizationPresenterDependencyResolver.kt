package dependency

import model.AppData
import presenters.IMyLocalizationPresenter
import presenters.MyLocalizationsPresenter
import view.IAstroWeatherView
import view.ILocalizationsView

/**
 * Created by domin_000 on 01.06.2016.
 */
class MyLocalizationPresenterDependencyResolver{
    companion object {
        fun get(view: ILocalizationsView):IMyLocalizationPresenter{
            return MyLocalizationsPresenter(view)
        }
    }
}