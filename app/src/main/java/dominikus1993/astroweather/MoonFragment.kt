package dominikus1993.astroweather

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dependency.MoonFragmentDependencyResolver
import model.MoonData
import utils.PreferencesUtils
import view.IAstroWeatherView

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
class MoonFragment : Fragment(), IAstroWeatherView<MoonData> {

    val presenter = MoonFragmentDependencyResolver.get(this)
    val updateAstroDataHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_moon_list, container, false)
        val settings = PreferencesUtils.getPreferences { s, i -> this.activity.getSharedPreferences(s,i) }
        updateAstroDataHandler.removeCallbacksAndMessages(null)
        updateAstroDataHandler.postDelayed(presenter.moonDataTimer(updateAstroDataHandler, settings), settings.interval.toLong())
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showData(data: MoonData) {
        throw UnsupportedOperationException()
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
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction()
    }

}

