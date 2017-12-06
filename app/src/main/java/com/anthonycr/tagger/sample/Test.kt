package com.anthonycr.tagger.sample

import android.util.Log
import com.anthonycr.sample.TAG
import com.anthonycr.tagger.CaseStyle
import com.anthonycr.tagger.Tag

/**
 * Created by anthonycr on 12/4/17.
 */
@Tag(CaseStyle.ALL_CAPS)
object Test {

    fun log() {
        Log.d(TAG, "Hello World Test")
    }

}