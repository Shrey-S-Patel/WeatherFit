package weather.efk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    EditText username_input;
    EditText password_input;
    TextView reg_text;
    Button log_btn;

    FirebaseAuth my_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.uname_input);
        password_input = findViewById(R.id.password_input);
        log_btn = findViewById(R.id.log_button);
        reg_text = findViewById(R.id.reg_txt);

        my_auth = FirebaseAuth.getInstance();

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    //Log in
    private void loginUserAccount(){
        String email = username_input.getText().toString();
        String password = password_input.getText().toString();

        if(TextUtils.isEmpty(email)){
            username_input.setError("Email cannot be empty!");
            username_input.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            password_input.setError("Password cannot be empty!");
            password_input.requestFocus();
        }
        else{
            my_auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = my_auth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Login Success!",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = my_auth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this, "Logged In Now.", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}