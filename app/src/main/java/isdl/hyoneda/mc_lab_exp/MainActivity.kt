package isdl.hyoneda.mc_lab_exp

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

// データセットの初期化
var state = States()
var setting = Setting()

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onResume() {
        super.onResume()
        val decor = this.window.decorView
        decor.systemUiVisibility = 2 or 4 or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        // permission周り
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if ( permission != PackageManager.PERMISSION_GRANTED ) {
            Log.e("PERMISSION", "Permission拒否1")
            // 権限をリクエスト
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }else{
            Log.e("PERMISSION", "Permission1あり！")
        }
        if ( permission2 != PackageManager.PERMISSION_GRANTED ) {
            Log.e("PERMISSION", "Permission拒否2")
            // 権限をリクエスト
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }else{
            Log.e("PERMISSION", "Permission2あり！")
        }

        Log.i("パッケージ名", packageName)

        // 初期画面をexp_fragmentにする
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TitleFragment()).commit()

        // 設定画面を作ります
        val pref : SharedPreferences = applicationContext.getSharedPreferences("config", Context.MODE_PRIVATE)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            0,1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("PERMISSION", "Permission拒否")
                } else {
                    Log.i("PERMISSION", "Permission許可")
                }
            }
        }
    }
/* 右上メニューの表示
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_top -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TitleFragment()).commit()
            }
            R.id.nav_exp -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, ExpFragment()).commit()
            }
            R.id.nav_que -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, QuestionFragment()).commit()
            }
            R.id.nav_setting -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, SettingFragment()).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
