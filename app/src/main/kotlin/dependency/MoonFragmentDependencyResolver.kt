package dependency

import model.MoonData
import presenters.IMoonFragmentPresenter
import presenters.MoonFragmentPresenter
import view.IAstroWeatherView

/**
 * Created by domin_000 on 27.05.2016.
 */
class MoonFragmentDependencyResolver{
    companion object{
        fun get(view:IAstroWeatherView<MoonData>):IMoonFragmentPresenter{
            return MoonFragmentPresenter(view)
        }
    }
}