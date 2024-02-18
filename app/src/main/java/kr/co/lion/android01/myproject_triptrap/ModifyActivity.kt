package kr.co.lion.android01.myproject_triptrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityModifyBinding
import kr.co.lion.androidproject1test.ApelVisit
import kr.co.lion.androidproject1test.Chiness
import kr.co.lion.androidproject1test.CountryType
import kr.co.lion.androidproject1test.JapanVisit
import kr.co.lion.androidproject1test.Util

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)
        setToolBar()
        getData()
    }
    //툴바 설정
    fun setToolBar(){
        activityModifyBinding.apply {
            materialModifyToolbar5.apply {
                //타이틀
                title = "여행자 정보 수정"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭?
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴설정
                inflateMenu(R.menu.modify_menu)
                //클릭
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.well_menu_button -> {
                            printData()
                            finish()

                        }
                    }
                    true
                }
            }
            ModifyseekBar.addOnChangeListener { slider, value, fromUser ->
                ModifytripDate.text = "여행기간 : ${value.toInt()}"
            }
        }
    }

    //입력을 받는다
    fun getData(){
        activityModifyBinding.apply {
            var info = intent?.getIntExtra("info", 0)
            if (info != null){
                var traveler = Util.worldList[info]

                //공통
                ModifynameEditText.setText("${traveler.name}")
                ModifyseekBar.value = traveler.visit.toFloat()

                //타입별로 분기
                when(traveler.type){
                    CountryType.JAPAN -> {
                        ModifyjapanContainer.isVisible = true
                        var japan = traveler as Japan
                        when(japan.visitJapanCity){
                            JapanVisit.TOKYO -> {
                                ModifytoggleGroup2.check(R.id.ModifytokyoButton)
                            }
                            JapanVisit.OSAKA -> {
                                ModifytoggleGroup2.check(R.id.ModifyosakaButton)
                            }
                            JapanVisit.FUKUOKA -> {
                                ModifytoggleGroup2.check(R.id.ModifyfukuokaButton)
                            }
                            JapanVisit.OTHER -> {
                                ModifytoggleGroup2.check(R.id.ModifyotherButton)
                            }
                        }
                        ModifyfavoritePlace.setText("${japan.rememberPlace}")

                    }
                    CountryType.CHINA -> {
                        ModifychinaContainer.isVisible = true
                        var china = traveler as China
                        ModifyvisitCitytext.setText("${china.visitChinaCity}")
                        when(china.chinaLanguage){
                            Chiness.NEVER -> {
                                ModifyradioGroup.check(R.id.ModifyradioButton)
                            }
                            Chiness.LITTLE -> {
                                ModifyradioGroup.check(R.id.ModifyradioButton2)
                            }
                            Chiness.ALOTS -> {
                                ModifyradioGroup.check(R.id.ModifyradioButton3)
                            }
                            Chiness.WELL -> {
                                ModifyradioGroup.check(R.id.ModifyradioButton4)
                            }
                        }

                    }
                    CountryType.ENGLAND -> {
                        ModifyenglandContainer.isVisible = true
                        var england = traveler as England
                        ModifyrememberFood.setText("${england.rememberEnglandFood}")
                        ModifyrememberShopping.setText("${england.rememberEnglandShop}")

                    }
                    CountryType.FRANCE -> {
                        ModifyfranceContainer.isVisible = true
                        var france = traveler as France
                        when(france.visitFranceApel){
                            ApelVisit.MORNING -> {
                                ModifytoggleGroup3.check(R.id.ModifyapelMorningButton)
                            }
                            ApelVisit.EVENING -> {
                                ModifytoggleGroup3.check(R.id.ModifyapelEvening)
                            }
                        }
                        ModifyrememberFood2.setText("${france.rememberFranceFood}")

                    }
                    CountryType.SWISS -> {
                        ModifyswissContainer.isVisible = true
                        var swiss = traveler as Swiss
                        ModifyhouseLocationText.setText("${swiss.houseSwissLocation}")
                        ModifymoneyText.setText("${swiss.swissMoney}")

                    }
                }
            }
        }
    }

    //출력을 한다
    fun printData(){
        var info = intent.getIntExtra("info", 0)
        var traveler = Util.worldList[info]
        activityModifyBinding.apply {
            //공통
                traveler.name = ModifynameEditText.text.toString()
                traveler.visit = ModifyseekBar.value.toInt()

                //분기
                if (traveler is Japan){
                    traveler.visitJapanCity = when(ModifytoggleGroup2.checkedButtonId){
                        R.id.ModifytokyoButton -> JapanVisit.TOKYO
                        R.id.ModifyosakaButton -> JapanVisit.OSAKA
                        R.id.ModifyfukuokaButton -> JapanVisit.FUKUOKA
                        R.id.ModifyotherButton -> JapanVisit.OTHER
                        else -> JapanVisit.TOKYO
                    }
                    traveler.rememberPlace = ModifyfavoritePlace.text.toString()
                }

                else if(traveler is China){
                    traveler.visitChinaCity = ModifyvisitCitytext.text.toString()
                    traveler.chinaLanguage = when(ModifyradioGroup.checkedRadioButtonId){
                        R.id.ModifyradioButton -> Chiness.NEVER
                        R.id.ModifyradioButton2 -> Chiness.LITTLE
                        R.id.ModifyradioButton3 -> Chiness.ALOTS
                        R.id.ModifyradioButton4 -> Chiness.WELL
                        else -> Chiness.LITTLE
                    }
                }

                else if(traveler is England){
                    traveler.rememberEnglandFood = ModifyrememberFood.text.toString()
                    traveler.rememberEnglandShop = ModifyrememberShopping.text.toString()
                }

                else if (traveler is France){
                    traveler.visitFranceApel = when(ModifytoggleGroup3.checkedButtonId){
                        R.id.ModifyapelMorningButton -> ApelVisit.MORNING
                        R.id.ModifyapelEvening -> ApelVisit.EVENING
                        else -> ApelVisit.MORNING
                    }
                    traveler.rememberFranceFood = ModifyrememberFood2.text.toString()
                }

                else if (traveler is Swiss){
                    traveler.houseSwissLocation = ModifyhouseLocationText.text.toString()
                    traveler.swissMoney = ModifymoneyText.text.toString().toInt()
                }


        }
    }
}









































