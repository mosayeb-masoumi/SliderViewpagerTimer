package com.example.viewpagertimer;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.example.viewpagertimer.animation.CubeOutDepthTransformation;

public class DialogFactory {

    private Context context;



    public interface DialogFactoryInteraction {

        void onAcceptButtonClicked(String... strings);

        void onDeniedButtonClicked(boolean cancel_dialog);
    }

    public DialogFactory(Context ctx) {

        this.context = ctx;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void createAnimateDialog(DialogFactoryInteraction listener, LinearLayout root) {

        View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_animate, (ViewGroup) root, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(customLayout);

        //create dialog and set background transparent
        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        }

        dialog.show();



    }
}
