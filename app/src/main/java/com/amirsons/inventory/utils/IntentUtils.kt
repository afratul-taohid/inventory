package com.amirsons.inventory.utils

/* Created by Imran Khan on 17-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.content.Context
import android.content.Intent
import android.os.Bundle

class IntentUtils {

    companion object {
        @JvmStatic val instance = IntentUtils()
    }

    fun onActivityIntentWithoutExtras(from: Context, to: Class<*>) {
        from.startActivity(Intent(from, to))
    }

    fun onActivityIntentWithExtras(from: Context, to: Class<*>, bundle: Bundle) {
        val intent = Intent(from, to)
        intent.putExtras(bundle)
        from.startActivity(intent)
    }
}
