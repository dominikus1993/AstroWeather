package utils

import android.content.Context
import android.content.res.Resources
import android.util.Log
import dominikus1993.astroweather.R
import java.io.IOException
import java.util.*

/**
 * Created by domin_000 on 29.05.2016.
 */
class ConfigUtil{
    companion object{
        private val TAG = "HELPER"
        fun getByKey(context:Context, name:String): String? {
            val resources = context.resources;

            try{
                val rawResource = resources.openRawResource(R.raw.config)
                val properties = Properties()
                properties.load(rawResource)
                return properties.getProperty(name)
            }catch(ex:Resources.NotFoundException){
                Log.e(TAG,ex.message)
            }catch(ex:IOException){
                Log.e(TAG, ex.message)
            }
            return null;
        }
    }
}