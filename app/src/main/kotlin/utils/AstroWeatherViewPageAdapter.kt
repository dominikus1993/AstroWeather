package utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by domin_000 on 27.05.2016.
 */
abstract class AbstractAstroWeatherPageAdapter(val manager: FragmentManager) : FragmentPagerAdapter (manager){
    abstract fun addFragment(fragment:Fragment, title:String)
}

open class AstroWeatherViewPageAdapter(manager: FragmentManager) : AbstractAstroWeatherPageAdapter (manager){

    private val  mFragmentList: ArrayList<Fragment> = arrayListOf()
    private val mFragmentTitleList:ArrayList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment? {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun addFragment(fragment:Fragment, title:String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int):String{
        return mFragmentTitleList[position]
    }

}


open class LandscapePageAdapter(manager: FragmentManager)  : AstroWeatherViewPageAdapter(manager) {
    override fun getPageWidth(position: Int): Float {
        return 1f / 2;
    }
}