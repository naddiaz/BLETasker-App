package com.naddiaz.tfg.bletasker.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.naddiaz.tfg.bletasker.R;
import com.naddiaz.tfg.bletasker.utils.UserPrefecences;

/**
 * Created by nad on 12/04/15.
 */
public class LogoutDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.dialog_logout_message));
        builder.setMessage(getResources().getString(R.string.dialog_logout_message_description));

        builder.setPositiveButton(getResources().getString(R.string.dialog_close_session), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                new UserPrefecences(getActivity()).readPreferences().saveBeaconManagerState(true);
                getActivity().finish();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        return builder.create();
    }
}
