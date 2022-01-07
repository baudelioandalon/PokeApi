package com.testexample.utils.extensions

import android.view.View
import com.testexample.utils.util.OnSingleClickListener

fun View.setOnSingleClickListener(doOnClick: ((View) -> Unit)) =
    setOnClickListener(OnSingleClickListener(doOnClick))