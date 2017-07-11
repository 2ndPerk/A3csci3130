package com.acme.a3csci3130;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetailViewActivity extends Activity {

    private EditText nameField, numField, adField;
    private RadioGroup typeField, provinceField;
    private MyApplicationData appState;
    Contact receivedPersonInfo;
    private String personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        numField = (EditText) findViewById(R.id.busiNum);
        adField = (EditText) findViewById(R.id.address);
        typeField = (RadioGroup) findViewById(R.id.primBusi);
        provinceField = (RadioGroup) findViewById(R.id.province);

        if(receivedPersonInfo != null){

            String num = Integer.toString(receivedPersonInfo.busiNum);

            nameField.setText(receivedPersonInfo.name);
            numField.setText(num);
            adField.setText(receivedPersonInfo.address);
            personID = receivedPersonInfo.uid;
            //typeField.check(findType(receivedPersonInfo));
            //provinceField.check(findProv(receivedPersonInfo));
        }
    }

    public int findType(Contact c) {
        String t = c.busiType;
        int r = getResources().getIdentifier(t,"RadioButton", "com.acme.a3csci3130");
        return r;
    }
    public int findProv(Contact c){
        return 0;
    };

    public void updateContact(View v){
        String name = nameField.getText().toString();
        int busiNum = Integer.parseInt(numField.getText().toString());
        String address = adField.getText().toString();
        String busiType = findRadioValue(typeField);
        String prov = findRadioValue(provinceField);
        receivedPersonInfo.updateData(personID, name, busiType, busiNum, address, prov);
        appState.firebaseReference.child(personID).setValue(receivedPersonInfo);

        finish();
    }

    public String findRadioValue(RadioGroup rg){
        int id= rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(id);
        int radioId = rg.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) rg.getChildAt(radioId);
        String value = (String) btn.getText();
        return value;
    }
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(personID).setValue(null);

        finish();
    }
}
