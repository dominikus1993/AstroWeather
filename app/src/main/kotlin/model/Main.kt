package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */
class Main {
    /**

     * @return
     * * The temp
     */
    /**

     * @param temp
     * * The temp
     */
    @SerializedName("temp")
    @Expose
    var temp: Double? = null
    /**

     * @return
     * * The pressure
     */
    /**

     * @param pressure
     * * The pressure
     */
    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null
    /**

     * @return
     * * The humidity
     */
    /**

     * @param humidity
     * * The humidity
     */
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null
    /**

     * @return
     * * The tempMin
     */
    /**

     * @param tempMin
     * * The temp_min
     */
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null
    /**

     * @return
     * * The tempMax
     */
    /**

     * @param tempMax
     * * The temp_max
     */
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null
}
