package isdl.hyoneda.mc_lab_exp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import java.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner


class SettingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v : View = inflater.inflate(R.layout.fragment_setting, container, false)

        val listOfRooms = arrayOf("実験室1", "実験室2", "TEST")
        val roomid = v.findViewById<Spinner>(R.id.EXP_ROOM)
        // 前回の値を復元
        //Log.i("aa", "ロード"+setting.room.toString())
        //roomid.setSelection(setting.room)
        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, listOfRooms)
        roomid.adapter = arrayAdapter

        roomid.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                // action
                when (pos) {
                    0 -> {
                        Log.i("setting", "0")
                        setting.room = 0
                    }
                    1 -> {
                        Log.i("setting", "1")
                        setting.room = 1
                    }
                    2 -> {
                        Log.i("setting", "2")
                        setting.room = 2
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        val fileButton = v.findViewById<Button>(R.id.newLogfile)
        fileButton.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DATE)
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            setting.logFile = "$year"+"_"+ "$month" + "_" + "$day" + "_" + "$hour$minute.txt"
            Log.i("setting", setting.logFile)
        }

        return v
    }
}