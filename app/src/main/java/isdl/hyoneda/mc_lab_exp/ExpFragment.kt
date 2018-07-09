package isdl.hyoneda.mc_lab_exp

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import java.io.File
import java.io.PrintWriter
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.support.v4.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.*


/*
* 環境変更画面
*
*/

// データセットの初期化
var state = States()
var setting = Setting()


class ExpFragment : Fragment() {

    // 操作ログのためのstateのコピーのバッファ
    var logBuff = mutableListOf<States>()
    var logTime = mutableListOf<String>()

    var startTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 環境のリセット
        state = States().copy()
        SendSignalToCeiling().execute()
        SendSignalToWall().execute()
        SendSignalToVMSitu().execute()
        SendSignalToVMVol().execute()

        // 書き込み可能かチェック
        val writable = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        if (writable) {
            val filePath = Environment.getExternalStorageDirectory().path + "/Android/data/isdl.hyoneda.mc_lab_exp/hyoneda_logs"
            val fileDir = File(filePath)
            Log.i("FILE", fileDir.exists().toString())
            if (!fileDir.exists()) {
                fileDir.mkdir()
            }
            val file = File(filePath + "/" + setting.logFile)
            // 書き込み先ファイルがあれば1行開けて追記。
            // なければ作る
            if (!file.exists()) {
                try {
                    file.createNewFile()
                    file.writeText("START FLAG\n")
                } catch (e: Exception) {
                    Log.e("file1", e.toString())
                }
            }else{
                file.appendText("NEW SETTION\n")
            }

        }else{
            Log.e("FILE", "NOT WRITABLE!")
        }

        startTime = System.currentTimeMillis()
    }

    override fun onStop() {
        super.onStop()

        val writable = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        if (writable) {
            val filePath = Environment.getExternalStorageDirectory().path + "/Android/data/isdl.hyoneda.mc_lab_exp/hyoneda_logs/" + setting.logFile
            val file = File(filePath)
            val lines = logTime.zip(logBuff)
            for ( line in lines) {
                file.appendText(line.toString() + "\n")
            }
        }else{
            Log.e("FILE", "NOT WRITABLE!")
        }
    }

    // UIに関してはここをいじる
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
                R.id.ce_color_wa -> {
                    Log.i("RADIO_OP", "天井色1")
                    state.c_color = 0
                    SendSignalToCeiling().execute()
                }
                R.id.ce_color_wh -> {
                    Log.i("RADIO_OP", "天井色2")
                    state.c_color = 1
                    SendSignalToCeiling().execute()
                }
                R.id.ce_color_co -> {
                    Log.i("RADIO_OP", "天井色3")
                    state.c_color = 2
                    SendSignalToCeiling().execute()
                }
            }
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
        }
        ceBriGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.c_radio_bri_high -> {
                    Log.i("RADIO_OP", "天井明るさ1")
                    state.c_bri = 2
                    SendSignalToCeiling().execute()
                }
                R.id.c_radio_bri_mid -> {
                    Log.i("RADIO_OP", "天井明るさ2")
                    state.c_bri = 1
                    SendSignalToCeiling().execute()
                }
                R.id.c_radio_bri_low -> {
                    Log.i("RADIO_OP", "天井明るさ3")
                    state.c_bri = 0
                    SendSignalToCeiling().execute()
                }
            }
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
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
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
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
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
        }
        vmSituGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.vm_button_bl -> {
                    Log.i("RADIO_OP", "擬似窓blank")
                    state.v_situ = "blank"
                    SendSignalToVMSitu().execute()
                }
                R.id.vm_button_ri -> {
                    Log.i("RADIO_OP", "擬似窓river")
                    state.v_situ = "river"
                    SendSignalToVMSitu().execute()
                }
                R.id.vm_button_be -> {
                    Log.i("RADIO_OP", "擬似窓beach")
                    state.v_situ = "beach"
                    SendSignalToVMSitu().execute()
                }
                R.id.vm_button_ki -> {
                    Log.i("RADIO_OP", "擬似窓kick")
                    state.v_situ = "kick"
                    SendSignalToVMSitu().execute()
                }
                else -> throw IllegalArgumentException("not supported")
            }
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
        }
        vmVolumeGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.vm_volume_0 -> {
                    Log.i("RADIO_OP", "音量0")
                    state.v_volume = 0
                    SendSignalToVMVol().execute()
                }
                R.id.vm_volume_1 -> {
                    Log.i("RADIO_OP", "音量1")
                    state.v_volume = 1
                    SendSignalToVMVol().execute()
                }
                R.id.vm_volume_2 -> {
                    Log.i("RADIO_OP", "音量2")
                    state.v_volume = 2
                    SendSignalToVMVol().execute()
                }
                R.id.vm_volume_3 -> {
                    Log.i("RADIO_OP", "音量3")
                    state.v_volume = 3
                    SendSignalToVMVol().execute()
                }
                else -> throw IllegalArgumentException("not supported")
            }
            logBuff.add(logBuff.lastIndex+1, state.copy())
            logTime.add(logTime.lastIndex+1, getTimeCode())
        }
        return v
    }

    // 経過時間から日付のフォーマットの文字列にして返す
    private fun getTimeCode() : String {
        val interval = System.currentTimeMillis() - startTime
        return  SimpleDateFormat("mm:ss:SS", Locale.JAPAN).format(interval)
    }

    inner class SendSignalToWall : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            jsonPut()
            return null
        }
    }
    inner class SendSignalToCeiling : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            tcpSendCeiling()
            return null
        }
    }
    inner class SendSignalToVMSitu : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            tcpSendVM()
            return null
        }
    }
    inner class SendSignalToVMVol : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            tcpSendVM2()
            return null
        }
    }
}

