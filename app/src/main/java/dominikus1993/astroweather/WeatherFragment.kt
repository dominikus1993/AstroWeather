package dominikus1993.astroweather

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import dependency.MyLocalizationPresenterDependencyResolver
import dependency.WeatherPresenterDependencyResolver
import model.AppData
import model.Localization
import model.LocalizationWeatherData
import presenters.IMyLocalizationPresenter
import presenters.IWeatherPresenter
import utils.AppConstants
import utils.AstroCalculatorUtils
import utils.PreferencesUtils
import utils.WeatherUtils
import view.IAstroWeatherView
import view.ILocalizationsView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment(), IAstroWeatherView<LocalizationWeatherData> , ILocalizationsView{

    private lateinit var presenter:IWeatherPresenter
    private lateinit var localizationPresenter:IMyLocalizationPresenter
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
        presenter.getWeatherDataByLocalization(Localization("London"), this.context, settings)
        localizationPresenter = MyLocalizationPresenterDependencyResolver.get(this)
        localizationPresenter.showAllMyCities { s, i -> activity.getSharedPreferences(s,i) }
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

        PreferencesUtils.setPreferences({s,i -> this.activity.getSharedPreferences(s,i)}, AppData(settings.location, settings.interval, WeatherUtils.changeWeatherByLocalization(settings, data)))
    }

    override fun showLocalizations(data: AppData) {
        val localizations = data.weatherData.localizationWeatherData?.map { it -> it.localization?.cityName }?.toTypedArray()
        val spinner = this.view?.findViewById(R.id.localizationsS) as Spinner
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, localizations)
        spinner.adapter = adapter
        spinner.setSelection(localizations?.indexOf(data.weatherData.chosenCity) ?: 0)

    }
}// Required empty public constructor
