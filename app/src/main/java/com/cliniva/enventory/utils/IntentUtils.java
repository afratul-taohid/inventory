package com.cliniva.enventory.utils;

/* Created by Imran Khan on 17-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {
    private static IntentUtils instance = null;
    public static IntentUtils getInstance(){
        if (instance == null){
            instance = new IntentUtils();
        }
        return instance;
    }

    public void onActivityIntentWithoutExtras(Activity from, Class<?> to){
        from.startActivity(new Intent(from, to));
        from.finish();
    }

    public void onActivityIntentWithExtras(Activity from, Class<?> to, Bundle bundle){
        Intent intent = new Intent(from, to);
        intent.putExtras(bundle);
        from.startActivity(intent);
        from.finish();
    }
}
