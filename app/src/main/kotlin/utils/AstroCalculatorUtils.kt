package utils

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
    }
}