package com.example.stressapp

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.stressapp.databinding.ActivityMainBinding
import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class MainActivity : AppCompatActivity() {
    private lateinit var ourBarChart: BarChart
    //private lateinit var appBarConfiguration: AppBarConfiguration
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ourBarChart = findViewById(R.id.ourBarChart)
        retrieveSAN()
        //23
       // binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
    fun retrieveSAN() {

        val database: Database = Database(this)

        val user: List<UserModel> = database.retreive()

        val userWellbeingArray = Array<Int>(user.size) { 0 }
        val userActivityArray = Array<Int>(user.size) { 0 }
        val userMoodArray = Array<Int>(user.size) { 0 }


        var index = 0
        for (a in user) {
            userWellbeingArray[index] = a.userWellbeing
            userActivityArray[index] = a.userActive
            userMoodArray[index] = a.userMood
            index++
        }
        SANBarChart(userWellbeingArray, userActivityArray, userMoodArray)
    }

    private fun SANBarChart(list1: Array<Int>, list2: Array<Int>, list3: Array<Int>) {

        var  ourBarEntries: ArrayList<BarEntry> = ArrayList()
        var  ourBarEntries2: ArrayList<BarEntry> = ArrayList()
        var  ourBarEntries3: ArrayList<BarEntry> = ArrayList()

        var i = 0

        for (entry in list1) {
            var value = list1[i].toFloat()
            ourBarEntries.add(BarEntry(i.toFloat(), value))
            var value2 = list2[i].toFloat()
            ourBarEntries2.add(BarEntry(i.toFloat(), value2))
            var value3 = list3[i].toFloat()
            ourBarEntries3.add(BarEntry(i.toFloat(), value3))
            i++
        }
        val set1 = BarDataSet( ourBarEntries, "Самочувствие")
        set1.setColor(Color.rgb(0, 255, 0));
        val set2 = BarDataSet( ourBarEntries2, "Активность")
        set2.setColor(Color.rgb(139, 69, 19));
        val set3 = BarDataSet( ourBarEntries3, "Настроение")
        set3.setColor(Color.rgb(244, 164, 96));

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(set1)
        dataSets.add(set2)
        dataSets.add(set3)
        val data = BarData(dataSets)
        val barWidth = 0.46f
        val barSpace = 0.02f
        val groupSpace = 0.04f
        val groupCount = 7
        ourBarChart.setData(data)
        ourBarChart.getBarData().setBarWidth(barWidth)
        val xAxis: XAxis = ourBarChart.xAxis
        xAxis.setGranularity(1f);
        ourBarChart.axisLeft.setDrawGridLines(false)
        ourBarChart.setDrawBarShadow(false)
        ourBarChart.setDrawValueAboveBar(false)
        ourBarChart.setPinchZoom(false)
        ourBarChart.setBackgroundColor(Color.rgb(211, 252, 210));
        ourBarChart.description.isEnabled = false
        ourBarChart.animateY(3000)

        data.setBarWidth(0.15f)

        ourBarChart.getXAxis().setAxisMinimum(0f);
        ourBarChart.getXAxis().setAxisMaximum(0 +ourBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount)
        ourBarChart.groupBars(0F, groupSpace, barSpace)


        ourBarChart.invalidate();

    }

    private fun createWarningDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.everydayTest)
        builder.setMessage(R.string.everydayDescription)
        builder.setNegativeButton(R.string.goToTest) { dialogInterface, i -> startActivity(Intent(this, TestActivity::class.java )) }
        builder.show()
    }
    private fun createWarningDialogResults()
    {
        val builder = AlertDialog.Builder(this)


        builder.setTitle("Результаты тестирования")
        builder.setMessage(results())
        builder.show()
    }

    private fun results(): String
    {
        var wellbeing : Int = 0
        var activeness : Int = 0
        var mood : Int = 0
        val database: Database = Database(this)
        val user: List<UserModel> = database.retreive()

        var todayWellbeing : Int = user.elementAt(0).userWellbeing
        var todayActivness : Int = user.elementAt(0).userActive
        var todayMood : Int = user.elementAt(0).userMood

        var index = 0
        for (a in user) {
            wellbeing += a.userWellbeing
            activeness += a.userActive
            mood += a.userMood
            index++
        }




        return ("В среднем за последние семь дней" + "\n"+"Самочувствие: " + resultToString(wellbeing/70) + "\n" + "Активность: " + resultToString(activeness/70)+ "\n" + "Настроение: " + resultToString(mood/70)+"\n"+ "Сегодня: " + "\n"+"Самочувствие: "+ resultToString(todayWellbeing/10) + "\n" + "Активность: " + resultToString(todayActivness/10)+ "\n" + "Настроение: " + resultToString(todayMood/10) )
    }

    private fun resultToString(result: Int): String
    {
        when (result){
            in 0..4 -> {return "ниже нормы"}
            in 4..5 -> {return "в норме"}
            else -> {return "благоприятное состояние"}
        }
    }
    public fun testMe(view: View)
    {
        createWarningDialog()
    }

    public fun reportMe(view: View)
    {
        //createWarningDialog()
    }
    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}

