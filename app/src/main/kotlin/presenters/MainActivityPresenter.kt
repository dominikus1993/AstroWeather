package presenters

import com.astrocalculator.AstroDateTime
import model.Time
import view.IAstroWeatherView

/**
 * Created by domin_000 on 27.05.2016.
 */
class MainActivityPresenter : IAstroWeatherMainActivityPresenter{
    private val view:IAstroWeatherView<Time>

    constructor(view:IAstroWeatherView<Time>){
        this.view = view
    }
}