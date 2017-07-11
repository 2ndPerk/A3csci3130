package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, numField, adField;
    private RadioGroup typeField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        numField = (EditText) findViewById(R.id.busiNum);
        adField = (EditText) findViewById(R.id.Address);
        typeField = (RadioGroup) findViewById(R.id.primBusi);
        provinceField = (RadioGroup) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID

        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        int busiNum = Integer.parseInt(numField.getText().toString());
        String address = adField.getText().toString();
        String busiType = findRadioValue(typeField);
        String prov = findRadioValue(provinceField);
        Contact business = new Contact(personID, name, busiType, busiNum, address, prov);
        appState.firebaseReference.child(personID).setValue(business);

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
}
