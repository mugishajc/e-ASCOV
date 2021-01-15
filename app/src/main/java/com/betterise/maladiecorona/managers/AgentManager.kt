package com.betterise.maladiecorona.managers

import android.content.Context

/**
 * Created by Alexandre on 01/07/20.
 */
class AgentManager() {

    companion object {
        const val PRIVATE_MODE = 0

        const val PREFS = "PREFS"
        const val PREF_AGENT_NUMBER = "PREF_AGENT_NUMBER"
    }

    /***
     * Saving the agent phone number
     */
    fun saveAgentNumber(context: Context, agentNumber : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_AGENT_NUMBER, agentNumber)
            .apply()
    }

    /***
     * Retrieving the current agent number
     */
    fun getAgentNumber(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(PREF_AGENT_NUMBER, "")


}