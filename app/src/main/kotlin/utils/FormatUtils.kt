package utils

import com.astrocalculator.AstroDateTime
import org.joda.time.DateTime

/**
 * Created by domin_000 on 27.05.2016.
 */
fun AstroDateTime.format() = String.format("${if(this.day.toString().length == 1) "0${this.day}" else this.day.toString()}-${if(this.month.toString().length == 1) "0${this.month}" else this.month.toString()}-${this.year.toString()} ${if(this.hour.toString().length == 1) "0${this.hour}" else this.hour.toString()}:${if(this.minute.toString().length == 1) "0${this.minute}" else this.minute.toString()}:${if(this.second.toString().length == 1) "0${this.second}" else this.second.toString()}", this)

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

fun DateTime.format() = String.format(" ${if(this.hourOfDay.toString().length == 1) "0${this.hourOfDay}" else this.hourOfDay.toString()}:${if(this.minuteOfHour.toString().length == 1) "0${this.minuteOfHour}" else this.minuteOfHour.toString()}:${if(this.secondOfMinute.toString().length == 1) "0${this.secondOfMinute}" else this.secondOfMinute.toString()}", this)


fun Double.dagreeToDirection() : String {
    val value = ((this / 22.5) + 0.5).toInt()
    val array = arrayOf("N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW")
    return array[value % 16]
}