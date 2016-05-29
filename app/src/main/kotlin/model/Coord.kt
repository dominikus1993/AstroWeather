package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */
class Coord {

    /**

     * @return
     * * The lon
     */
    /**

     * @param lon
     * * The lon
     */
    @SerializedName("lon")
    @Expose
    var lon: Double? = null
    /**

     * @return
     * * The lat
     */
    /**

     * @param lat
     * * The lat
     */
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

}
