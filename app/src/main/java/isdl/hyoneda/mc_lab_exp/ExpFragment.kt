package isdl.hyoneda.mc_lab_exp

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
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

class ExpFragment : Fragment() {
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
                R.id.wa_color_ye -> { //左上
                    Log.i("RADIO_OP", "壁面色1")
                    state.w_color = 0
                    SendSignalToWall().execute()
                }
                R.id.wa_color_wh -> { //中上
                    Log.i("RADIO_OP", "壁面色2")
                    state.w_color = 1
                    SendSignalToWall().execute()
                }
                R.id.wa_color_or -> { //右下
                    Log.i("RADIO_OP", "壁面色3")
                    state.w_color = 2
                    SendSignalToWall().execute()
                }
                R.id.wa_color_sb -> { //右上
                    Log.i("RADIO_OP", "壁面色4")
                    state.w_color = 3
                    SendSignalToWall().execute()
                }
                R.id.wa_color_db -> { //左下
                    Log.i("RADIO_OP", "壁面色5")
                    state.w_color = 4
                    SendSignalToWall().execute()
                }
                R.id.wa_color_gr -> { //中下
                    Log.i("RADIO_OP", "壁面色6")
                    state.w_color = 5
                    SendSignalToWall().execute()
                }
                else -> throw IllegalArgumentException("not supported")
            }
        }
        waBriGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.w_radio_bri_high -> {
                    Log.i("RADIO_OP", "壁面明るさ1")
                    state.w_bri = 3
                    SendSignalToWall().execute()
                }
                R.id.w_radio_bri_mid -> {
                    Log.i("RADIO_OP", "壁面明るさ2")
                    state.w_bri = 2
                    SendSignalToWall().execute()
                }
                R.id.w_radio_bri_low -> {
                    Log.i("RADIO_OP", "壁面明るさ3")
                    state.w_bri = 1
                    SendSignalToWall().execute()
                }
                R.id.w_radio_bri_zero -> {
                    Log.i("RADIO_OP", "壁面明るさ4")
                    state.w_bri = 0
                    SendSignalToWall().execute()
                }
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
    inner class SendSignalToWall : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            jsonPut()
            return null
        }
    }
}

