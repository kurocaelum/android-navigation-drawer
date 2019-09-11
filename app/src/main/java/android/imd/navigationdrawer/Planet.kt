package android.imd.navigationdrawer

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray

class Planet(var name: String, var imgId: Int) {

    // Atributo estático (static do java)
    companion object{
        fun buildPlanets(context: Context): List<Planet>{
            var planets: ArrayList<Planet> = ArrayList()
            var res: Resources = context.resources

            var names = res.getStringArray(R.array.planets_names)
            var images: TypedArray = res.obtainTypedArray(R.array.planets_imgs)

            for (i in names.indices){
                var planet = Planet(names[i], images.getResourceId(i, -1))

                planets.add(planet)
            }

            return planets
        }
    }
}