package presenters

import model.Time
import view.IAstroWeatherView
import java.util.logging.Handler

/**
 * Created by domin_000 on 27.05.2016.
 */
class MainActivityPresenter : IAstroWeatherMainActivityPresenter{
    private val view:IAstroWeatherView<Time>

    constructor(view:IAstroWeatherView<Time>){
        this.view = view
    }

    fun getDateTime(handler:Handler){
        view.showData()
    }
}