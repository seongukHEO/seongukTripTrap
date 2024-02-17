package kr.co.lion.android01.myproject_triptrap

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityInputBinding
import kr.co.lion.androidproject1test.ApelVisit
import kr.co.lion.androidproject1test.Chiness
import kr.co.lion.androidproject1test.CountryType
import kr.co.lion.androidproject1test.JapanVisit
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
                    checkOK()

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


    //입력을 받는다
    fun inputInfo(){
        activityInputBinding.apply {
            //타입별로 분기한다
            when(toggleGroup.checkedButtonId){
                R.id.japanButton -> {
                    var japan = Japan()
                    japan.type = CountryType.JAPAN
                    japan.name = nameEditText.text.toString()
                    japan.visit = sliderTrip.value.toInt()
                    japan.visitJapanCity = when(toggleGroup2.checkedButtonId){
                        R.id.tokyoButton -> JapanVisit.TOKYO
                        R.id.osakaButton -> JapanVisit.OSAKA
                        R.id.fukuokaButton -> JapanVisit.FUKUOKA
                        R.id.otherButton -> JapanVisit.OTHER
                        else -> JapanVisit.OTHER
                    }
                    japan.rememberPlace = favoritePlace.text.toString()
                        Util.worldList.add(japan)
                }
                R.id.chinaButton -> {
                    var china = China()
                    china.type = CountryType.CHINA
                    china.name = nameEditText.text.toString()
                    china.visit = sliderTrip.value.toInt()
                    china.visitChinaCity = visitCitytext.text.toString()
                    china.chinaLanguage = when(radioGroup.checkedRadioButtonId){
                        R.id.radioButton -> Chiness.NEVER
                        R.id.radioButton2 -> Chiness.LITTLE
                        R.id.radioButton3 -> Chiness.ALOTS
                        R.id.radioButton4 -> Chiness.WELL
                        else -> Chiness.LITTLE
                    }
                    Util.worldList.add(china)

                }
                R.id.englandButton -> {
                    var england = England()
                    england.type = CountryType.ENGLAND
                    england.name = nameEditText.text.toString()
                    england.visit = sliderTrip.value.toInt()
                    england.rememberEnglandFood = rememberFood.text.toString()
                    england.rememberEnglandShop = rememberShopping.text.toString()
                    Util.worldList.add(england)

                }
                R.id.franceButton -> {
                    var france = France()
                    france.type = CountryType.FRANCE
                    france.name = nameEditText.text.toString()
                    france.visit = sliderTrip.value.toInt()
                    france.visitFranceApel = when(toggleGroup3.checkedButtonId){
                        R.id.apelMorningButton -> ApelVisit.MORNING
                        R.id.apelEvening -> ApelVisit.EVENING
                        else -> ApelVisit.MORNING
                    }
                    france.rememberFranceFood = rememberFood2.text.toString()
                    Util.worldList.add(france)

                }
                R.id.swissButton -> {
                    var swiss = Swiss()
                    swiss.type = CountryType.SWISS
                    swiss.name = nameEditText.text.toString()
                    swiss.visit = sliderTrip.value.toInt()
                    swiss.houseSwissLocation = houseLocationText.text.toString()
                    swiss.swissMoney = moneyText.text.toString().toInt()
                    Util.worldList.add(swiss)

                }
            }
        }

    }
    // 유효성 검사
    fun checkOK(){
        activityInputBinding.apply {
            //공통
            var name = nameEditText.text.toString()
            if (name.trim().isEmpty()) {
                Util.showInfoDiaLog(
                    this@InputActivity,
                    "이름 입력 오류",
                    "이름을 입력해주세요"
                ) { dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(nameEditText, this@InputActivity)
                }
                return
            }

            //국가별
            when(toggleGroup.checkedButtonId){
                //일본
                R.id.japanButton -> {
                    var favorite = favoritePlace.text.toString()
                    if (favorite.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "인상깊은 관광지 입력 오류", "인상깊은 관광지를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(favoritePlace, this@InputActivity)
                        }
                        return
                    }
                }
                //중국
                R.id.chinaButton -> {
                    var visitplace = visitCitytext.text.toString()
                    if (visitplace.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "방문한 도시 입력 오류", "방문한 도시를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(visitCitytext, this@InputActivity)
                        }
                        return
                    }
                }
                //영국
                R.id.englandButton -> {
                    var rememberFood1 = rememberFood.text.toString()
                    if (rememberFood1.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "기억에 남는 음식 입력 오류", "기억에 남는 음식을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(rememberFood, this@InputActivity)
                        }
                        return
                    }
                    var rememberShop = rememberShopping.text.toString()
                    if (rememberShop.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "기억에 남는 쇼핑 목록 입력 오류", "기억에 남는 쇼핑 목록을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(rememberShopping, this@InputActivity)
                        }
                        return
                    }

                }
                //프랑스
                R.id.franceButton -> {
                    var remFood2 = rememberFood2.text.toString()
                    if (remFood2.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "기억에 남는 음식 입력 오류", "기억에 남는 음식을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(rememberFood2, this@InputActivity)
                        }
                        return
                    }
                }
                //스위스
                R.id.swissButton -> {
                    var house = houseLocationText.text.toString()
                    if (house.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "숙소의 위치 입력 오류", "숙소의 위치를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(houseLocationText, this@InputActivity)
                        }
                        return
                    }
                    var money = moneyText.text.toString()
                    if (money.trim().isEmpty()){
                        Util.showInfoDiaLog(this@InputActivity, "경비 입력 오류", "경비를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(moneyText, this@InputActivity)
                        }
                        return
                    }
                }
            }
            inputInfo()
            finish()

        }
    }
}






























