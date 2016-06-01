package dominikus1993.astroweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import android.widget.ListView
import dependency.MyLocalizationPresenterDependencyResolver
import model.AppData
import presenters.IMyLocalizationPresenter
import view.IAstroWeatherView
import view.ILocalizationsView
import java.util.*

class MyLocalizations : AppCompatActivity(), ILocalizationsView{

    private lateinit var localizationList : ListView
    private lateinit var presenter : IMyLocalizationPresenter
    private lateinit var adapter:ArrayAdapter<String?>
    private lateinit var list:ArrayList<String?>

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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLocalizations(data: AppData): Unit{
        val localizationsNames = data.weatherData.localizationWeatherData?.map { it -> it.localization?.cityName }
        localizationsNames?.forEach { it ->
            list.add(it)
            adapter.notifyDataSetChanged()
        }
    }

}
