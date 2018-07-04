package isdl.hyoneda.mc_lab_exp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast


class ExpFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // UIに関してはここをいじる
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        /*
        val colorGroup = findViewById<RadioGroup>(R.id.color_group)
        colorGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.radio_1 -> Toast.makeText(this, "赤色が選ばれています", Toast.LENGTH_SHORT).show()
                R.id.radio_2 -> Toast.makeText(this, "黄色が選ばれています", Toast.LENGTH_SHORT).show()
                R.id.radio_3 -> Toast.makeText(this, "青色が選ばれています", Toast.LENGTH_SHORT).show()
                R.id.radio_4 -> Toast.makeText(this, "青色が選ばれています", Toast.LENGTH_SHORT).show()
                R.id.radio_5 -> Toast.makeText(this, "青色が選ばれています", Toast.LENGTH_SHORT).show()
                R.id.radio_6 -> Toast.makeText(this, "青色が選ばれています", Toast.LENGTH_SHORT).show()
                else -> throw IllegalArgumentException("not supported")
            }
        }*/

        return inflater.inflate(R.layout.fragment_exp, container, false)
    }
}
