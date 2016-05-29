package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by domin_000 on 29.05.2016.
 */
class WeatherData {
    /**

     * @return
     * * The coord
     */
    /**

     * @param coord
     * * The coord
     */
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    /**

     * @return
     * * The sys
     */
    /**

     * @param sys
     * * The sys
     */
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    /**

     * @return
     * * The weather
     */
    /**

     * @param weather
     * * The weather
     */
    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList()
    /**

     * @return
     * * The base
     */
    /**

     * @param base
     * * The base
     */
    @SerializedName("base")
    @Expose
    var base: String? = null
    /**

     * @return
     * * The main
     */
    /**

     * @param main
     * * The main
     */
    @SerializedName("main")
    @Expose
    var main: Main? = null
    /**

     * @return
     * * The wind
     */
    /**

     * @param wind
     * * The wind
     */
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    /**

     * @return
     * * The clouds
     */
    /**

     * @param clouds
     * * The clouds
     */
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    /**

     * @return
     * * The dt
     */
    /**

     * @param dt
     * * The dt
     */
    @SerializedName("dt")
    @Expose
    var dt: Int? = null
    /**

     * @return
     * * The id
     */
    /**

     * @param id
     * * The id
     */
    @SerializedName("id")
    @Expose
    var id: Int? = null
    /**

     * @return
     * * The name
     */
    /**

     * @param name
     * * The name
     */
    @SerializedName("name")
    @Expose
    var name: String? = null
    /**

     * @return
     * * The cod
     */
    /**

     * @param cod
     * * The cod
     */
    @SerializedName("cod")
    @Expose
    var cod: Int? = null
}