package isdl.hyoneda.mc_lab_exp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class QuestionFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_question, container, false)

        val web : WebView = v.findViewById(R.id.web_questions)
        web.webViewClient = WebViewClient() // 標準ブラウザの表示を阻止
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.loadUrl("https://goo.gl/forms/lWbXEtOHsCNHhzkm2")

        return v
    }
}
