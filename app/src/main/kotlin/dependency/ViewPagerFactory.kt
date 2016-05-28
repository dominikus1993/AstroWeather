package dependency

import android.content.res.Configuration
import android.support.v4.app.FragmentManager
import utils.AbstractAstroWeatherPageAdapter
import utils.AstroWeatherViewPageAdapter
import utils.LandscapePageAdapter

/**
 * Created by domin_000 on 28.05.2016.
 */
class ViewPagerFactory{
    companion object{
        fun get(isTablet:Boolean, orientation:Int, supportFragmentManager: FragmentManager): AbstractAstroWeatherPageAdapter {
            if(!isTablet){
                if (orientation == Configuration.ORIENTATION_PORTRAIT){
                    return AstroWeatherViewPageAdapter(supportFragmentManager)
                }
                else{
                   return LandscapePageAdapter(supportFragmentManager)
                }
            }
            else{
                return LandscapePageAdapter(supportFragmentManager)
            }
        }
    }
}