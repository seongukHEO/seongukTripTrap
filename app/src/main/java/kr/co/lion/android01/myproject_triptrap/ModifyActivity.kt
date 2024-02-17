package kr.co.lion.android01.myproject_triptrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)
        setToolBar()
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

                        }
                    }
                    true
                }
            }
        }
    }

    //입력을 받는다
    fun getData(){

    }

    //출력을 한다
    fun printData(){

    }

}









































