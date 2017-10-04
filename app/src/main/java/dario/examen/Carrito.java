package dario.examen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Carrito extends AppCompatActivity {
    final TextView textviews[] = new TextView[13];
    public static final String PREFS_NAME = "MyPrefsFile";
    Intent data;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        settings.edit().clear().commit();
        int x = 0;
        final int precios[] = new int[4];
        int total = 0;
        bundle = getIntent().getExtras();
        precios[0] = 60;
        precios[1] = 55;
        precios[2] = 80;
        precios[3] = 75;
        textviews[0] = (TextView) findViewById(R.id.textView12);
        textviews[1] = (TextView) findViewById(R.id.textView13);
        textviews[2] = (TextView) findViewById(R.id.textView14);
        textviews[3] = (TextView) findViewById(R.id.textView15);
        textviews[4] = (TextView) findViewById(R.id.textView16);
        textviews[5] = (TextView) findViewById(R.id.textView17);
        textviews[6] = (TextView) findViewById(R.id.textView18);
        textviews[7] = (TextView) findViewById(R.id.textView19);
        textviews[8] = (TextView) findViewById(R.id.textView20);
        textviews[9] = (TextView) findViewById(R.id.textView21);
        textviews[10] = (TextView) findViewById(R.id.textView22);
        textviews[11] = (TextView) findViewById(R.id.textView23);
        textviews[12] = (TextView) findViewById(R.id.textView24);
        Button pagar = (Button) findViewById(R.id.button);
        data = new Intent();

        for (int i=0; i<4; i++){
            if(bundle.getInt("cants"+i) != 0){
                //Log.i("------second",String.valueOf(bundle.getString("tipo"+i)));
                textviews[x++].setText(bundle.getString("tipo"+i));
                textviews[x++].setText(String.valueOf(bundle.getInt("cants"+i)));
                data.putExtra("cants"+i, bundle.getInt("cants"+i));
                data.putExtra("tipo"+i, bundle.getString("tipo"+i));
                total += bundle.getInt("cants"+i)*precios[i];
                textviews[x++].setText("Subtotal: " + bundle.getInt("cants"+i)*precios[i] );
            }
            else{
                textviews[x++].setText(bundle.getString("tipo"+i));
                textviews[x++].setText("0");
                textviews[x++].setText("Subtotal: 0");
            }
        }
        data.putExtra("total", total);
        textviews[x].setText("Total: "+total);

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "PAGO REALIZADO";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Bateria baja Orden guardada", Toast.LENGTH_LONG).show();
        }
    }
}
