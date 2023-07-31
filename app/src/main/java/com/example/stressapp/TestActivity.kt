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
    private var wellbeingTemp : Int = 0
    private var activenessTemp : Int = 0
    private var moodTemp : Int = 0
    private var buttonFlag : Int = 0
    private val negativeListL: List<String> = listOf("Самочувствие плохое", "Чувствую себя слабым", "Разбитый", "Обессиленный", "Напряженный", "Больной", "Усталый", "Изнуренный", "Легко утомляемый", "Вялый", "Пассивный", "Малоподвижный", "Медлительный", "Бездеятельный", "Безучастный", "Равнодушный", "Сонливый", "Желание отдохнуть", "Соображать трудно", "Рассеянный", "Грустный", "Плохое настроение", "Несчастный", "Мрачный", "Унылый", "Печальный", "Озабоченный", "Пессимистичный", "Разочарованный", "Недовольный", "")
    private val positiveListR: List<String> = listOf("Самочувствие хорошее", "Чувствую себя сильным", "Работоспособный", "Полный сил", "Расслабленный", "Здоровый", "Отдохнувший", "Свежий", "Выносливый", "Бодрый", "Активный", "Подвижный", "Быстрый", "Деятельный", "Увлеченный", "Взволнованный", "Возбужденный", "Желание работать", "Соображать легко", "Внимательный", "Веселый", "Хорошее настроение", "Счастливый", "Жизнерадостный", "Восторженный", "Радостный", "Спокойный", "Оптимистичный", "Полный надежд", "Довольный", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val rGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val nextBtn = findViewById<Button>(R.id.button3)
        val txtL = findViewById<TextView>(R.id.textL)
        val txtR = findViewById<TextView>(R.id.textR)
        //val txt1 = findViewById<TextView>(R.id.textView1)
        //val txt2 = findViewById<TextView>(R.id.textView2)
        //val txt3 = findViewById<TextView>(R.id.textView3)

        //val txt4 = findViewById<TextView>(R.id.textView5)
        //txt4.text = buttonFlag.toString()
        txtL.text = ""
        txtR.text = ""
        nextBtn.text = "начать"
        rGroup.visibility = View.INVISIBLE
        nextBtn.setOnClickListener{
            rGroup.visibility = View.VISIBLE
                txtL.text = negativeListL[buttonFlag]
                txtR.text = positiveListR[buttonFlag]
                nextBtn.text = "следующее состояние"

                when (buttonFlag) {
                    in 0..10 -> {
                        wellbeing += wellbeingTemp
                    }
                    in 11..20 -> {
                        activeness += activenessTemp
                    }
                    in 21..30 -> {
                        mood += moodTemp
                    }
                }
               // txt1.setText(wellbeing.toString())
                //txt2.setText(activeness.toString())
                //txt3.setText(mood.toString())
               // txt4.text = buttonFlag.toString()
            if (buttonFlag < 30)
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
                        in 0 ..10 -> {wellbeingTemp = 1}
                        in 11..20 -> {activenessTemp = 1}
                        in 21..30 -> {moodTemp = 1}}
                }
                R.id.radioButton2 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 2}
                        in 11..20 -> {activenessTemp = 2}
                        in 21..30 -> {moodTemp = 2}}
                }
                R.id.radioButton3 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 3}
                        in 11..20 -> {activenessTemp = 3}
                        in 21..30 -> {moodTemp = 3}}
                }
                R.id.radioButton4 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 4}
                        in 11..20 -> {activenessTemp = 4}
                        in 21..30 -> {moodTemp = 4}}
                }
                R.id.radioButton5 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 5}
                        in 11..20 -> {activenessTemp = 5}
                        in 21..30 -> {moodTemp = 5}}
                }
                R.id.radioButton6 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 6}
                        in 11..20 -> {activenessTemp = 6}
                        in 21..30 -> {moodTemp = 6}}
                }
                R.id.radioButton7 -> {
                    when (buttonFlag){
                        in 0 ..10 -> {wellbeingTemp = 7}
                        in 11..20 -> {activenessTemp = 7}
                        in 21..30 -> {moodTemp = 7}}
                }
            }

        }
    }

    private fun createExitDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.testPassed)
        builder.setMessage("Самочувствие: " + resultToString(wellbeing/10) + "\n" + "Активность: " + resultToString(activeness/10)+ "\n" + "Настроение: " + resultToString(mood/10))

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

}