package dominikus1993.astroweather

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import model.AppData
import model.Localization
import model.WeatherData
import model.WeatherSettings
import presenters.IMyLocalizationPresenter
import presenters.IWeatherPresenter
import utils.AppConstants
import utils.AstroCalculatorUtils


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment(){

    private lateinit var presenter:IWeatherPresenter
    private lateinit var localizationPresenter:IMyLocalizationPresenter
    private lateinit var test:TextView
    private lateinit var settings:AppData

    private lateinit var presenterFun:  (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNetworkState()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
    private fun requestNetworkState(){
        if(!AstroCalculatorUtils.hasPermissions(context, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET))) {
            ActivityCompat.requestPermissions(this.activity, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET), AppConstants.REQUEST_NETWORK_STATE);
        }
    }

    override fun onDetach() {
        super.onDetach()
    }
}// Required empty public constructor
