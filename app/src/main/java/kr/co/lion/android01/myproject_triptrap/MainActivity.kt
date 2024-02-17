package kr.co.lion.android01.myproject_triptrap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityMainBinding
import kr.co.lion.android01.myproject_triptrap.databinding.MainBinding
import kr.co.lion.androidproject1test.CountryType
import kr.co.lion.androidproject1test.Util

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initView()
        setToolBar()
        floatbutton()
    }

    override fun onResume() {
        super.onResume()
        activityMainBinding.apply {
            recyclerview.adapter?.notifyDataSetChanged()
        }
    }
    //뷰설정
    fun initView(){
        activityMainBinding.apply {
            recyclerview.apply {
                //어댑터 객체
                adapter = CountryAdapter()
                //레이아웃 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                //데코
                var deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
    //floatButton
    fun floatbutton(){
        activityMainBinding.apply {
            floatButton.setOnClickListener {
                var newIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(newIntent)
            }
        }
    }
    //툴바 설정
    fun setToolBar(){
        activityMainBinding.apply {
            materialToolbar.apply {
                //타이틀
                title = "TripTrap"
                //메뉴 설정
                inflateMenu(R.menu.main_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    //필터,, 맨 마지막에 하자
                    true
                }
            }
        }
    }

    //어댑터 클래스
    inner class CountryAdapter : RecyclerView.Adapter<CountryAdapter.GatherCountry>(){

        //viewHolderClass
        inner class GatherCountry(mainBinding: MainBinding):RecyclerView.ViewHolder(mainBinding.root){
            var mainBinding:MainBinding
            init {
                this.mainBinding = mainBinding
                //가로 세로의 길이를 맞춘다
                this.mainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GatherCountry {
            //viewBinding
            var mainBinding = MainBinding.inflate(layoutInflater)
            //viewHolderClass
            var gatherCountry = GatherCountry(mainBinding)
            return gatherCountry
        }

        override fun getItemCount(): Int {
            return Util.worldList.size
        }

        override fun onBindViewHolder(holder: GatherCountry, position: Int) {
            var world1 = Util.worldList[position]
            holder.mainBinding.recyclerText.text = world1.name
            when(world1.type){
                CountryType.JAPAN -> {
                    holder.mainBinding.recyclerImage.setImageResource(R.drawable.japan_gh)
                }
                CountryType.CHINA -> {
                    holder.mainBinding.recyclerImage.setImageResource(R.drawable.china_gh)
                }
                CountryType.ENGLAND -> {
                    holder.mainBinding.recyclerImage.setImageResource(R.drawable.england_gh)
                }
                CountryType.FRANCE -> {
                    holder.mainBinding.recyclerImage.setImageResource(R.drawable.france_gh)
                }
                CountryType.SWISS -> {
                    holder.mainBinding.recyclerImage.setImageResource(R.drawable.swiss_gh)
                }
            }
            holder.mainBinding.root.setOnClickListener {
                var newIntent = Intent(this@MainActivity, InfoActivity::class.java)
                newIntent.putExtra("position", position)
                startActivity(newIntent)
            }
        }
    }
}

































