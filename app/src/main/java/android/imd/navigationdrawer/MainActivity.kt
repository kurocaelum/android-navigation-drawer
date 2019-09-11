package android.imd.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PlanetAdapter.OnItemClickPlanetListener {

    private var planetSelected: Planet ?= null
    private var drawerToggle: ActionBarDrawerToggle ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        left_drawer.adapter = PlanetAdapter(this, this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // Tornar botão visível

        drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_close
        ){
            override fun onDrawerOpened(drawerView: View) {
                setTitle("Escolha um planeta")
                invalidateOptionsMenu() // "Avisar android" que houve mudança no menu
            }

            override fun onDrawerClosed(drawerView: View) {
                if(planetSelected != null){
                    setTitle(planetSelected?.name)
                } else {
                    setTitle(R.string.app_name)
                }

                invalidateOptionsMenu()
            }
        }

        drawer_layout.addDrawerListener(drawerToggle!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle?.onOptionsItemSelected(item)!!){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // "Avisar" que clicou no botão
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    private fun selectItem(planet: Planet?){
        if (planet != null){
            this.planetSelected = planet
            val fragment = PlanetFragment.newInstance(planet)   // Usando método ao invés de construtor
            val transaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content_frame, fragment)
            transaction.commit()
            setTitle(planet.name)
        }

        drawer_layout.closeDrawer(left_drawer!!)
    }

    override fun onClick(planet: Planet) {
        selectItem(planet)
    }
}
