package isdl.hyoneda.mc_lab_exp

import android.content.AsyncTaskLoader
import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.net.*
import android.os.AsyncTask
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.sql.Time
import java.util.concurrent.TimeUnit


// TCP socket通信
fun tcpSendCeiling() {
    try {
        var host : String = ""
        var roomId : Int = 1
        when (setting.room){
            0 -> { // KICK
                host = "192.168.10.20"
                roomId = 1
            }
            1 -> { // KICK
                host = "192.168.10.20"
                roomId = 2
            }
            2 -> {
                host = "172.20.11.55"
            }
        }
        val port = 50001 // KICK
        var whiteSig = 0
        var warmSig = 0
        if (state.c_color == 0 && state.c_bri == 0) {
            whiteSig = 23
            warmSig = 144
        }else if (state.c_color == 0 && state.c_bri == 1) {
            whiteSig = 37
            warmSig = 235
        }else if (state.c_color == 0 && state.c_bri == 2) {
            whiteSig = 55
            warmSig = 356
        }else if (state.c_color == 1 && state.c_bri == 0) {
            whiteSig = 110
            warmSig = 53
        }else if (state.c_color == 1 && state.c_bri == 1) {
            whiteSig = 181
            warmSig = 92
        }else if (state.c_color == 1 && state.c_bri == 2) {
            whiteSig = 254
            warmSig = 124
        }else if (state.c_color == 2 && state.c_bri == 0) {
            whiteSig = 160
            warmSig = 0
        }else if (state.c_color == 2 && state.c_bri == 1) {
            whiteSig = 268
            warmSig = 0
        }else if (state.c_color == 2 && state.c_bri == 2) {
            whiteSig = 390
            warmSig = 0
        }
        val message = "MANUAL_SIG-ALL\r\n$roomId,$whiteSig,$warmSig"
        val address = InetAddress.getByName(host)
        val endPoint = InetSocketAddress(address, port)
        val socket = Socket(address, port)
        //socket.connect(endPoint, 2000)
        val fdin = socket.getInputStream()
        val fdout = socket.getOutputStream()
        Log.i("ceiling", message)
        fdout.write(message.toByteArray())
        fdout.flush()
        fdin.close()
        fdout.close()
    }
    catch(e : Exception){
        Log.e("TCPソケット通信-天井照明", e.toString())
    }
}
/*=========================================================================*/
// 擬似窓
// 実験室1 IP : 192.168.10.
// 実験室2 IP : 192.168.10.23
// PORT   : 50005
// SIGNAL : "BLANK" "MOVE1, 2, 3" "VOLUME0, 1, 2, 3"
// 返答を待つとダメ?
/*=========================================================================*/
// シチュエーション変更
fun tcpSendVM() {
    try {
        var host : String = "" //test
        when (setting.room){
            0 -> { // KICK
                host = "192.168.10.22" // 実験室1
            }
            1 -> { // KICK
                host = "192.168.10.23" // 実験室2
            }
            2 -> {
                host = "172.20.11.55"
            }
        }
        val port = 50005
        var message : String = ""
        val endPoint = InetSocketAddress(host, port)
        val socket = Socket(host, port)
        val fdin = socket.getInputStream()
        val fdout = socket.getOutputStream()

        when (state.v_situ) {
            "blank" -> message = "BLANK"
            "kick" -> message = "MOVE1"
            "river" -> message = "MOVE2"
            "beach" -> message = "MOVE3"
        }

        fdout.write(message.toByteArray())
        fdout.flush()
        fdin.close()
        fdout.close()
    }
    catch(e : Exception){
        Log.e("TCPソケット通信-擬似窓Situ", e.toString())
    }
}
// ボリューム変更
fun tcpSendVM2() {
    try {
        var host : String = ""
        when (setting.room){
            0 -> { // KICK
                host = "192.168.10.22" // 実験室1
            }
            1 -> { // KICK
                host = "192.168.10.23" // 実験室2
            }
            2 -> {
                host = "172.20.11.55"
            }
        }
        val port : Int = 50005
        var message : String = ""
        val address = InetAddress.getByName(host)
        val socket = Socket(address, port)
        val fdin = socket.getInputStream()
        val fdout = socket.getOutputStream()

        when (state.v_volume) {
            0 -> message = "VOLUME0"
            1 -> message = "VOLUME1"
            2 -> message = "VOLUME2"
            3 -> message = "VOLUME3"
        }

        fdout.write(message.toByteArray())
        fdout.flush()
        fdin.close()
        fdout.close()
    }
    catch(e : Exception){
        Log.e("TCPソケット通信-擬似窓Vol", e.toString())
    }
}
/*
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
*/

/*=========================================================================*/
// jsonをputする
//fun jsonPut(id: Int, flag: Boolean) {
fun jsonPut() {
    var ipAddr : String = ""
    var userName : String = ""
    when (setting.room){
        0 -> { // KICK
            ipAddr = "192.168.10.15" // KICK 01
            userName = "jy7Kk8cyAJ0XPmjj6vEzwEL3sV1OlJvjBU8gTT9i" // KICK 01
        }
        1 -> { // KICK
            ipAddr = "192.168.10.16" // KICK 02
            userName = "c4iMVp5HYHtrBwZgr4W4MBTrfjt33WTq9fYQRnVb" // KICK 02
        }
        2 -> {
            ipAddr = "172.20.11.101" // KC111
            userName = "MS4ah6torLCZ6vfaJwldQ15bhdbCQxdQv6S-XW12" // KC111
            //ipAddr = "172.20.11.99" // KC101(KC104)
            //userName = "XXJEdeefpEJrKMfFJgjX0owEFyspfhlclRknCdWB" // KC101(KC104)
        }
    }

    val url: String
    val clients = OkHttpClient.Builder()
    val client = clients.build()

    // json生成
    //val json = JSONObject()
    val json : String
    var bri : Int = 0
    var color : String = ""
    if (state.w_bri == 0) {
        json = "{\"on\":false}"
    } else {
        when (state.w_color) {
            0 -> {
                color = "\"colormode\":\"xy\", \"xy\":[0.4184, 0.4306]"
            }
            1 -> {
                color = "\"hue\":0, \"sat\":0"
            }
            2 -> {
                color = "\"colormode\":\"xy\", \"xy\":[0.4877, 0.4358]"
            }
            3 -> {
                color = "\"colormode\":\"xy\", \"xy\":[0.227, 0.2079]"
            }
            4 -> {
                color = "\"colormode\":\"xy\", \"xy\":[0.1807, 0.0662]"
            }
            5 -> {
                color = "\"colormode\":\"xy\", \"xy\":[0.3959, 0.493]"
            }
        }
        when (state.w_bri) {
            1 -> bri = 70
            2 -> bri = 160
            3 -> bri = 254
        }
        json = "{\"on\":true, $color, \"bri\":$bri}"
    }

    // PUT
    val putBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    val request : Request

    url = "http://$ipAddr/api/$userName/groups/1/action"
    request = Request.Builder().url(url).put(putBody).build()
    Log.i("wall", json)
    // KICKでは効かんときあるからなんとなく2回送っている...
    try {
        client.newCall(request).execute()
    }catch (e:Exception){
        Log.e("jsonPut", "JSONPUTに失敗")
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