package isdl.hyoneda.mc_lab_exp

import android.content.AsyncTaskLoader
import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.net.*
import android.os.AsyncTask
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

import java.net.HttpURLConnection
import java.net.*

class SendSignals<T>(context : Context) : AsyncTaskLoader<Int>(context) {
    override fun loadInBackground(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

// TCP socket通信
fun send(host : String, port : Int, message : ByteArray) {
    try {
        val address = InetAddress.getByName(host)
        val socket = Socket(address, port)
        val fdin = socket.getInputStream()
        val fdout = socket.getOutputStream()
        fdout.write(message)
        fdout.flush()
        fdin.close()
        fdout.close()
    }
    catch(e : Exception){
        Log.e("TCPソケット通信", e.toString())
    }
}
// UDP socket通信
fun sendSignal(): Boolean{
    val senderPort = 0
    val host = "172.20.11.177"
    val port = 12321
    val data = "MANUAL_SIG-ALL\r\n2,1000,1000".toByteArray()
    var ret = false
    var socket: DatagramSocket? = null
    try {
        // socketオープン
        socket = DatagramSocket(senderPort)
        val address = InetAddress.getByName(host)
        // データグラムパケットの構築
        val packet = DatagramPacket(data, data.size, address, port)
        socket.send(packet)
        ret = true
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        socket?.close()
    }
    return ret
}
fun sendSignal(_ip: String, _port: Int, _signal: String): Boolean{
    val senderPort = 0

    val data = _signal.toByteArray()

    var ret = false
    var socket: DatagramSocket? = null
    try {
        // socketオープン
        socket = DatagramSocket(senderPort)
        val address = InetAddress.getByName(_ip)
        // データグラムパケットの構築
        val packet = DatagramPacket(data, data.size, address, _port)
        socket.send(packet)
        ret = true
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        socket?.close()
    }
    return ret
}
/*=========================================================================*/
// jsonをputする
//fun jsonPut(id: Int, flag: Boolean) {
fun jsonPut() {
    val ipAddr = "172.20.11.99"
    //val userName = "ak9-W3P7uTwKqCOHdB2HLaDvPB71FIoyIKXjW6Rl"
    val userName = "jy7Kk8cyAJ0XPmjj6vEzwEL3sV1OlJvjBU8gTT9i" // KICK 01
    //var url = "http://172.20.11.99/api/ak9-W3P7uTwKqCOHdB2HLaDvPB71FIoyIKXjW6Rl/lights/$id/state"
    var url: String
    val client: OkHttpClient = OkHttpClient.Builder().build()
    // json生成
    val json = JSONObject()
    when (state.w_color) {
        "none" -> {
            json.put("on", false)
        }
        "white" -> {
            json.put("on", true)
            json.put("hue", 37000)
            json.put("sat", 70)
        }
        "warm" -> {
            json.put("on", true)
            json.put("hue", 15000)
            json.put("sat", 130)
        }
        "cool" -> {
            json.put("on", true)
            json.put("hue", 42000)
            json.put("sat", 200)
        }
    }
    json.put("bri", state.w_luminance)
    // PUT
    val putBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
    var request : Request
    for (i in 1..7) {
        url = "http://$ipAddr/api/$userName/lights/$i/state"
        request = Request.Builder().url(url).put(putBody).build()
        client.newCall(request).execute()
    }
    // getResult
    //val result: String? = response.body()?.string()
    //response.close()
}

/*
class SendSignal : AsyncTask<Void, Int, Void>() {
    override fun onPreExecute() {
    }

    override fun doInBackground(vararg param: Void?): Void? {
        //Thread.sleep(800)
        if (state.on) {
            jsonPut(state.idOfLight, true)
        } else {
            jsonPut(state.idOfLight, false)
        }
        return null
    }

    override fun onPostExecute(result: Void?) {
    }
}
*/