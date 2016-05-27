package presenters

import android.os.Handler

/**
 * Created by domin_000 on 27.05.2016.
 */
interface IAstroWeatherMainActivityPresenter {
    fun getDateTime(handler: Handler) : Runnable
}