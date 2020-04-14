package com.razan.MyCarTracks;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razan.MyCarTracks.Alarm.SensorDataManager;

/**
 * Custom Dialog show dialog method can be called from any class
 **/
public class SensorDialog {

    private Dialog mDialog;
    private TextView mTitleTxtV;
    private CheckBox mActivateCheckB;
    private EditText mEditText;
    private Button mSaveBtn;
    private Context mContext;


    public interface OnClicks {
        void onSaveClickListener(boolean isChecked, String editTextValue);
    }

    public SensorDialog(Context context) {
        mContext = context;
        mDialog = new Dialog(context);
    }

    //showDialog method links the xml file for the dialog and its content
    public void showDialog(int type, String title, String hint, final OnClicks onClicks) {

        mDialog.setContentView(R.layout.dialog_sensor);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTitleTxtV = mDialog.findViewById(R.id.titleTxtV);
        mActivateCheckB = mDialog.findViewById(R.id.activateCheckB);
        mEditText = mDialog.findViewById(R.id.editText);
        mSaveBtn = mDialog.findViewById(R.id.saveBtn);
        mActivateCheckB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    mEditText.setVisibility(View.VISIBLE);
                else
                    mEditText.setVisibility(View.GONE);
            }
        });

        if (type == ServicesActivity.FILTER) {
            mActivateCheckB.setChecked(SensorDataManager.getFilter().isActivated());
            if (SensorDataManager.getFilter().getEnteredValue() != 0)
                mEditText.setText(""+SensorDataManager.getFilter().getEnteredValue());
        } else if (type == ServicesActivity.SPEED_LIMIT) {
            mActivateCheckB.setChecked(SensorDataManager.getSpeedLimit().isActivated());
            if (SensorDataManager.getSpeedLimit().getEnteredValue() != 0)
                mEditText.setText(""+SensorDataManager.getSpeedLimit().getEnteredValue());

        }

        mTitleTxtV.setText(title);
        mEditText.setHint(hint);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivateCheckB.isChecked() && !mEditText.getText().toString().trim().isEmpty()){
                    onClicks.onSaveClickListener(true, mEditText.getText().toString().trim());
                    mDialog.cancel();
                }
                else if (mActivateCheckB.isChecked() && mEditText.getText().toString().trim().isEmpty())
                    Toast.makeText(mContext, "Please Enter Value", Toast.LENGTH_SHORT).show();
                else if (!mActivateCheckB.isChecked()){
                    onClicks.onSaveClickListener(false, "0");
                    mDialog.cancel();
                }


            }
        });

        mDialog.show();
    }
}
