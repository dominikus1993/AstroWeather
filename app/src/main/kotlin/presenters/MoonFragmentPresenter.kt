package presenters

import android.os.Handler
import model.AppData
import model.MoonData
import utils.AstroCalculatorUtils
import view.IAstroWeatherView

/**
 * Created by domin_000 on 27.05.2016.
 */
class MoonFragmentPresenter : IMoonFragmentPresenter{
    private val view:IAstroWeatherView<MoonData>;

    constructor(view: IAstroWeatherView<MoonData>) {
        this.view = view
    }


    override fun moonDataTimer(handler: Handler, appSettings:AppData): Runnable {
        return object : Runnable {
            override fun run() {
                view.showData(AstroCalculatorUtils.getMoonData(appSettings.location))
                handler.postDelayed(this, 1000);
            }
        }
    }
}