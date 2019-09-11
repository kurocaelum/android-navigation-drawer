package android.imd.navigationdrawer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PlanetAdapter(): BaseAdapter() {

    var planets: List<Planet> ?= null
    var listener: OnItemClickPlanetListener ?= null

    constructor(context: Context, listener: OnItemClickPlanetListener): this(){
        this.planets = Planet.buildPlanets(context)
        this.listener = listener
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View ?= p1
        var vh: ViewHolder

        val planet= planets?.get(p0)

        if(view == null){
            val li = LayoutInflater.from(p2?.context)
            view = li.inflate(R.layout.drawer_list_item, p2, false)

            vh = ViewHolder()
            vh.textItem = view.findViewById(R.id.textItem)
            vh.textItem?.setOnClickListener { listener?.onClick(planet!!) }

        } else {
            vh = view.tag as ViewHolder
        }

        vh.textItem?.text = planet?.name
        return view!!
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface OnItemClickPlanetListener{
        fun onClick(planet: Planet)
    }

    private class ViewHolder{
        var textItem: TextView?= null
    }

}