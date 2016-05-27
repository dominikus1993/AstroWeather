package dependency

import model.MoonData
import model.SunData
import presenters.IMoonFragmentPresenter
import presenters.ISunFragmentPresenter
import presenters.MoonFragmentPresenter
import presenters.SunFragmentPresenter
import view.IAstroWeatherView

/**
 * Created by domin_000 on 28.05.2016.
 */
class SunFragmentDependencyResolver{
    companion object{
        fun get(view: IAstroWeatherView<SunData>): ISunFragmentPresenter {
            return SunFragmentPresenter(view)
        }
    }
}