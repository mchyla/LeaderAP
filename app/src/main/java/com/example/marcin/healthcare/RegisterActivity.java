package com.example.marcin.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcin.healthcare.model.Leader;
import com.example.marcin.healthcare.repository.LeaderRepository;

import org.w3c.dom.Text;

import java.sql.SQLException;

/**
 * Created by mchyl on 27/04/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        TextView headTextView = (TextView)findViewById(R.id.headTextView);
        headTextView.setText(R.string.toRegister);

        Button buttonRegister = (Button) findViewById(R.id.addSchoolCoordinatorButton);

        final TextView nameTextView = (TextView) findViewById(R.id.textLeaderName);
        final TextView lastNameTextView = (TextView) findViewById(R.id.textLeaderLastName);
        final TextView emailTextView = (TextView) findViewById(R.id.textLeaderEmail);
        final TextView pinTextView = (TextView) findViewById(R.id.textLeaderPin);
        final TextView confirmPinTextView = (TextView) findViewById(R.id.textLeaderConfirmPin);
        final TextView phoneTextView = (TextView) findViewById(R.id.textLeaderTelephone);
        final TextView cityTextView = (TextView) findViewById(R.id.textLeaderCity);
        final TextView cityChangedTextView = (TextView) findViewById(R.id.textLeaderCityChanged) ;
        final TextView collageTextView = (TextView) findViewById(R.id.textLeaderCollege) ;

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameTextView.getText().toString();
                String lastName = lastNameTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String pin = pinTextView.getText().toString();
                String confirmPin = confirmPinTextView.getText().toString();
                String phone = phoneTextView.getText().toString();
                String city = cityTextView.getText().toString();
                String cityChanged = cityChangedTextView.getText().toString();
                String collage = collageTextView.getText().toString();

                if(name.equals("") || lastName.equals("") || email.equals("") || pin.equals("") || confirmPin.equals("") ||
                        phone.equals("") || city.equals("") || cityChanged.equals("") || collage.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.fillAllFields,
                            Toast.LENGTH_LONG).show();
                } else {
                    if(email.contains("@") && email.contains(".")){
                        if(pin.equals(confirmPin)){

                            Leader leader = new Leader();
                            leader.setName(name);
                            leader.setLastName(lastName);
                            leader.setEmail(email);
                            leader.setPin(Integer.valueOf(pin));
                            leader.setPhone(phone);
                            leader.setCity(city);
                            leader.setCollage(collage);

                            try {
                                LeaderRepository.addLeader(getApplicationContext(), leader);
                                Toast.makeText(getApplicationContext(), R.string.success,
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
                            } catch (SQLException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

               // startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        });


    }



}