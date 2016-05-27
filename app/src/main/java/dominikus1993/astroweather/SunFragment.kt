package dominikus1993.astroweather

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dependency.SunFragmentDependencyResolver
import model.SunData
import presenters.ISunFragmentPresenter
import utils.PreferencesUtils
import utils.format
import view.IAstroWeatherView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SunFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SunFragment : Fragment(), IAstroWeatherView<SunData> {

    private val presenter:ISunFragmentPresenter = SunFragmentDependencyResolver.get(this)

    private val updateAstroDataHandler = Handler()

    private lateinit var sounRise: TextView
    private lateinit var sunSet: TextView
    private lateinit var tweelightMorning: TextView
    private lateinit var tweelightEvening: TextView
    private lateinit var azimuthRise: TextView
    private lateinit var azimuthSet: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_sun, container, false)
        sounRise = view!!.findViewById(R.id.sunrise) as TextView
        sunSet = view.findViewById(R.id.sunset) as TextView
        tweelightMorning = view.findViewById(R.id.twilightMorning) as TextView
        tweelightEvening = view.findViewById(R.id.twilightEvening) as TextView
        azimuthRise = view.findViewById(R.id.azimuthRise) as TextView
        azimuthSet = view.findViewById(R.id.azimuthSet) as TextView

        val settings = PreferencesUtils.getPreferences { s, i -> this.activity.getSharedPreferences(s,i) }

        updateAstroDataHandler.removeCallbacksAndMessages(null)
        updateAstroDataHandler.postDelayed(presenter.sunDataTimer(updateAstroDataHandler, settings), settings.interval.toLong())

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showData(data: SunData) {
        sounRise.text = data.sunInfo.sunrise.format()
        sunSet.text = data.sunInfo.sunset.format()
        tweelightMorning.text = data.sunInfo.twilightMorning.format()
        tweelightEvening.text = data.sunInfo.twilightEvening.format()
        azimuthRise.text = data.sunInfo.azimuthRise.format(2)
        azimuthSet.text = data.sunInfo.azimuthSet.format(2)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}// Required empty public constructor
