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

/*
* 環境変更画面
*
*/

class ExpFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // UIに関してはここをいじる
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println("expFlagment スタート")
        val v : View = inflater.inflate(R.layout.fragment_exp, container, false)

        // まとめてラジオグループをゲット
        val waColorGroup = v.findViewById<RadioGroup>(R.id.w_color_group)
        val waBriGroup = v.findViewById<RadioGroup>(R.id.w_bri_group)
        val vmSituGroup = v.findViewById<RadioGroup>(R.id.vm_situ_group)
        val vmVolumeGroup = v.findViewById<RadioGroup>(R.id.vm_volume_group)
        //val hoge : TableRadioGroup = v.findViewById<RadioGroup>(R.id.w_color_group)
        
        waColorGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.wa_color_ye -> Log.i("RADIO_OP", "1")
                R.id.wa_color_wh -> Log.i("RADIO_OP", "2")
                R.id.wa_color_or -> Log.i("RADIO_OP", "3")
                R.id.wa_color_sb -> Log.i("RADIO_OP", "4")
                R.id.wa_color_db -> Log.i("RADIO_OP", "5")
                R.id.wa_color_gr -> Log.i("RADIO_OP", "6")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        waBriGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.w_radio_bri_high -> Log.i("RADIO_OP", "11")
                R.id.w_radio_bri_mid -> Log.i("RADIO_OP", "12")
                R.id.w_radio_bri_low -> Log.i("RADIO_OP", "13")
                R.id.w_radio_bri_zero -> Log.i("RADIO_OP", "14")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        vmVolumeGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.vm_volume_0 -> Log.i("RADIO_OP", "音量0")
                R.id.vm_volume_1 -> Log.i("RADIO_OP", "音量1")
                R.id.vm_volume_2 -> Log.i("RADIO_OP", "音量2")
                R.id.vm_volume_3 -> Log.i("RADIO_OP", "音量3")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        return v
    }
}
