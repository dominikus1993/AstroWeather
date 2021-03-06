package utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import com.astrocalculator.AstroCalculator
import com.astrocalculator.AstroDateTime
import model.MoonData
import model.SunData
import org.joda.time.DateTime
import java.util.*

/**
 * Created by domin_000 on 27.05.2016.
 */
class AstroCalculatorUtils{
    companion object{
        fun getSunData(location: AstroCalculator.Location): SunData {
            val time = DateTime()
            val calendar = GregorianCalendar()
            val moonModel = AstroCalculator(AstroDateTime(time.yearOfEra, time.monthOfYear, time.dayOfMonth, time.hourOfDay, time.minuteOfHour, time.secondOfMinute, calendar.timeZone.rawOffset, true), location)
            return SunData(moonModel.sunInfo, location)
        }

        fun getMoonData(location: AstroCalculator.Location): MoonData {
            val time = DateTime()
            val calendar = GregorianCalendar()
            val moonModel = AstroCalculator(AstroDateTime(time.yearOfEra, time.monthOfYear, time.dayOfMonth, time.hourOfDay, time.minuteOfHour, time.secondOfMinute, calendar.timeZone.rawOffset, true), location)
            return MoonData(moonModel.moonInfo, location)
        }

        fun isOnline(context : Context) :Boolean {
            val  connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo;
            return networkInfo != null && networkInfo.isConnectedOrConnecting;
        }

        fun hasPermissions(context:Context, permissions:Array<String>):Boolean{
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

