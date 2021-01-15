package com.betterise.maladiecorona.model.out

/**
 * Created by Alexandre on 31/08/20.
 */
class Poll {

    var pollId : Int = 0
    var date : String = ""
    var agent : String = ""
    var answers : MutableList<PollAnswer> = arrayListOf()
    var result : PollResult = PollResult()

}