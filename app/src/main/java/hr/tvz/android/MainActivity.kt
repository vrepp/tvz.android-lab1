package hr.tvz.android

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
//import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.math.BigDecimal

class MainActivity : Activity() {
    // Properties
    private var currentResult = BigDecimal.ZERO
    private var currentNumber = ""
    private var currentOperation = Operation.ADDITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ButterKnife.bind(this);

        setupButtonListeners()
    }

    private fun clearAll() {
        currentResult = BigDecimal.ZERO
        currentNumber = ""
        currentOperation = Operation.ADDITION

        displayResult(resources.getString(R.string.btn_number_zero))
    }

    private fun displayResult(number: String) {
        txtResult.text = number
    }

    private fun setupButtonListeners() {
        // Button Clear
        btnActionClear.setOnClickListener {
            clearAll()
        }

        // Button Numbers
        btnNumberZero.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberOne.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberTwo.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberThree.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberFour.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberFive.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberSix.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberSeven.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberEight.setOnClickListener { numberButtonClicked(it as Button) }
        btnNumberNine.setOnClickListener { numberButtonClicked(it as Button) }

        // Button Operations
        btnOperationAddition.setOnClickListener { operationButtonClicked(it as Button) }
        btnOperationSubtraction.setOnClickListener { operationButtonClicked(it as Button) }
        btnOperationMultiplication.setOnClickListener { operationButtonClicked(it as Button) }
        btnOperationDivision.setOnClickListener { operationButtonClicked(it as Button) }

        // Button Operation Equals
        btnOperationEquals.setOnClickListener { operationEqualsClicked() }
    }

    private fun numberButtonClicked(button: Button) {
        val number = button.text

        currentNumber += number

        displayResult(currentNumber)
    }

    private fun operationButtonClicked(button: Button) {
        operationEqualsClicked()

        currentOperation = when(button.id) {
            R.id.btnOperationAddition -> Operation.ADDITION
            R.id.btnOperationSubtraction -> Operation.SUBTRACTION
            R.id.btnOperationMultiplication -> Operation.MULTIPLICATION
            R.id.btnOperationDivision -> Operation.DIVISION
            else -> Operation.ADDITION
        }
    }

    private fun operationEqualsClicked() {
        try {
            val number = currentNumber.toBigDecimal()

            currentResult = when(currentOperation) {
                Operation.ADDITION -> ( currentResult + number )
                Operation.SUBTRACTION -> ( currentResult - number )
                Operation.MULTIPLICATION -> ( currentResult * number )
                Operation.DIVISION -> ( currentResult / number )
            }

            currentNumber = ""
            displayResult(currentResult.toPlainString())
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
        }
    }
}

enum class Operation {
    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
}


