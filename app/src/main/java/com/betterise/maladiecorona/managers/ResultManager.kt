package com.betterise.maladiecorona.managers

import com.betterise.maladiecorona.model.Answer
import com.betterise.maladiecorona.model.Question.Companion.AGE2
import com.betterise.maladiecorona.model.Question.Companion.ALIMENTATION
import com.betterise.maladiecorona.model.Question.Companion.BREATH
import com.betterise.maladiecorona.model.Question.Companion.CANCER
import com.betterise.maladiecorona.model.Question.Companion.COUGH
import com.betterise.maladiecorona.model.Question.Companion.DIABETES
import com.betterise.maladiecorona.model.Question.Companion.DIARRHEA
import com.betterise.maladiecorona.model.Question.Companion.HEIGHT
import com.betterise.maladiecorona.model.Question.Companion.PREGNANT
import com.betterise.maladiecorona.model.Question.Companion.RESPIRATORY_DISEASE
import com.betterise.maladiecorona.model.Question.Companion.SORE
import com.betterise.maladiecorona.model.Question.Companion.TASTE
import com.betterise.maladiecorona.model.Question.Companion.TEMPERATURE2
import com.betterise.maladiecorona.model.Question.Companion.TENSION
import com.betterise.maladiecorona.model.Question.Companion.WEIGHT
import com.betterise.maladiecorona.model.ResultType
import kotlin.math.pow

/**
 * Created by Alexandre on 25/06/20.
 */
class ResultManager {

    /***
     * Algorithm that defines a diagnoses depending on the answers
     */
    fun getResults(answers : MutableList<Answer>) : ResultType {

        var result = ResultType.CASE1

        if (answers[TEMPERATURE2].value == 2){

            if (answers[COUGH].value == 1 || answers[TASTE].value == 1 || answers[SORE].value == 1)
                result = ResultType.CASE2
            else if (answers[TENSION].value == 1 ||
                answers[TENSION].value == 3 ||
                answers[DIABETES].value == 1 ||
                answers[CANCER].value == 1 ||
                answers[RESPIRATORY_DISEASE].value == 1 ||
                answers[PREGNANT].value == 1 ||
                answers[AGE2].value > 69 ||
                getIMC(answers) >= QuestionManager.IMC_LIMIT){

                if (answers[BREATH].value == 1 || answers[ALIMENTATION].value == 1)
                    result = ResultType.CASE3bis
                else
                    result = ResultType.CASE3
            }
            else if (answers[AGE2].value <= 69)
                result = ResultType.CASE4
        }
        else if (answers[COUGH].value == 1){

            if (answers[TASTE].value == 1 || answers[SORE].value == 1) {

                if (answers[TENSION].value == 1 ||
                    answers[TENSION].value == 3 ||
                    answers[DIABETES].value == 1 ||
                    answers[CANCER].value == 1 ||
                    answers[RESPIRATORY_DISEASE].value == 1 ||
                    answers[PREGNANT].value == 1 ||
                    answers[AGE2].value > 69 ||
                    getIMC(answers) >= QuestionManager.IMC_LIMIT) {

                    if (answers[BREATH].value == 1 || answers[ALIMENTATION].value == 1)
                        result = ResultType.CASE3bis
                    else
                        result = ResultType.CASE3
                }
                else if (answers[AGE2].value <= 69)
                    result = ResultType.CASE4
            }
        }
        else if (answers[DIARRHEA].value == 1){

            if (answers[TENSION].value == 1 ||
                answers[TENSION].value == 3 ||
                answers[DIABETES].value == 1 ||
                answers[CANCER].value == 1 ||
                answers[RESPIRATORY_DISEASE].value == 1 ||
                answers[PREGNANT].value == 1 ||
                answers[AGE2].value > 69 ||
                getIMC(answers) >= QuestionManager.IMC_LIMIT) {

                if (answers[BREATH].value == 1 || answers[ALIMENTATION].value == 1)
                    result = ResultType.CASE3bis
                else
                    result = ResultType.CASE3
            }

            else if (answers[AGE2].value <= 69)
                result = ResultType.CASE4

        }
        else if (answers[BREATH].value == 1 || answers[ALIMENTATION].value == 1)
            result = ResultType.CASE5


        return result
    }




    /***
     * Calculating IMC
     */
    private fun getIMC(answers : MutableList<Answer>) =
        if (answers[WEIGHT].value <= 1 || answers[HEIGHT].value <= 1)
            100.0
        else
            answers[WEIGHT].value / (answers[HEIGHT].value.toDouble()/100).pow(2)



}