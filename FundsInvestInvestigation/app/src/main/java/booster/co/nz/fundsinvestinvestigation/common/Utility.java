package booster.co.nz.fundsinvestinvestigation.common;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by ximeiliu on 13/02/17.
 */

public class Utility {

    static public void alertSimpleMessage(Context context, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();

    }
}
