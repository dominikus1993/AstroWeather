package model

import com.astrocalculator.AstroCalculator

/**
 * Created by domin_000 on 27.05.2016.
 */

data class AppData(val location: AstroCalculator.Location, val interval:Int, val weatherData:WeatherData)