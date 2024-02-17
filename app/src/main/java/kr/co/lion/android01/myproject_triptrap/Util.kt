package kr.co.lion.androidproject1test

import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.android01.myproject_triptrap.World
import kotlin.concurrent.thread

class Util {
    companion object{
        var worldList = mutableListOf<World>()
        // 포커스를 주고 키보드를 올려주는 메서드
        //키보드를 올린다
        fun showSoftInput(view: View, context: Context){
            // 포커스를 준다.
            view.requestFocus()

            thread {
                SystemClock.sleep(500)
                val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, 0)
            }
        }

        //키보드를 내려준다
        fun hideSoftInput(activity: AppCompatActivity){
            //현재 포커스를 가지고있는 뷰가 있다면 키보드를 내린다
            if(activity.window.currentFocus != null){
                val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0);
            }
        }
        //안내를 위한 다이얼로그를 보여준다
        fun showInfoDiaLog(context: Context, title:String, message:String, listener:(DialogInterface, Int) -> Unit ){
            var diaLogBuilder = MaterialAlertDialogBuilder(context)
            diaLogBuilder.setTitle(title)
            diaLogBuilder.setMessage(message)
            diaLogBuilder.setPositiveButton("확인", listener)
            diaLogBuilder.show()


        }
    }
}


enum class JapanVisit(var num:Int, var str:String){
    TOKYO(0, "도쿄"),
    OSAKA(1, "오사카"),
    FUKUOKA(2, "후쿠오카"),
    OTHER(3, "다른 곳에 방문"),
}

enum class CountryType(var num: Int, var str: String){
    JAPAN(0, "일본"),
    CHINA(1, "중국"),
    ENGLAND(2, "영국"),
    FRANCE(3, "프랑스"),
    SWISS(4, "스위스")
}

enum class Chiness(var num: Int, var str: String){
    NEVER(0, "전혀 못함"),
    LITTLE(1, "간단한 대화 가능"),
    ALOTS(2, "어느정도 대화 가능"),
    WELL(3, "유창함")
}

enum class ApelVisit(var num: Int, var str: String){
    MORNING(0, "오전"),
    EVENING(1, "오후")
}





































