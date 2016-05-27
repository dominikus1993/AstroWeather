package presenters

import android.os.Handler
import model.AppData
import model.SunData
import utils.AstroCalculatorUtils
import view.IAstroWeatherView

/**
 * Created by domin_000 on 27.05.2016.
 */
class SunFragmentPresenter : ISunFragmentPresenter{
    private val view: IAstroWeatherView<SunData>;

    constructor(view: IAstroWeatherView<SunData>) {
        this.view = view
    }

    override fun sunDataTimer(handler: Handler, appSettings: AppData): Runnable {
        return object : Runnable {
            override fun run() {

                view.showData(AstroCalculatorUtils.getSunData(appSettings.location))

                handler.postDelayed(this, appSettings.interval.toLong());
            }
        }
    }
}