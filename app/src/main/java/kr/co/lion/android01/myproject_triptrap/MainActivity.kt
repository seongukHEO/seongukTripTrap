package kr.co.lion.android01.myproject_triptrap

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.myproject_triptrap.databinding.ActivityMainBinding
import kr.co.lion.android01.myproject_triptrap.databinding.MainBinding
import kr.co.lion.androidproject1test.CountryType
import kr.co.lion.androidproject1test.ShowsFilter
import kr.co.lion.androidproject1test.Util

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    var filterDiaLog = ShowsFilter.FILTER_TYPE_ALL

    //여기서 이용할 리스트
    var tripList = mutableListOf<World>()
    var tripListIndex = mutableListOf<Int>()

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
            showResult()
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
                    showFilterDiaLog()
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
            return tripList.size
        }

        override fun onBindViewHolder(holder: GatherCountry, position: Int) {
            var world1 = tripList[position]
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
                newIntent.putExtra("position", tripListIndex[position])
                startActivity(newIntent)
            }
        }
    }


    //필터 생성
    fun showFilterDiaLog(){
        var diaLogMine = MaterialAlertDialogBuilder(this@MainActivity)
        diaLogMine.setTitle("필터 선택")

        var tripArray = arrayOf("전체", "일본", "중국", "영국", "프랑스", "스위스")
        diaLogMine.setItems(tripArray){ dialogInterface: DialogInterface, i: Int ->
            //사용자가 선택한 DiaLog의 항목 순서 값으로 분기한다 즉 첫번째 버튼은 0번, 2번째 버튼은 1번
            filterDiaLog = when(i){
                0 -> ShowsFilter.FILTER_TYPE_ALL
                1 -> ShowsFilter.FILTER_TYPE_JAPAN
                2 -> ShowsFilter.FILTER_TYPE_CHINA
                3 -> ShowsFilter.FILTER_TYPE_ENGLAND
                4 -> ShowsFilter.FILTER_TYPE_FRANCE
                5 -> ShowsFilter.FILTER_TYPE_SWISS
                else -> ShowsFilter.FILTER_TYPE_ALL
            }
            //정보를 가져온다
            showResult()

            activityMainBinding.recyclerview.adapter?.notifyDataSetChanged()
        }
        diaLogMine.setPositiveButton("확인", null)
        diaLogMine.show()
    }

    //내부 내용 가져오기
    fun showResult(){
        //싹 비워준다
        tripList.clear()
        tripListIndex.clear()
        //타입별로 분기한다
        when(filterDiaLog){
            ShowsFilter.FILTER_TYPE_ALL -> {
                Util.worldList.forEachIndexed { index, world ->
                    tripList.add(world)
                    tripListIndex.add(index)
                }
            }
            ShowsFilter.FILTER_TYPE_JAPAN -> {
                Util.worldList.forEachIndexed { index, world ->
                    if (world.type == CountryType.JAPAN){
                        tripList.add(world)
                        tripListIndex.add(index)
                    }
                }
            }
            ShowsFilter.FILTER_TYPE_CHINA -> {
                Util.worldList.forEachIndexed { index, world ->
                    if (world.type == CountryType.CHINA){
                        tripList.add(world)
                        tripListIndex.add(index)
                    }
                }
            }
            ShowsFilter.FILTER_TYPE_ENGLAND -> {
                Util.worldList.forEachIndexed { index, world ->
                    if (world.type == CountryType.ENGLAND){
                        tripList.add(world)
                        tripListIndex.add(index)
                    }
                }
            }
            ShowsFilter.FILTER_TYPE_FRANCE -> {
                Util.worldList.forEachIndexed { index, world ->
                    if (world.type == CountryType.FRANCE){
                        tripList.add(world)
                        tripListIndex.add(index)
                    }
                }
            }
            ShowsFilter.FILTER_TYPE_SWISS -> {
                Util.worldList.forEachIndexed { index, world ->
                    if (world.type == CountryType.SWISS){
                        tripList.add(world)
                        tripListIndex.add(index)
                    }
                }
            }
        }


    }

}


































