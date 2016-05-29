package dominikus1993.astroweather

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dependency.WeatherPresenterDependencyResolver
import model.Localization
import model.LocalizationWeatherData
import presenters.IWeatherPresenter
import utils.AppConstants
import utils.AstroCalculatorUtils
import view.IAstroWeatherView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment(), IAstroWeatherView<LocalizationWeatherData> {

    private val handler = Handler()
    private lateinit var presenter:IWeatherPresenter
    private lateinit var test:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WeatherPresenterDependencyResolver.get(this, context)
        requestNetworkState()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_weather, container, false)
        test = view.findViewById(R.id.test) as TextView
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(presenter.getWeatherDataByLocalization(Localization(19.45,51.77), handler), 0L)
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

    override fun showData(data: LocalizationWeatherData) {
        test.text = data.weatherData.main.temp.toString()
    }
}// Required empty public constructor
