package dependency

import model.Time
import presenters.IAstroWeatherMainActivityPresenter
import presenters.MainActivityPresenter
import view.IAstroWeatherView

/**
 * Created by domin_000 on 27.05.2016.
 */
class MainActivityPresenterDependencyResolver{
    companion object{
        fun get(view:IAstroWeatherView<Time>) : IAstroWeatherMainActivityPresenter{
            return MainActivityPresenter(view)
        }
    }
}