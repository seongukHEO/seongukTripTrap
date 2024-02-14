package kr.co.lion.android01.myproject_triptrap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityInfoBinding
import kr.co.lion.androidproject1test.CountryType
import kr.co.lion.androidproject1test.Util

class InfoActivity : AppCompatActivity() {

    lateinit var activityInfoBinding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(activityInfoBinding.root)
        setToolBar()
        floatButton()
        setEvent()
    }
    //툴바 설정
    fun setToolBar(){
        //materialToolBar
        activityInfoBinding.apply {
            //materialToolBar
            materialToolbar2.apply {
                //타이틀
                title = "여행자 정보 출력"
            }
            //bottomAppBar
            bottomAppBar.apply {
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴
                inflateMenu(R.menu.info_menu)
                //메뉴를 눌렀을 떄
                setOnMenuItemClickListener {
                    //삭제
                    var infofo = intent.getIntExtra("position", 0)
                    Util.worldList.removeAt(infofo)
                    finish()

                    true
                }
            }
        }
    }
    //floatButton
    fun floatButton(){
        activityInfoBinding.apply {
            floatingActionButton.setOnClickListener {
                //수정
                var infofo = intent.getIntExtra("position", 0)
                var newIntent = Intent(this@InfoActivity, ModifyActivity::class.java)
                newIntent.putExtra("info", infofo)
                startActivity(newIntent)
            }
        }
    }
    //이벤트 설정
    fun setEvent(){
        activityInfoBinding.apply {
            resultInfoText.apply {
                var infofo = intent.getIntExtra("position", 0)
                var info = Util.worldList[infofo]
                //공통
                text = "이름 : ${info.name}\n"
                append("여행 기간 : ${info.visit}일\n")
                //여행지 별로 분기
                when(info.type){
                    CountryType.JAPAN -> {
                        var japan = info as Japan
                        append("방문한 일본 여행지 : ${japan.visitJapanCity.str}\n")
                        append("인상깊은 관광지 : ${japan.rememberPlace}\n")
                    }
                    CountryType.CHINA -> {
                        var china = info as China
                        append("방문한 도시 : ${china.visitChinaCity}\n")
                        append("중국어 수준 : ${china.chinaLanguage.str}\n")

                    }
                    CountryType.ENGLAND -> {
                        var england = info as England
                        append("기억에 남는 음식 : ${england.rememberEnglandFood}\n")
                        append("기억에 남는 쇼핑 목록 : ${england.rememberEnglandShop}\n")

                    }
                    CountryType.FRANCE -> {
                        var france = info as France
                        append("에펠탑 방문 시간 : ${france.visitFranceApel.str}\n")
                        append("기억에 남는 음식 : ${france.rememberFranceFood}\n")

                    }
                    CountryType.SWISS -> {
                        var swiss = info as Swiss
                        append("숙소의 위치 : ${swiss.houseSwissLocation}\n")
                        append("경비 : ${swiss.swissMoney}\n")

                    }
                }
            }
        }
    }
}

















































