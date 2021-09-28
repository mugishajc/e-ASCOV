package com.betterise.maladiecorona.model.out

/**
 * Created by Alexandre on 31/08/20.
 */
class Poll {

    var pollId : Int = 0
    var date : String = ""
    var agent : String = ""
    var agentname:String=""

    var firstname : String = ""
    var lastname : String = ""
    var national_ID : String = ""
    var patientgender : String = ""
    var patienttelephone: String = ""
    var dob : String = ""
    var occupation  : String = ""
    var residence : String = ""
    var nationality : String = ""
    var province: String = ""
    var district: String = ""
    var sector : String = ""
    var cell: String = ""
    var village: String = ""
    var ascov_result: String=""

    var answers : MutableList<PollAnswer> = arrayListOf()
    var result : PollResult = PollResult()

}