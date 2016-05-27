package presenters

import android.os.Handler
import model.Time
import org.joda.time.DateTime
import utils.format
import view.IAstroWeatherView


/**
 * Created by domin_000 on 27.05.2016.
 */
class MainActivityPresenter : IAstroWeatherMainActivityPresenter{
    private val view:IAstroWeatherView<Time>

    constructor(view:IAstroWeatherView<Time>){
        this.view = view
    }

    override fun getDateTime(handler: Handler) : Runnable{
        return object : Runnable {
            override fun run() {
                val time = DateTime()

                view.showData(Time(time.format()))

                handler.postDelayed(this, 500)
            }
        }
    }
}