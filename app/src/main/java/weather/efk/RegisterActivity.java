package weather.efk;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RegisterActivity extends AppCompatActivity {
    EditText name_input, username_input, email_input,password_input;
    Button button;
    TextView login_txt;/*
    FirebaseUser firebaseUser;
    FirebaseAuth my_auth;
    DatabaseReference mbase;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_input=findViewById(R.id.name_input);
        username_input=findViewById(R.id.username_input);
        email_input=findViewById(R.id.uname_input);
        password_input=findViewById(R.id.password_input);
        button=findViewById(R.id.log_button);
        login_txt=findViewById(R.id.reg_txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
/*
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = my_auth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
*/
    }
/*
    public void writeNewUser() {
        String email = email_input.getText().toString();
        String username = username_input.getText().toString();
        String fullname = name_input.getText().toString();

        User user = new User(username, email, fullname);

        mbase.child("users").child(username).setValue(user);
    }
*/
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean passwordLength(EditText text){
        CharSequence str = text.getText().toString();
        if (str.length() < 6){
            return false;
        }
        return true;
    }

    private void checkDataEntered() {
        if (isEmpty(name_input)) {
            Toast t = Toast.makeText(this, "You must enter full name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(username_input)) {
            username_input.setError("User name is required!");
        }
        if (isEmail(email_input) == false) {
            email_input.setError("Enter a valid email address!");
        }
        if (isEmpty(password_input)){
            password_input.setError("Password cannot be empty!");
        }
        if (passwordLength(password_input) == false){
            password_input.setError("Password cannot be less than 6 characters!");
        }
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = my_auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }*/
/*
        my_auth = FirebaseAuth.getInstance();
        firebaseUser = my_auth.getCurrentUser();
        mbase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser currentUser = my_auth.getCurrentUser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
                writeNewUser();
            }
        });

    }

    private void createUser(){

        String email = email_input.getText().toString();
        String password = password_input.getText().toString();

        my_auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
/*
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Registration successful!",Toast.LENGTH_LONG).show();

                            // if the user created intent to login activity
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText( getApplicationContext(),"Registration failed!" + " Please try again later", Toast.LENGTH_LONG).show();
                        }
*/
}