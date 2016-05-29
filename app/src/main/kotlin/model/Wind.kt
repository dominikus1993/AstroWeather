package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */
class Wind {

    /**

     * @return
     * * The speed
     */
    /**

     * @param speed
     * * The speed
     */
    @SerializedName("speed")
    @Expose
    var speed: Double? = null
    /**

     * @return
     * * The deg
     */
    /**

     * @param deg
     * * The deg
     */
    @SerializedName("deg")
    @Expose
    var deg: Int? = null

}
