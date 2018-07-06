package isdl.hyoneda.mc_lab_exp

/*
c_color
    0 : 3000K
    1 : 4500K
    2 : 5500K
c_bri
    0 : 300lx
    1 : 500lx
    2 : 700lx
w_color
    0 : 黄色
    1 : 白
    2 : オレンジ
    3 : スカイブルー
    4 : 濃い青
    5 : 黄緑
w_bri
    0 : なし
    1 :
    2 :
    3 :
v_situ
    blank
    beach
    river
    kick
v_volume
    0 : 0%
    1 : 30%
    2 : 60%
    3 : 100%

*/
data class States (
    var c_color : Int = 0,
    var c_bri : Int = 0,
    var w_color : Int = 0,
    var w_bri : Int = 0,
    var v_situ : String = "blank",
    var v_volume : Int = 0
)