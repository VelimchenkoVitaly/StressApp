package com.example.stressapp

import android.content.res.Resources
import android.graphics.Color
import android.icu.text.AlphabeticIndex.ImmutableIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.core.view.marginTop
import android.view.Gravity
import android.widget.TextView
import android.widget.Button


class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setTest()


    }

    private fun setTest()
{
    var buttonFlag : Int = 1

    val listR: List<String> = listOf("Самочувствие плохое","Чувствую себя слабым", "Активный", "Подвижный", "Грустный", "Плохое настроение", "Разбитый", "Обессиленный", "Быстрый", "Деятельный", "Несчастный", "Мрачный", "Расслабленный", "Больной", "Увлеченный", "Взволнованный", "Унылый", "Печальный", "Усталый", "Изнуренный", "Возбужденный", "Желание работать", "Озабоченный", "Пессимистичный", "Легко утомляемый", "Вялый", "Соображать легко", "Внимательный", "Разочарованный", "Недовольный")
    val listL: List<String> = listOf("Самочувствие хорошее", "Чувствую себя сильным", "Пассивный", "Малоподвижный", "Веселый", "Хорошее настроение", "Работоспособный", "Полный сил", "Медлительный", "Бездеятельный", "Счастливый", "Жизнерадостный", "Напряженный", "Здоровый", "Безучастный", "Равнодушный", "Восторженный", "Радостный", "Отдохнувший", "Свежий", "Сонливый", "Желание отдохнуть", "Спокойный", "Оптимистичный", "Выносливый", "Бодрый", "Соображать трудно", "Рассеянный", "Полный надежд", "Довольный")
    val layout = findViewById<RelativeLayout>(R.id.layout)

    val paramsRG = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    paramsRG.addRule(RelativeLayout.CENTER_VERTICAL)


    val rGroup = RadioGroup(this)
    rGroup.gravity = Gravity.CENTER_HORIZONTAL
    rGroup.orientation = LinearLayout.HORIZONTAL
    rGroup.layoutParams = paramsRG

    val paramsTxtR = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )

    paramsTxtR.setMargins(0,1200,10,0)

    val paramsTxtL = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )

    paramsTxtL.setMargins(10,1200,0,0)
    val txtR = TextView(this)

    txtR.layoutParams = paramsTxtR
    txtR.gravity = Gravity.RIGHT
    txtR.setTextColor(Color.BLACK)
    txtR.textSize = 17F

    val txtL = TextView(this)
    txtR.text = listR[0]
    txtL.text = listL[0]
    txtL.layoutParams = paramsTxtL
    txtL.gravity = Gravity.LEFT
    txtL.setTextColor(Color.BLACK)
    txtL.textSize = 17F
    //var x = 0
    val rBut1 = RadioButton(this)
    rBut1.text = "3"
    val rBut2 = RadioButton(this)
    rBut2.text = "2"
    val rBut3 = RadioButton(this)
    rBut3.text = "1"
    val rBut4 = RadioButton(this)
    rBut4.text = "0"
    val rBut5 = RadioButton(this)
    rBut5.text = "1"
    val rBut6 = RadioButton(this)
    rBut6.text = "2"
    val rBut7 = RadioButton(this)
    rBut7.text = "3"

    val paramsBtn = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    paramsBtn.setMargins(0,1300,0,0)

    val OKbtn = Button(this)
    OKbtn.text = "следующее состояние"
    OKbtn.layoutParams = paramsBtn
    OKbtn.gravity = Gravity.CENTER_HORIZONTAL
    OKbtn.setBackgroundColor(Color.RED)

    OKbtn.setOnClickListener{
        if (buttonFlag <= 30){
        txtR.text = listR[buttonFlag]
        txtL.text = listL[buttonFlag]
        buttonFlag++}
        //else сюда дописать вывод результатов после их подсчета
    }
    rGroup.addView(rBut1)
    rGroup.addView(rBut2)
    rGroup.addView(rBut3)
    rGroup.addView(rBut4)
    rGroup.addView(rBut5)
    rGroup.addView(rBut6)
    rGroup.addView(rBut7)
    layout.addView(rGroup)
    layout.addView(OKbtn)
    layout.addView(txtR)
    layout.addView(txtL)
}
}