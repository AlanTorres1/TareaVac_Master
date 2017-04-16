package edu.ipn.cecyt9.tareavac_master;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Seleccion(View v){
        Intent envia;
        switch(v.getId())
        {
            case R.id.btnCalculadora:
                envia=new Intent(this,calculadora.class);
                finish();
                startActivity(envia);
                break;
            case R.id.btnDivisas:
                envia=new Intent(this,Divisas.class);
                finish();
                startActivity(envia);
                break;
            case R.id.btnLogin:
                envia=new Intent(this,Login.class);
                finish();
                startActivity(envia);
                break;
        }
    }
}
