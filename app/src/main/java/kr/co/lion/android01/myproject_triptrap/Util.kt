package kr.co.lion.androidproject1test

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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
                SystemClock.sleep(1000)
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
    }
}


enum class JapanVisit(num:Int, str:String){
    TOKYO(0, "도쿄"),
    OSAKA(1, "오사카"),
    FUKUOKA(2, "후쿠오카"),
    OTHER(3, "다른 곳에 방문"),
}

enum class CountryType(num: Int, str: String){
    JAPAN(0, "일본"),
    CHINA(1, "중국"),
    ENGLAND(2, "영국"),
    FRANCE(3, "프랑스"),
    SWISS(4, "스위스")
}

enum class Chiness(num: Int, str: String){
    NEVER(0, "전혀 못함"),
    LITTLE(1, "간단한 대화 가능"),
    ALOTS(2, "어느정도 대화 가능"),
    WELL(3, "유창함")
}

enum class ApelVisit(num: Int, str: String){
    MORNING(0, "오전"),
    EVENING(1, "오후")
}





































