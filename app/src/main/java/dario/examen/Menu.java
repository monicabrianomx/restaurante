package dario.examen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    static final int REQUEST_CODE = 1;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREFS_NAME2 = "MyPrefsFile2";
    final EditText editexts[] = new EditText[4];
    int total =0;
    final String valores[] = new String[4];
    final int cants[] = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button botones[] = new Button[2];
        final int cantidades[] = new int[4];
        final String tipo[] = new String[4];
        botones[0] = (Button) findViewById(R.id.carrito);
        botones[1] = (Button) findViewById(R.id.historial);
        editexts[0] = (EditText) findViewById(R.id.editText);
        editexts[1] = (EditText) findViewById(R.id.editText3);
        editexts[2] = (EditText) findViewById(R.id.editText4);
        editexts[3] = (EditText) findViewById(R.id.editText5);
        tipo[0] = "Hamburguesa de Pollo";
        tipo[1] = "Hamburguesa de Res";
        tipo[2] = "Hamburguesa de Camaron";
        tipo[3] = "Hamburguesa BBQ";

        recuperarDatos();

        for (int i=0;i<4;i++){
            final int finalI = i;
            editexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        cants[finalI] =Integer.parseInt( editexts[finalI].getText().toString() );
                        //Log.i("-----Cantidad: ", String.valueOf(cantidades[finalI]));
                    }catch (Exception e){
                        cants[finalI] = 0;
                    }

                }
            });
        }
        botones[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Carrito.class);
                for (int i=0; i<4; i++){
                    intent.putExtra("tipo"+i, tipo[i]);
                    intent.putExtra("cants"+i, cants[i]);
                }
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        botones[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("VALORRRRRRTOTAL", ""+total);
                if(total>0){
                    Intent intent = new Intent(Menu.this, Historial.class);
                    for (int i=0; i<4; i++){
                        Log.i("ANTESDE",""+valores[i]);
                        intent.putExtra("tipo"+i, ""+ valores[i]);
                        intent.putExtra("cants"+i, ""+ cants[i]);
                        intent.putExtra("totals",""+ total);
                    }
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            total = data.getExtras().getInt("total");

            for (int i=0; i<4;i++){
                valores[i] = data.getExtras().getString("tipo"+i);
                cants[i] = data.getExtras().getInt("cants"+i);
            }
        }
    }

    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            guardaDatos();
            Toast.makeText(context, "Bateria baja Orden guardada", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        guardaDatos();
    }

    public void recuperarDatos(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        for(int i = 0; i < 4; i++ ) {
            editexts[i].setText(settings.getString(Integer.toString(i), ""));
            try{
                cants[i] = Integer.parseInt(settings.getString(Integer.toString(i),"0"));
            }catch(Exception e){
                cants[i] = 0;
            }
        }
    }

    public void guardaDatos(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("0", String.valueOf(editexts[0].getText()));
        editor.putString("1", String.valueOf(editexts[1].getText()));
        editor.putString("2", String.valueOf(editexts[2].getText()));
        editor.putString("3", String.valueOf(editexts[3].getText()));
        editor.commit();
    }

}
