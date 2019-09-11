package android.imd.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

// Não é recomendado criar construtor para fragment
class PlanetFragment: Fragment() {

    private var planet: Planet?= null

    companion object{
        fun newInstance(planet: Planet): PlanetFragment{
            var fragment = PlanetFragment()
            fragment.planet = planet

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_planet, container, false)
        var imageView: ImageView = view.findViewById(R.id.image)
        imageView.setImageResource(planet?.imgId!!)

        activity?.title = planet?.name

        return view
    }
}