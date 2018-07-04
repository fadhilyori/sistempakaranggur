package dev.anggur.sistempakaranggur.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anggur.sistempakaranggur.R;
import dev.anggur.sistempakaranggur.api.ApiRequest;
import dev.anggur.sistempakaranggur.api.RetroClient;
import dev.anggur.sistempakaranggur.models.ResponseUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txv_to_register)
    TextView txvToRegister;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . . . .");
        progressDialog.setCancelable(false);
    }

    @OnClick({R.id.btn_login, R.id.txv_to_register})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                progressDialog.show();
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                ApiRequest request = RetroClient.getRequestService();
                Call<ResponseUser> login = request.login(username,password);
                login.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                        progressDialog.dismiss();
                        if (response.code() == 200) {
                            Intent intentToDiagnosa = new Intent(LoginActivity.this,MenuActivity.class);
                            intentToDiagnosa.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentToDiagnosa);
                        }
                        else Snackbar.make(view, "Login Gagal : Email atau Password Salah", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {
                        progressDialog.dismiss();
                        Snackbar.make(view, "Login Gagal : gagal menghubungkan ke server", Snackbar.LENGTH_LONG).show();
                        Log.d(getLocalClassName(), "onFailure: " + t.getMessage());
                    }
                });
                break;
            case R.id.txv_to_register:
                Intent intentToRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                intentToRegister.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentToRegister);
                break;
        }
    }
}
