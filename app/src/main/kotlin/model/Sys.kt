package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */
class Sys {
    /**

     * @return
     * * The type
     */
    /**

     * @param type
     * * The type
     */
    @SerializedName("type")
    @Expose
    var type: Int? = null
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
     * * The message
     */
    /**

     * @param message
     * * The message
     */
    @SerializedName("message")
    @Expose
    var message: Double? = null
    /**

     * @return
     * * The country
     */
    /**

     * @param country
     * * The country
     */
    @SerializedName("country")
    @Expose
    var country: String? = null
    /**

     * @return
     * * The sunrise
     */
    /**

     * @param sunrise
     * * The sunrise
     */
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null
    /**

     * @return
     * * The sunset
     */
    /**

     * @param sunset
     * * The sunset
     */
    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null
}
