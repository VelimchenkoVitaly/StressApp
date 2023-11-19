package com.example.stressapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class TestActivity : AppCompatActivity() {
    private var wellbeing : Int = 0
    private var activeness : Int = 0
    private var mood : Int = 0
    var visualMoodTemp : Int = 0
    private var wellbeingTemp : Int = 0
    private var activenessTemp : Int = 0
    private var moodTemp : Int = 0
    private var buttonFlag : Int = 0
    private val negativeListL: List<String> = listOf("","Самочувствие плохое", "Чувствую себя слабым", "Разбитый", "Обессиленный", "Напряженный", "Больной", "Усталый", "Изнуренный", "Легко утомляемый", "Вялый", "Пассивный", "Малоподвижный", "Медлительный", "Бездеятельный", "Безучастный", "Равнодушный", "Сонливый", "Желание отдохнуть", "Соображать трудно", "Рассеянный", "Грустный", "Плохое настроение", "Несчастный", "Мрачный", "Унылый", "Печальный", "Озабоченный", "Пессимистичный", "Разочарованный", "Недовольный", "")
    private val positiveListR: List<String> = listOf("","Самочувствие хорошее", "Чувствую себя сильным", "Работоспособный", "Полный сил", "Расслабленный", "Здоровый", "Отдохнувший", "Свежий", "Выносливый", "Бодрый", "Активный", "Подвижный", "Быстрый", "Деятельный", "Увлеченный", "Взволнованный", "Возбужденный", "Желание работать", "Соображать легко", "Внимательный", "Веселый", "Хорошее настроение", "Счастливый", "Жизнерадостный", "Восторженный", "Радостный", "Спокойный", "Оптимистичный", "Полный надежд", "Довольный", "")
    val db = Database(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val rGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val nextBtn = findViewById<Button>(R.id.button3)
        val txtL = findViewById<TextView>(R.id.textL)
        val txtR = findViewById<TextView>(R.id.textR)
        val rGroup2 = findViewById<RadioGroup>(R.id.radioGroup2)
        val image = findViewById<ImageView>(R.id.imageView)
        //val txt1 = findViewById<TextView>(R.id.textView1)
        //val txt2 = findViewById<TextView>(R.id.textView2)
        //val txt3 = findViewById<TextView>(R.id.textView3)

        //val txt4 = findViewById<TextView>(R.id.textView5)
        //txt4.text = buttonFlag.toString()
        rGroup2.visibility=View.INVISIBLE
        image.visibility = View.INVISIBLE
        txtL.text = ""
        txtR.text = ""
        nextBtn.text = "начать"
        rGroup.visibility = View.INVISIBLE
        nextBtn.setOnClickListener{
            rGroup.visibility = View.VISIBLE
                txtL.text = negativeListL[buttonFlag]
                txtR.text = positiveListR[buttonFlag]
                nextBtn.text = "Далее"

                when (buttonFlag) {
                    in 0..0 -> {
                        rGroup.visibility = View.INVISIBLE
                        rGroup2.visibility=View.VISIBLE
                        image.visibility = View.VISIBLE
                    }
                    in 1..11 -> {
                        image.visibility = View.INVISIBLE
                        rGroup2.visibility=View.INVISIBLE
                        wellbeing += wellbeingTemp
                    }
                    in 12..21 -> {
                        activeness += activenessTemp
                    }
                    in 22..31 -> {
                        mood += moodTemp
                    }
                }
               // txt1.setText(wellbeing.toString())
                //txt2.setText(activeness.toString())
                //txt3.setText(mood.toString())
               // txt4.text = buttonFlag.toString()
            if (buttonFlag < 31)
                buttonFlag++
            else {
                nextBtn.text = "закончить"
                createExitDialog()
            }
                rGroup.clearCheck()
        }

        rGroup.setOnCheckedChangeListener{group, checkedId ->
            when(checkedId)
            {
                R.id.radioButton1 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 1}
                        in 12..21 -> {activenessTemp = 1}
                        in 22..31 -> {moodTemp = 1}}
                }
                R.id.radioButton2 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 2}
                        in 12..21 -> {activenessTemp = 2}
                        in 22..31 -> {moodTemp = 2}}
                }
                R.id.radioButton3 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 3}
                        in 12..21 -> {activenessTemp = 3}
                        in 22..31 -> {moodTemp = 3}}
                }
                R.id.radioButton4 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 4}
                        in 12..21 -> {activenessTemp = 4}
                        in 22..31 -> {moodTemp = 4}}
                }
                R.id.radioButton5 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 5}
                        in 12..21 -> {activenessTemp = 5}
                        in 22..31 -> {moodTemp = 5}}
                }
                R.id.radioButton6 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 6}
                        in 12..21 -> {activenessTemp = 6}
                        in 22..31 -> {moodTemp = 6}}
                }
                R.id.radioButton7 -> {
                    when (buttonFlag){
                        in 1 ..11 -> {wellbeingTemp = 7}
                        in 12..21 -> {activenessTemp = 7}
                        in 22..31 -> {moodTemp = 7}}
                }
            }
        }
        rGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton11 -> {
                    visualMoodTemp = 2
                    mood += 4
                }
                R.id.radioButton12 -> {
                    visualMoodTemp = 4
                    mood += 3
                }
                R.id.radioButton13 -> {
                    visualMoodTemp = 6
                    mood += 5

                }
                R.id.radioButton14 -> {
                    visualMoodTemp = 8
                    moodTemp += -2
                }
                R.id.radioButton15 -> {
                    visualMoodTemp = 10
                    moodTemp = -3
                }
                R.id.radioButton16 -> {
                    visualMoodTemp = 12
                    moodTemp = 5}
                R.id.radioButton17 -> {
                    visualMoodTemp = 14
                    moodTemp = -4
                }
                R.id.radioButton18 -> {
                    visualMoodTemp = 16
                    moodTemp = 1
                }
                R.id.radioButton19 -> {
                    visualMoodTemp = 18
                    moodTemp = -3
                }
            }
        }
    }

    private fun createExitDialog()
    {
        val builder = AlertDialog.Builder(this)
        db.addData(wellbeing, activeness, mood )
        builder.setTitle(R.string.testPassed)
        builder.setTitle(R.string.testPassed)
        builder.setMessage("Самочувствие: " + resultToString(wellbeing/10) + "\n" + "Активность: " + resultToString(activeness/10)+ "\n" + "Настроение: " + resultToString(mood/10)+ "\n" + "-" + resultToString2(visualMoodTemp))

        builder.setNegativeButton(R.string.exit) { dialogInterface, i -> startActivity(Intent(this, MainActivity::class.java )) }
        builder.show()
    }

    private fun resultToString(result: Int): String
    {
        when (result){
            in 0..4 -> {return "ниже нормы"}
            in 4..5 -> {return "в норме"}
            else -> {return "благоприятное состояние"}
        }
    }
    private fun resultToString2(result: Int): String
    {
        if (result == 2){
            return "спокойное"
        }
        if (result == 4){
            return "сонное"
        }
        if (result == 6){
            return "восторженное"
        }
        if (result == 8){
            return "пресыщенное"
        }
        if (result == 10){
            return "скучное"
        }
        if (result == 12){
            return "радостное"
        }
        if (result == 14){
            return "неудовлетворенное"
        }
        if (result == 16){
            return "приятное"
        }
        if (result == 18){
            return "безразличное"
        }
        else {return "Состояние не было указано"}
    }

}