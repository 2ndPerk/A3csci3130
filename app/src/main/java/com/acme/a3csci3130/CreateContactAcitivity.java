package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, numField, adField;
    private RadioGroup typeField, provinceField;
    private MyApplicationData appState;

    /**
     * Runs on creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.newContactButton);
        nameField = (EditText) findViewById(R.id.name);
        numField = (EditText) findViewById(R.id.busiNum);
        adField = (EditText) findViewById(R.id.address);
        typeField = (RadioGroup) findViewById(R.id.primBusi);
        provinceField = (RadioGroup) findViewById(R.id.province);
    }

    /**
     * Submits the new contact to Firebase when the button is clicked
     * Gets the values from the appropriate UI fields
     * @param v
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        if(!(findRadioValue(typeField).equals("null")||findRadioValue(provinceField).equals("null"))){
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

    }

    /**
     * Finds the value of the selected radio button in a given radio group
     * @param rg radio group to be checked
     * @return the value of the selected option
     */
    public String findRadioValue(RadioGroup rg){
        int id= rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(id);
        int radioId = rg.indexOfChild(radioButton);
        if(radioId == -1)return("null");
        RadioButton btn = (RadioButton) rg.getChildAt(radioId);
        String value = (String) btn.getText();
        return value;
    }
}
