package com.testexample.utils.extensions

import android.util.Log

fun String.log(key: String, error: Boolean = false){
    if(!error){
        Log.i(key,this)
    }else{
        Log.e(key,this)
    }
}