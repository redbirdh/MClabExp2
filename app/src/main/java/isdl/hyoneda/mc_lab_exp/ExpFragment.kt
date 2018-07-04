package isdl.hyoneda.mc_lab_exp

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast


class ExpFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // UIに関してはここをいじる
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val colorGroup = view?.findViewById<RadioGroup>(R.id.w_color_group)
        colorGroup?.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.radio_1 -> Log.i("COLOR_BUTTON", "1")
                R.id.radio_2 -> Log.i("COLOR_BUTTON", "2")
                R.id.radio_3 -> Log.i("COLOR_BUTTON", "3")
                R.id.radio_4 -> Log.i("COLOR_BUTTON", "4")
                R.id.radio_5 -> Log.i("COLOR_BUTTON", "5")
                R.id.radio_6 -> Log.i("COLOR_BUTTON", "6")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        val briGroup = view?.findViewById<RadioGroup>(R.id.w_bri_group)
        briGroup?.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.w_radio_bri_high -> Log.i("COLOR_BUTTON", "11")
                R.id.w_radio_bri_mid -> Log.i("COLOR_BUTTON", "12")
                R.id.w_radio_bri_low -> Log.i("COLOR_BUTTON", "13")
                R.id.w_radio_bri_zero -> Log.i("COLOR_BUTTON", "14")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        return inflater.inflate(R.layout.fragment_exp, container, false)
    }
}
