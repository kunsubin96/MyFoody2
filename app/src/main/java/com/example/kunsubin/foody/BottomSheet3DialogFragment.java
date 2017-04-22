package com.example.kunsubin.foody;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

/**
 * Created by kunsubin on 3/26/2017.
 */
//show Dialog khi nhấn dấu cộng bên góc trái màn hình
public class BottomSheet3DialogFragment extends BottomSheetDialogFragment {
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.home_plus_menu, null);
        dialog.setContentView(contentView);
    }
}
