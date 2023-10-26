package com.example.secondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

//ვიზუალურ კომპონენტში უნდა შეიყვანოთ რიცხევბი [1,1000].
// ღილაკზე დაჭერის შემდეგ გამოიძახეთ ფუნქცია რომელმაც უნდა დააბრუნოს ინპუთში შეყვანილი რიცხვის შესაბამისი ქართული ტექსტი.
// მაგალითად შემოვიდა 527 უნდა დაბრუნდეს ხუთასოცდაშვიდი. მიღებული შედეგი გამოაჩინეთ TextView-ში

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        button.setOnClickListener {
            val number = editText.text.toString().toIntOrNull()

            if (number != null && number in 1..1000) { //ვამოწმებ არის თუ არა ჩემს რეინჯში
                val finalResult: String =
                    convertToText(number) // ეს იქნება ძირითადი ფუნქცია, რომელიც გადაიყვანს ინტებს ტექსტში
                textView.text = finalResult
            } else {
                textView.text = "ეს რიცხვი არ არის 1-1000 რეინჯში"
            }
        }
    }

    fun convertToText(number: Int): String {
        val erteuli = listOf(
            "ნული", "ერთი", "ორი", "სამი", "ოთხი","ხუთი",
            "ექვსი", "შვიდი", "რვა", "ცხრა"
        )
        val teens = listOf(
            "", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი",
            "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თრვამეტი","ცხრამეტი"
        )
        val ateuli = listOf(
            "", "ოც", "ორმოც", "სამოც", "ოთხმოც"
        )
        val aseuli = listOf(
            "", "ას", "ორას", "სამას", "ოთხას",
            "ხუთას", "ექვსას", "შვიდას", "რვაას", "ცხრაას"
        )

        if (number == 0) {
            return erteuli[0]
        }

        val oneDigit = number % 10 //ერთნიშნასთვის
        val twoDigits = (number / 10) % 10 //ორნიშნასთვის
        val threeDigits = (number / 100) % 10 //სამნიშნასთვის

        var numberToWords: String = ""
        if (threeDigits > 0){ //სფეისს ვუმატებ ასეულის გამოსაყოფად
            numberToWords += "${aseuli[threeDigits]} "
        } else if(twoDigits==0 && oneDigit ==0){ // თუ უნაშთოდ იყოფა 100-ზე, ანუ ასეულია და ი-ს ვუმატებ
            numberToWords += "${aseuli[threeDigits]}ი"
        }
        if (twoDigits > 1 && oneDigit > 0){ //
            numberToWords += "${ateuli[twoDigits/2]}და" //ოცეულებით დავყავი
        } else if( twoDigits > 1 && (twoDigits % 2) == 0) { //აქ ვითვალისწინებ რო ათეული არ იწყებოდეს კენტი რიცხვით
            numberToWords += "${ateuli[twoDigits/2]}ი"
        }
        if (oneDigit > 0 && (twoDigits % 2) == 0){
            numberToWords += "${erteuli[oneDigit]}"
        } else if (oneDigit > 0 && (twoDigits % 2) == 1){
            numberToWords += "${teens[oneDigit]}"
        } else {
            numberToWords += "ათი"
        }

        return numberToWords
        }

    override fun onClick(p0: View?) {
        val number = editText.text.toString().toIntOrNull()
        val input = editText.text.toString()

        if (number != null && number in 1..1000) { //ვამოწმებ არის თუ არა ჩემს რეინჯში
            val finalResult: String =
                convertToText(number) // ეს იქნება ძირითადი ფუნქცია, რომელიც გადაიყვანს ინტებს ტექსტში
            textView.text = finalResult
        } else {
            textView.text = "ეს რიცხვი არ არის 1-1000 რეინჯში"
        }
    }
}
