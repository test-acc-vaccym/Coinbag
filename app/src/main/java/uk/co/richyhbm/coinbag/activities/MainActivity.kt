package uk.co.richyhbm.coinbag.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import uk.co.richyhbm.coinbag.BR
import uk.co.richyhbm.coinbag.R
import uk.co.richyhbm.coinbag.databinding.ActivityMainBinding
import uk.co.richyhbm.coinbag.utils.Icons
import uk.co.richyhbm.coinbag.view_model.ActivityMainViewModel

class MainActivity : AppCompatActivity() {
    val viewModel = ActivityMainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.vm, viewModel)
        binding.executePendingBindings()

        viewModel.fabIcon.set( Icons.getIcon(this, CommunityMaterial.Icon.cmd_plus, R.color.grey_50, 18))

        val refreshLayout = findViewById(R.id.main_swipe_refresh_layout) as SwipeRefreshLayout
        refreshLayout.setOnRefreshListener {
            updateAdapter()
            refreshLayout.isRefreshing = false
        }

        updateAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }

    fun onFabClick(v: View) {
        val i = Intent(this@MainActivity, AddAccountActivity::class.java)
        startActivity(i)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_menu -> {
                updateAdapter()
                return true
            }
            R.id.about_menu -> {
                val i = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun updateAdapter() {

    }
}
