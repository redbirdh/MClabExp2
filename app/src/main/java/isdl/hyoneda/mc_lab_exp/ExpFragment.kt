package isdl.hyoneda.mc_lab_exp

import android.content.DialogInterface
import android.os.Bundle
import android.provider.MediaStore
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
        val ceColorGroup : RadioGroup = v.findViewById(R.id.c_color_group)
        val ceBriGroup : RadioGroup = v.findViewById(R.id.c_bri_group)
        val waColorGroup : TableRadioGroup = v.findViewById(R.id.w_color_group)
        val waBriGroup : RadioGroup= v.findViewById(R.id.w_bri_group)
        val vmSituGroup : TableRadioGroup = v.findViewById(R.id.vm_situ_group)
        val vmVolumeGroup : RadioGroup = v.findViewById(R.id.vm_volume_group)

        ceColorGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.ce_color_wa -> Log.i("RADIO_OP", "天井色1")
                R.id.ce_color_wh -> Log.i("RADIO_OP", "天井色2")
                R.id.ce_color_co -> Log.i("RADIO_OP", "天井色3")
            }
        }
        ceBriGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.c_radio_bri_high -> Log.i("RADIO_OP", "天井明るさ1")
                R.id.c_radio_bri_mid -> Log.i("RADIO_OP", "天井明るさ2")
                R.id.c_radio_bri_low -> Log.i("RADIO_OP", "天井明るさ3")
            }
        }
        waColorGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.wa_color_ye -> Log.i("RADIO_OP", "壁面色1")
                R.id.wa_color_wh -> Log.i("RADIO_OP", "壁面色2")
                R.id.wa_color_or -> Log.i("RADIO_OP", "壁面色3")
                R.id.wa_color_sb -> Log.i("RADIO_OP", "壁面色4")
                R.id.wa_color_db -> Log.i("RADIO_OP", "壁面色5")
                R.id.wa_color_gr -> Log.i("RADIO_OP", "壁面色6")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        waBriGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.w_radio_bri_high -> Log.i("RADIO_OP", "壁面明るさ1")
                R.id.w_radio_bri_mid -> Log.i("RADIO_OP", "壁面明るさ2")
                R.id.w_radio_bri_low -> Log.i("RADIO_OP", "壁面明るさ3")
                R.id.w_radio_bri_zero -> Log.i("RADIO_OP", "壁面明るさ4")
                else -> throw IllegalArgumentException("not supported")
            }
        }
        vmSituGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.vm_button_bl -> Log.i("RADIO_OP", "擬似窓blank")
                R.id.vm_button_ri -> Log.i("RADIO_OP", "擬似窓river")
                R.id.vm_button_be -> Log.i("RADIO_OP", "擬似窓beach")
                R.id.vm_button_ki -> Log.i("RADIO_OP", "擬似窓kick")
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
