package com.betterise.maladiecorona.managers

import android.content.Context
import com.betterise.maladiecorona.R
import com.betterise.maladiecorona.model.*
import com.betterise.maladiecorona.model.out.Poll
import com.betterise.maladiecorona.model.out.PollAnswer
import com.betterise.maladiecorona.model.out.PollResult
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mjc on 24/06/20.
 */
class QuestionManager(
    private var questions: Array<String>,
    private var choices: Array<String>,
    private var types: Array<String>
) {

    companion object {
        const val IMC_LIMIT = 30
    }

    private var questionIndex = 0

    private val answers: MutableList<Answer> = arrayListOf()

    init {
        questions.forEach { _ -> answers.add(Answer(AnswerType.NUMERIC)) }

    }

    fun getCurrentQuestion() = questions[questionIndex]
    fun getCurrentIndex() = questionIndex
    fun getQuestionCount() = questions.size
    fun nextQuestion() = questionIndex++
    fun previousQuestion() = questionIndex--
    fun hasMoreQuestion() = questionIndex < questions.size - 1
    fun canGoBack() = questionIndex > 0
    fun getChoices() = choices[questionIndex].split("|")
    fun getAnswer() = answers[questionIndex]
    fun setAnswer(value: Int) {
        answers[questionIndex].value = value
    }

    fun setTextAnswer(text: String) {
        answers[questionIndex].text = text
    }

    fun getResults() = ResultManager().getResults(answers)


    fun getChoices(index: Int) = choices[index].split("|")

    fun getCurrentQuestionType() = getQuestionType(questionIndex)

    /***
     * Retrieving the question type
     */
    private fun getQuestionType(index: Int) = when (types[index]) {
        "ternary" -> QuestionType.TERNARY
        "digit" -> QuestionType.DIGIT
        "double" -> QuestionType.DOUBLE
       // "tel" -> QuestionType.TELEPHONE
        "digit_forced" -> QuestionType.DIGIT_FORCED
     //   "city" -> QuestionType.CITY
        else -> QuestionType.BINARY
    }


    /***
     * Create a poll (formated to be sent to server)
     * based on user's answers
     */
    fun createPoll(context: Context): Poll {

        var poll = Poll()

        // Poll's id
        poll.pollId = 1

        // Poll's date
        var dateFormat = context.getString(R.string.datetime_format)
        poll.date = SimpleDateFormat(dateFormat).format(Calendar.getInstance().time)

        // Poll's agent phone number
        poll.agent = AgentManager().getAgentNumber(context)


       // Poll's agent names
        poll.agentname = AgentManager().getAgentName(context)

        poll.firstname=PatientManager().getPatientFirstname(context)
        poll.lastname=PatientManager().getPatientLastname(context)
        poll.national_ID=PatientManager().getPatientNational_ID(context)
        poll.patientgender=PatientManager().getPatientGender(context)
        poll.patienttelephone=PatientManager().getPatientTelephone(context)
        poll.dob=PatientManager().getPatientDob(context)
        poll.occupation=PatientManager().getPatientOccupation(context)
        poll.nationality=PatientManager().getPatientNationality(context)
        poll.residence=PatientManager().getPatientResidence(context)
        poll.province=PatientManager().getPatientProvince(context)
        poll.district=PatientManager().getPatientDistrict(context)
        poll.sector=PatientManager().getPatientSector(context)
        poll.cell=PatientManager().getPatientCell(context)
        poll.village=PatientManager().getPatientVillage(context)
        poll.ascov_result=PatientManager().getPatientAscov_Result(context)

        // Poll's result
        var result = getResults()
        poll.result = PollResult(
            result.ordinal + 1, context.getString(
                when (result) {
                    ResultType.CASE1 -> R.string.result1
                    ResultType.CASE2 -> R.string.result2
                    ResultType.CASE3 -> R.string.result3
                    ResultType.CASE3bis -> R.string.result3bis
                    ResultType.CASE4 -> R.string.result4
                    ResultType.CASE5 -> R.string.result5
                }
            )
        )

        // Poll's answers
        answers.forEachIndexed() { index, answer ->

            var pollAnswer = PollAnswer()
            pollAnswer.questionId = index

            if (!answer.text.isNullOrEmpty())
                pollAnswer.answer = answer.text
            else {
                when (getQuestionType(index)) {
                    QuestionType.BINARY -> pollAnswer.answer = getChoices(index)[answer.value - 1]
                    QuestionType.DOUBLE -> pollAnswer.answer = getChoices(index)[answer.value - 1]
                    QuestionType.TERNARY -> pollAnswer.answer = getChoices(index)[answer.value - 1]
                    QuestionType.DIGIT_FORCED -> pollAnswer.answer = answer.value.toString()
                    QuestionType.DIGIT ->
                        if (answer.value == 1)
                            pollAnswer.answer = getChoices(index)[answer.value]
                        else
                            pollAnswer.answer = answer.value.toString()
                }
            }

            poll.answers.add(pollAnswer)
        }

        return poll
    }
}