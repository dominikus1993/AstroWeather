package dominikus1993.astroweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ListView
import model.AppData
import presenters.IMyLocalizationPresenter
import view.IAstroWeatherView

class MyLocalizations : AppCompatActivity(), IAstroWeatherView<AppData> {

    private lateinit var localizationList : ListView
    private lateinit var presenter : IMyLocalizationPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_localizations)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        localizationList = findViewById(R.id.localizations) as ListView

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showData(data: AppData) {
        throw UnsupportedOperationException()
    }

}
