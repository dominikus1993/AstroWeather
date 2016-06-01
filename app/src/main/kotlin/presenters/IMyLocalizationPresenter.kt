package presenters

import android.content.SharedPreferences

/**
 * Created by domin_000 on 01.06.2016.
 */
interface IMyLocalizationPresenter{
    fun showAllMyCities(getSharedPreferences : (String, Int) -> SharedPreferences)
}