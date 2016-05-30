package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by domin_000 on 30.05.2016.
 */
class City {

    /**
     * @return The id
     */
    /**
     * @param id The id
     */
    @SerializedName("id")
    @Expose
    var id: Int? = null
    /**
     * @return The name
     */
    /**
     * @param name The name
     */
    @SerializedName("name")
    @Expose
    var name: String? = null
    /**
     * @return The coord
     */
    /**
     * @param coord The coord
     */
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    /**
     * @return The country
     */
    /**
     * @param country The country
     */
    @SerializedName("country")
    @Expose
    var country: String? = null
    /**
     * @return The population
     */
    /**
     * @param population The population
     */
    @SerializedName("population")
    @Expose
    var population: Int? = null
    /**
     * @return The sys
     */
    /**
     * @param sys The sys
     */
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
}
