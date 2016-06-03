package dominikus1993.astroweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.*
import dependency.MyLocalizationPresenterDependencyResolver
import model.WeatherData
import model.WeatherSettings
import presenters.IMyLocalizationPresenter
import retrofit2.Call
import retrofit2.Response
import utils.AccuWeatherServiceBuilder
import utils.AstroCalculatorUtils
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView
import java.util.*

class MyLocalizations : AppCompatActivity(), IAstroWeatherView<WeatherSettings>{

    private lateinit var localizationList : ListView
    private lateinit var presenter : IMyLocalizationPresenter
    private lateinit var adapter:ArrayAdapter<String?>
    private lateinit var list:ArrayList<String?>
    private lateinit var cityName: EditText
    private lateinit var addCity:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_localizations)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        list = arrayListOf()
        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, list)
        localizationList = findViewById(R.id.localizations) as ListView
        localizationList.adapter = adapter

        presenter = MyLocalizationPresenterDependencyResolver.get(this)
        presenter.showAllMyCities { s, i -> getSharedPreferences(s, i) }

        addCity = findViewById(R.id.saveCity) as Button
        cityName = findViewById(R.id.cityName) as EditText

        addCity.setOnClickListener { it ->
            val service = AccuWeatherServiceBuilder.getService(this.applicationContext)
            if(AstroCalculatorUtils.isOnline(applicationContext)){
                val data = service?.getWeatherForLocalization(cityName.text.toString(), ConfigUtil.getByKey(this.applicationContext, Constants.OpenWeatherApiKey.value) as String)

                data?.enqueue(object : retrofit2.Callback<WeatherData> {
                    override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                        val toast = Toast.makeText(applicationContext, "Nie ma takiego miasta nie można dodać miasta", Toast.LENGTH_SHORT)
                        toast.show()
                    }

                    override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                        val body = response?.body()
                        if(body != null){
                            val settings = WeatherSettings.getFromSettings { s, i -> getSharedPreferences(s, i) }
                            val newSettings = WeatherSettings()
                            newSettings.cities = settings.cities?.plus(listOf(body.city?.name as String))
                            newSettings.chosenCity = settings.chosenCity
                            newSettings.weatherData = settings.weatherData?.plus(body)

                            WeatherSettings.setSettings(newSettings, { s,i -> getSharedPreferences(s, i)})
                            presenter.showAllMyCities { s, i -> getSharedPreferences(s, i) }
                            val toast = Toast.makeText(applicationContext, "Pomyślnie dodałęm miasto", Toast.LENGTH_SHORT)
                            toast.show()

                        }
                        else{
                            val toast = Toast.makeText(applicationContext, "Nie ma takiego miasta nie można dodać miasta", Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    }

                })
            }
            else{
                val toast = Toast.makeText(applicationContext, "Brak połaczenia internetowego nie można dodać miasta", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }



    override fun showData(data: WeatherSettings) {
        adapter.clear()
        adapter.addAll(data.cities)
    }
}
