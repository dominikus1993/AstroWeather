package dominikus1993.astroweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.TextView
import com.astrocalculator.AstroCalculator
import model.AppData
import utils.PreferencesUtils

class Settings : AppCompatActivity() {

    private lateinit var latitude: TextView
    private lateinit var longitude:TextView
    private lateinit var interval: TextView
    private lateinit var saveButton: Button
    private lateinit var settings:AppData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        settings = PreferencesUtils.getPreferences { s, i -> getSharedPreferences(s,i)}
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        longitude = findViewById(R.id.editText) as TextView
        latitude = findViewById(R.id.editText2) as TextView
        interval = findViewById(R.id.editText3) as TextView
        saveButton = findViewById(R.id.save) as Button

        setTextBoxes()

        saveButton.setOnClickListener { l ->
            val settings = AppData(AstroCalculator.Location(latitude.text.toString().toDouble(), longitude.text.toString().toDouble()), interval.text.toString().toInt())
            PreferencesUtils.setPreferences({x, y -> getSharedPreferences(x, y)}, settings )
        }
    }

    private fun setTextBoxes(): Unit {
        longitude.text = settings.location.longitude.toString()
        latitude.text = settings.location.latitude.toString()
        interval.text = settings.interval.toString()
    }

}
