package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 29.05.2016.
 */
class Weather {
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
     * * The main
     */
    /**

     * @param main
     * * The main
     */
    @SerializedName("main")
    @Expose
    var main: String? = null
    /**

     * @return
     * * The description
     */
    /**

     * @param description
     * * The description
     */
    @SerializedName("description")
    @Expose
    var description: String? = null
    /**

     * @return
     * * The icon
     */
    /**

     * @param icon
     * * The icon
     */
    @SerializedName("icon")
    @Expose
    var icon: String? = null
}
