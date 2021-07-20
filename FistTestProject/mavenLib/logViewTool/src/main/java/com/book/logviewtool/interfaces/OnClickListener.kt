package com.book.logviewtool.interfaces

import android.view.View

interface OnClickListener {
    fun onClick(v: View?, position: Int)
    fun onLongClick(v: View?, position: Int)
}