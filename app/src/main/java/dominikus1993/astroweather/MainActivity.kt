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
import dependency.ViewPagerFactory
import model.Time
import presenters.IAstroWeatherMainActivityPresenter
import presenters.MainActivityPresenter
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
