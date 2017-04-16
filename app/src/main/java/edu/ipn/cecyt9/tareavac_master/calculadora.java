package edu.ipn.cecyt9.tareavac_master;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class calculadora extends Activity {

    public String operacion="";
    EditText txtNumeroUno;
    EditText txtNumeroDos;
    Spinner SpOperaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        txtNumeroUno = (EditText)findViewById(R.id.txtNumero1);
        txtNumeroDos = (EditText)findViewById(R.id.txtNumero2);
        SpOperaciones = (Spinner) findViewById(R.id.SpOperacion);
        initListenerOperaciones();
    }


    public void btnOperacion(View v){
        String Numero1 = txtNumeroUno.getText().toString();
        String Numero2 = txtNumeroDos.getText().toString();
        if (Numero1.isEmpty() || Numero2.isEmpty()){
            Toast.makeText(getApplicationContext(), "Asegurate de escribir un numero en cada campo", Toast.LENGTH_LONG).show();
        }else{
            if (operacion.equals("")){
                Toast.makeText(getApplicationContext(), "Debe Elegir una operacion", Toast.LENGTH_SHORT).show();
            }else{
                new miWsCalculadora().execute(Numero1,Numero2,operacion);
            }
        }
    }

    public void initListenerOperaciones(){
        try {
            SpOperaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i!=0){
                        operacion = adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(adapterView.getContext(), operacion, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    operacion ="";
                }
            });
        }catch (Exception xDDD){
            Toast.makeText(getApplicationContext(), xDDD.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private class miWsCalculadora extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            try{
                String NAMESPACE = "http://miWS/";
                String URL = "http://192.168.0.3:8080/WebApplication1/calculadora";
                String METHOD_NAME = "operaciones";
                String SOAP_ACTION = "http://miWS/WebApplication1/operaciones";

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("Num1",strings[0]);
                request.addProperty("Num2",strings[1]);
                request.addProperty("Operacion",strings[2]);
                SoapSerializationEnvelope envelope =
                        new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
                result = resultado.toString();

            }catch(Exception xDDD) {
                Toast.makeText(getApplicationContext(), "Ups", Toast.LENGTH_LONG).show();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String resultados) {


            Toast.makeText(getApplicationContext(),"Resultado: "+ resultados ,Toast.LENGTH_LONG).show();
        }
    }
}
