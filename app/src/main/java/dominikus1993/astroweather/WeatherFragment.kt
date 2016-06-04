package dominikus1993.astroweather

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dependency.WeatherPresenterDependencyResolver
import model.WeatherData
import model.WeatherSettings
import okhttp3.ResponseBody
import presenters.IWeatherPresenter
import retrofit2.Call
import retrofit2.Response
import services.IOpenWeatherService
import utils.*
import view.IAstroWeatherView
import java.io.BufferedInputStream


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
    private lateinit var test:TextView
    private lateinit var wind:TextView
    private lateinit var pressure:TextView
    private lateinit var windDirection:TextView
    private lateinit var presenterFun:(WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit;
    private lateinit var hoursAdapter:ArrayAdapter<String>
    private lateinit var hoursSpinner:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNetworkState()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_weather, container, false)
        presenter = WeatherPresenterDependencyResolver.get()
        presenterFun = presenter.getWeatherDataByLocalization(context, AccuWeatherServiceBuilder.getService(context) as IOpenWeatherService)
        return view
    }

    override fun onStart() {
        super.onStart()
        setUp(view)
        refresh(WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }, presenterFun)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
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
        test = view?.findViewById(R.id.temp) as TextView
        wind = view?.findViewById(R.id.wind) as TextView
        pressure = view?.findViewById(R.id.pressure) as TextView
        windDirection = view?.findViewById(R.id.windDirection) as TextView
        //spinner
        val settings = WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }
        val spinner = view?.findViewById(R.id.localizationsS) as Spinner
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, settings.cities)
        spinner.adapter = adapter
        spinner.setSelection(settings.cities?.indexOf(settings.chosenCity) ?: 0)

        hoursSpinner = view?.findViewById(R.id.hours) as Spinner
        hoursAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, mutableListOf())
        hoursSpinner.adapter = hoursAdapter

        hoursSpinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val settings = WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }
                showData(settings.weatherData?.filter { it -> it.city?.name.equals(settings.chosenCity)}?.first())
            }

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val settings = WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }
                settings.chosenCity = settings.cities?.get(position)
                WeatherSettings.setSettings(settings, { s, i -> activity.getSharedPreferences(s, i) })
                refresh(WeatherSettings.getFromSettings { s, i -> activity.getSharedPreferences(s, i) }, presenterFun)
            }

        }

    }

    fun refresh(weatherSettings: WeatherSettings, presenterFun:  (WeatherSettings, (WeatherData?) -> Unit, (Throwable?) -> Unit) -> Unit){
        presenterFun(weatherSettings, {it ->
            hoursAdapter.clear()
            hoursAdapter.addAll(it?.list?.map { x -> x.dtTxt})
            hoursSpinner.setSelection(0)
            this.showData(it)
        }, {it ->
            val toast = Toast.makeText(context, it?.message, Toast.LENGTH_SHORT)
            toast.show()
        })
    }


    override fun showData(data: WeatherData?) {
        val hour = data?.list?.get(hoursSpinner.selectedItemPosition)
        test.text = (hour?.main?.temp?.minus(273)?.format(2)).toString()
        wind.text = "${hour?.wind?.speed} m/s"
        windDirection.text = hour?.wind?.deg?.dagreeToDirection()
        pressure.text = hour?.main?.pressure.toString()
        setBackground(hour?.weather?.first()?.icon ?: "")
    }

    fun setBackground(icon: String): Unit {
        val service = AccuWeatherServiceBuilder.getIconService()
        if(AstroCalculatorUtils.isOnline(context)){
            val res = service.getWeatherForLocalization("$icon.png").enqueue(object: retrofit2.Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    view?.background = activity.resources.getDrawable(R.drawable.ic_close_24dp)
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    try{
                        val stream = response?.body()?.byteStream()
                        val buffered = BufferedInputStream(stream)
                        val bitmap = BitmapFactory.decodeStream(buffered)
                        view?.background = BitmapDrawable(resources,bitmap)
                    }catch(ex:Exception){
                        view?.background = activity.resources.getDrawable(R.drawable.ic_close_24dp)
                    }

                }
            })
        }
        else{
            val toast = Toast.makeText(context, "Brak połaczenia internetowego", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}// Required empty public constructor
