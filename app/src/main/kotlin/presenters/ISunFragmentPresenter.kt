package presenters

import android.os.Handler
import model.AppData

/**
 * Created by domin_000 on 27.05.2016.
 */
interface ISunFragmentPresenter{
    fun sunDataTimer(handler: Handler, appSettings: AppData): Runnable
}