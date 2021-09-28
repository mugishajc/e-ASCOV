package com.betterise.maladiecorona.managers

import android.content.Context

class PatientManager {

    companion object {
        const val PRIVATE_MODE = 0

        const val PREFS = "PREFS"
        const val PREF_PATIENT_FIRSTNAME = "PREF_PATIENT_FIRSTNAME"
        const val PREF_PATIENT_LASTNAME = "PREF_PATIENT_LASTNAME"
        const val PREF_PATIENT_NATIONAL_ID = "PREF_PATIENT_NATIONAL_ID"
        const val PREF_PATIENT_GENDER = "PREF_PATIENT_GENDER"
        const val PREF_PATIENT_TELEPHONE = "PREF_PATIENT_TELEPHONE"
        const val PREF_PATIENT_DOB = "PREF_PATIENT_DOB"
        const val PREF_PATIENT_OCCUPATION = "PREF_PATIENT_OCCUPATION"
        const val PREF_PATIENT_NATIONALITY = "PREF_PATIENT_NATIONALITY"
        const val PREF_PATIENT_RESIDENCE = "PREF_PATIENT_RESIDENCE"
        const val PREF_PATIENT_PROVINCE = "PREF_PATIENT_PROVINCE"
        const val PREF_PATIENT_DISTRICT = "PREF_PATIENT_DISTRICT"
        const val PREF_PATIENT_SECTOR = "PREF_PATIENT_SECTOR"
        const val PREF_PATIENT_CELL = "PREF_PATIENT_CELL"
        const val PREF_PATIENT_VILLAGE = "PREF_PATIENT_VILLAGE"

        const val PREF_PATIENT_ASCOV_RESULT = "PREF_PATIENT_ASCOV_RESULT"


    }

    fun savePatientFirstname(context: Context, patientFirstame : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientFirstame)
            .apply()
    }


    fun savePatientLastname(context: Context, patientLastname : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientLastname)
            .apply()
    }


    fun savePatientNational_ID(context: Context, patientNational_ID : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientNational_ID)
            .apply()
    }


    fun savePatientGender(context: Context, patientGender : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientGender)
            .apply()
    }


    fun savePatientTelephone(context: Context, patientTelephone : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientTelephone)
            .apply()
    }

    fun savePatientDob(context: Context, patientDob : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientDob)
            .apply()
    }


    fun savePatientOccupation(context: Context, patientOccupation : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientOccupation)
            .apply()
    }


    fun savePatientNationality(context: Context, patientNationality : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientNationality)
            .apply()
    }


    fun savePatientResidence(context: Context, patientResidence : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientResidence)
            .apply()
    }

    fun savePatientProvince(context: Context, patientProvince: String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientProvince)
            .apply()
    }


    fun savePatientDistrict(context: Context, patientDistrict : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientDistrict)
            .apply()
    }


    fun savePatientSector(context: Context, patientSector : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientSector)
            .apply()
    }


    fun savePatientCell(context: Context, patientCell : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientCell)
            .apply()
    }


    fun savePatientVillage(context: Context, patientVillage : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientVillage)
            .apply()
    }

    fun savePatientAscov_Result(context: Context, patientAscov_Result : String){
        var sharedPrefs = context.getSharedPreferences(PREFS, PRIVATE_MODE)
        sharedPrefs
            .edit()
            .putString(PREF_PATIENT_FIRSTNAME, patientAscov_Result)
            .apply()
    }


    /***
     * Retrieving the current agent number
     */
    fun getPatientFirstname(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_FIRSTNAME, "")


    fun getPatientLastname(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_LASTNAME, "")
    fun getPatientNational_ID(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_NATIONAL_ID, "")
    fun getPatientGender(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_GENDER, "")
    fun getPatientTelephone(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_TELEPHONE, "")
    fun getPatientDob(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_DOB, "")
    fun getPatientOccupation(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_OCCUPATION, "")
    fun getPatientNationality(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_NATIONALITY, "")
    fun getPatientResidence(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_RESIDENCE, "")
    fun getPatientProvince(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_PROVINCE, "")
    fun getPatientDistrict(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_DISTRICT, "")
    fun getPatientSector(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_SECTOR, "")
    fun getPatientCell(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_CELL, "")
    fun getPatientVillage(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_VILLAGE, "")
    fun getPatientAscov_Result(context: Context)= context.getSharedPreferences(PREFS, PRIVATE_MODE).getString(
        PREF_PATIENT_ASCOV_RESULT, "")



}