package dominikus1993.astroweather

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dependency.WeatherPresenterDependencyResolver
import model.AppData
import model.WeatherData
import model.WeatherSettings
import presenters.IMyLocalizationPresenter
import presenters.IWeatherPresenter
import services.IOpenWeatherService
import utils.AccuWeatherServiceBuilder
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
class WeatherFragment : Fragment(), IAstroWeatherView<WeatherData?>{

    private lateinit var presenter:IWeatherPresenter
    private lateinit var localizationPresenter:IMyLocalizationPresenter
    private lateinit var test:TextView
    private lateinit var settings:AppData
    private lateinit var presenterFun:(WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNetworkState()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_weather, container, false)
        setUp(view)
        presenter = WeatherPresenterDependencyResolver.get()
        presenterFun = presenter.getWeatherDataByLocalization(context, AccuWeatherServiceBuilder.getService(context) as IOpenWeatherService)

        refresh(WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }, presenterFun)

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

    fun setUp(view: View?){
        test = view?.findViewById(R.id.test) as TextView


        //spiner
        val settings = WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }
        val spinner = this.view?.findViewById(R.id.localizationsS) as Spinner
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, settings.cities)
        spinner.adapter = adapter
        spinner.setSelection(settings.cities?.indexOf(settings.chosenCity) ?: 0)


        spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                refresh(WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }, presenterFun)
            }

        }

    }

    fun refresh(weatherSettings: WeatherSettings, presenterFun:  (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit){
        presenterFun(weatherSettings, {it ->
            this.showData(it)
        }, {it ->
            val toast = Toast.makeText(context, it?.message, Toast.LENGTH_SHORT)
            toast.show()
        })
    }

    override fun showData(data: WeatherData?) {
        throw UnsupportedOperationException()
    }
}// Required empty public constructor
