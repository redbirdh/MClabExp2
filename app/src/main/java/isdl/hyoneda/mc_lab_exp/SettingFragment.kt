package isdl.hyoneda.mc_lab_exp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import java.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class SettingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_setting, container, false)

        val roomGroup : RadioGroup = v.findViewById(R.id.EXP_ROOM2)
        Log.i("rooooooooooom", setting.toString())
        when (setting.room) {
            0 -> roomGroup.check(R.id.room1)
            1 -> roomGroup.check(R.id.room2)
            2 -> roomGroup.check(R.id.room3)
        }

        roomGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.room1 -> {
                    Log.i("setting", "0")
                    setting.room = 0
                }
                R.id.room2 -> {
                    Log.i("setting", "1")
                    setting.room = 1
                }
                R.id.room3 -> {
                    Log.i("setting", "2")
                    setting.room = 2
                }
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
            setting.logFile = "$year"+"_"+"$month"+"_"+"$day"+"_"+"$hour"+"_"+"$minute.txt"
            Log.i("setting", setting.logFile)
        }

        return v
    }
}