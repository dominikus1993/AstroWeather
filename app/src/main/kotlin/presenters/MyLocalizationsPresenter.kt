package presenters

import android.content.SharedPreferences
import model.AppData
import utils.PreferencesUtils
import view.IAstroWeatherView
import view.ILocalizationsView

/**
 * Created by domin_000 on 01.06.2016.
 */
class MyLocalizationsPresenter : IMyLocalizationPresenter{
    private val view: ILocalizationsView;

    constructor(view: ILocalizationsView) {
        this.view = view
    }


    override fun showAllMyCities(getSharedPreferences : (String, Int) -> SharedPreferences){
        val myData = PreferencesUtils.getPreferences { s, i -> getSharedPreferences(s,i) }

        view.showLocalizations(myData)
    }
}