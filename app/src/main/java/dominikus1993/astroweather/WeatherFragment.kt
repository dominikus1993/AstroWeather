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
import model.AppData
import model.Localization
import model.LocalizationWeatherData
import model.WeatherData
import presenters.IWeatherPresenter
import utils.AppConstants
import utils.AstroCalculatorUtils
import utils.PreferencesUtils
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

    private lateinit var presenter:IWeatherPresenter
    private lateinit var test:TextView
    private lateinit var settings:AppData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WeatherPresenterDependencyResolver.get(this, this.context)
        requestNetworkState()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_weather, container, false)
        test = view.findViewById(R.id.test) as TextView
        settings = PreferencesUtils.getPreferences { s, i -> this.activity.getSharedPreferences(s,i) }
        presenter.getWeatherDataByLocalization(Localization("London"), this.context)
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

        test.text = data.weatherData?.list?.first()?.main?.temp.toString()

        PreferencesUtils.setPreferences({s,i -> this.activity.getSharedPreferences(s,i)}, AppData(settings.location, settings.interval, data.weatherData as WeatherData))
    }
}// Required empty public constructor
