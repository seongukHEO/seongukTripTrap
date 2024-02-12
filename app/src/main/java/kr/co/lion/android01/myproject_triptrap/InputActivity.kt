package kr.co.lion.android01.myproject_triptrap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityInputBinding
import kr.co.lion.androidproject1test.Util

class InputActivity : AppCompatActivity() {
    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)
        setToolBar()
        setView()
        setEvent()
    }
    //툴바 설정
    fun setToolBar(){
        activityInputBinding.apply {
            materialToolbar4.apply {
                //타이틀
                title = "여행 정보 입력"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.input_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    var newIntent = Intent()
                    finish()

                    true
                }
            }
        }

    }
    //뷰 설정
    fun setView(){
        activityInputBinding.apply {
            toggleGroup.check(R.id.japanButton)
            japanContainer.isVisible = true
            chinaContainer.isVisible = false
            englandContainer.isVisible = false
            franceContainer.isVisible = false
            swissContainer.isVisible = false

            //일본의 방문 지역은 도쿄로 설정해둔다
            toggleGroup2.check(R.id.tokyoButton)

            //에펠 방문 시간은 오전으로 설정해둔다
            toggleGroup3.check(R.id.apelMorningButton)

            //포커스는 이름에 맞춘다
            Util.showSoftInput(nameEditText, this@InputActivity)
        }

    }
    //이벤트 설정
    fun setEvent(){
        activityInputBinding.apply {
            toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
                japanContainer.isVisible = false
                chinaContainer.isVisible = false
                englandContainer.isVisible = false
                franceContainer.isVisible = false
                swissContainer.isVisible = false

                when(toggleGroup.checkedButtonId){
                    R.id.japanButton -> {
                        japanContainer.isVisible = true
                        //일본 방문 지역 설정
                        toggleGroup2.check(R.id.tokyoButton)
                        favoritePlace.setText("")
                    }
                    R.id.chinaButton -> {
                        chinaContainer.isVisible = true
                        //초기화
                        visitCitytext.setText("")
                    }
                    R.id.englandButton -> {
                        englandContainer.isVisible = true
                        //초기화
                        rememberFood.setText("")
                        rememberShopping.setText("")
                    }
                    R.id.franceButton -> {
                        franceContainer.isVisible = true
                        //초기화
                        toggleGroup3.check(R.id.apelMorningButton)
                        rememberFood2.setText("")
                    }
                    R.id.swissButton -> {
                        swissContainer.isVisible = true
                        houseLocationText.setText("")
                        moneyText.setText("")
                    }
                }
                //이름 입력 칸에 포커스를 준다
                Util.showSoftInput(nameEditText, this@InputActivity)
            }
            sliderTrip.addOnChangeListener { slider, value, fromUser ->
                tripDate.text = "여행기간 : ${value.toInt()}일"
            }
        }

    }

    //유효성 검사
    fun checkOK(){

    }
}






























