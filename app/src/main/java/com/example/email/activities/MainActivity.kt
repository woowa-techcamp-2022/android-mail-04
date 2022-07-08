package com.example.email.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.email.App
import com.example.email.R
import com.example.email.databinding.ActivityMainBinding
import com.example.email.fragments.MailFragment
import com.example.email.fragments.SettingFragment
import com.example.email.viewmodels.MainViewModel
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    /**
     * MainActivity instance 생성,
     * SignInActivity 로부터 넘겨받은 닉네임과 이메일 정보를 싱글톤으로 저장 (재사용 대비)
     */
    companion object{
        lateinit var nickName: String
        lateinit var email : String
        fun getInstance(context : Context, nickName : String, email : String) : Intent {
            this.nickName = nickName
            this.email = email
            return Intent(context,MainActivity::class.java)
        }
    }

    /**
     * OnItemSelectedListener :
     * BottomNavigationView 와  NavigationRailView 의 메뉴 선택시 작동
     * 선택한 메뉴에 해당하는 fragment 로 화면 전환
     */
    inner class NavItemSelectedListener : NavigationBarView.OnItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val ft = supportFragmentManager.beginTransaction()
            val frameId = binding.fragmentFrame.id
            when(item.itemId){
                R.id.item_mail -> {
                    if (viewModel.navPosition.value!! != item.itemId) {
                        viewModel.navPosition.value = R.id.item_mail
                        ft.replace(frameId, mailFrag)
                    }
                    if(ft.isEmpty)
                        ft.replace(frameId, mailFrag)
                }
                R.id.item_setting -> {
                    if (viewModel.navPosition.value!! != item.itemId) {
                        viewModel.navPosition.value = R.id.item_setting
                        ft.replace(frameId, settingFrag)
                    }
                }
            }
            ft.commit()
            return true
        }
    }

    /**
     * 전환할 fragments
     */
    private val settingFrag  = SettingFragment()
    private val mailFrag  = MailFragment()

    /**
     * binding, 호출 시 초기화
     */
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    /**
     * viewModel, 호출 시 초기화
     */
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    /**
     * OnCreate() :
     * 초기화 작업 (화면 정보 확인, Toolbar, BottomNavigationView, NavigationRailView)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        configurationCheck()
        initToolBar()
        initNavi()
    }

    /**
     * 화면 정보 확인
     * 화면의 가로 pixel 값을 측정 density 로 나누어 dp 값으로 변환
     * 가로, 세로 모드를 확인
     * 가로 길이가 600dp 보다 크거나 가로 모드일 경우
     *  - BottomNavigationView 를 지우고 NavigationRailView 을 화면에 나타낸다.
     * 세로 모드일 경우
     *  - NavigationRailView 를 지우고 BottomNavigationView 을 화면에 나타낸다.
     */
    private fun configurationCheck(){
        val config = resources.configuration
        val density = resources.displayMetrics.density
        val width = resources.displayMetrics.widthPixels / density

        if (width > 600 ||config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.sideNav.visibility = View.VISIBLE
            binding.bottomNav.visibility = View.GONE
        }else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.sideNav.visibility = View.GONE
            binding.bottomNav.visibility = View.VISIBLE
        }
    }

    /**
     * BottomNavigationView 와  NavigationRailView 에 OnItemSelectedListener 를 추가
     * viewModel 의 navPosition 을 관찰해 값이 변하면 변한 값을
     * BottomNavigationView 와  NavigationRailView 의 선택 값으로 지정
     * (두 개의 navigationView 를 동기화)
     */
    private fun initNavi() {
        binding.bottomNav.setOnItemSelectedListener(NavItemSelectedListener())
        binding.sideNav.setOnItemSelectedListener(NavItemSelectedListener())
        viewModel.navPosition.observe(this){
            binding.bottomNav.selectedItemId = it
            binding.sideNav.selectedItemId = it
        }
    }

    /**
     * ToolBar 초기화,
     * toolbar 를 SupportActionBar 로 지정하고 버튼 초기화 (생성 및 아이콘 변경)
     * DrawerNavigationView 에 NavigationItemSelectedListener 추가
     * 선택한 메뉴에 해당하는 정수를 App 의 mailType 값으로 지정 (메일 목록 전환)
     * DrawerNavigationView 의 메뉴 선택시
     * viewModel 의  navPosition 값을 R.id.item_mail 으로 지정 (메일 화면으로 fragment 전환)
     */
    private fun initToolBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_30)

        binding.drawerNavView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item_primary -> App.mailType.value = 0
                R.id.item_social -> App.mailType.value = 1
                R.id.item_promotions -> App.mailType.value = 2
            }
            viewModel.navPosition.value = R.id.item_mail
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    /**
     * Toolbar 의 버튼 클릭 시 DrawerNavigationView open
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 뒤로가기 버튼을 누를 시
     * mailType, navPosition 확인, 둘다 0 이면 primary mail 화면이므로 종료
     * 그렇지 않다면 선택 값을 모두 0으로 설정 -> primary mail 화면으로 전환
     */
    override fun onBackPressed() {
        if (!(App.mailType.value!! == 0 && viewModel.navPosition.value == R.id.item_mail)){
            viewModel.navPosition.value = R.id.item_mail
            if (App.mailType.value!! != 0){
                App.mailType.value = 0
                binding.drawerNavView.setCheckedItem(R.id.item_primary)
            }
            return
        }
        super.onBackPressed()
    }
}