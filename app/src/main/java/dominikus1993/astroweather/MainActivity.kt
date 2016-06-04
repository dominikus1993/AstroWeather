package dominikus1993.astroweather

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import dependency.ViewPagerFactory
import model.Time
import model.WeatherData
import model.WeatherSettings
import presenters.IAstroWeatherMainActivityPresenter
import presenters.MainActivityPresenter
import retrofit2.Call
import retrofit2.Response
import services.IOpenWeatherService
import utils.AccuWeatherServiceBuilder
import utils.AstroCalculatorUtils
import utils.ConfigUtil
import utils.Constants
import view.IAstroWeatherView

class MainActivity : AppCompatActivity, IAstroWeatherView<Time> {

    val handler:Handler
    val presenter: IAstroWeatherMainActivityPresenter

    constructor(){
        handler = Handler()
        presenter = MainActivityPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        setupViewPager(this.resources.configuration.orientation)

        handler.postDelayed(presenter.getDateTime(handler), 1000)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        when(id){
            R.id.action_settings -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
            }
            R.id.localizations_settings -> {
                val intent = Intent(this, MyLocalizations::class.java)
                startActivity(intent)
            }
            R.id.refresh -> {
                if(AstroCalculatorUtils.isOnline(applicationContext))
                {
                    val settings = WeatherSettings.getFromSettings { s, i -> getSharedPreferences(s, i) }
                    val service = AccuWeatherServiceBuilder.getService(applicationContext) as IOpenWeatherService
                    val data = service.getWeatherForLocalization(settings.chosenCity as String, ConfigUtil.getByKey(applicationContext, Constants.OpenWeatherApiKey.value) as String)

                    data.enqueue(object : retrofit2.Callback<WeatherData> {
                        override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                            val res = response?.body()
                            if(res != null){
                                settings.weatherData = settings.weatherData?.map { it -> if(it.city?.name?.equals(res.city?.name) ?: false) res else it}
                                WeatherSettings.setSettings(settings, {s, i -> getSharedPreferences(s, i)})
                                val fragment = supportFragmentManager.findFragmentById(R.id.weatherFragment)
                                val ft = supportFragmentManager.beginTransaction();
                                ft.detach(fragment)
                                ft.attach(fragment)
                                ft.commit()
                            }else{
                                val toast = Toast.makeText(applicationContext, "Nie udało się zaktualizować danych pogodowych", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                        }

                        override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                            val toast = Toast.makeText(applicationContext, "Nie udało się zaktualizować danych pogodowych", Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    })
                }
                else{
                    val toast = Toast.makeText(applicationContext, "Brak połaczenia z internetem", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        else{

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        setupViewPager(if(newConfig?.orientation == null) Configuration.ORIENTATION_PORTRAIT else newConfig?.orientation  as Int)
    }

    fun setupViewPager(orientation : Int){
        val pager = findViewById(R.id.viewpager) as ViewPager?
        if(pager != null){
            val adapter = ViewPagerFactory.get(orientation, supportFragmentManager)
            adapter.addFragment(SunFragment(), "Sun")
            adapter.addFragment(MoonFragment(), "Moon")
            adapter.addFragment(WeatherFragment(), "Weather")
            pager.adapter = adapter
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.sun_fragment, SunFragment()).replace(R.id.moon_fragment, MoonFragment()).commit();
        }

    }

    override fun showData(data: Time) {
        val timer = findViewById(R.id.time) as TextView
        timer.text = data.dateTime
    }
}
