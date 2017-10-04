package dario.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Historial extends AppCompatActivity {
    final TextView textviews[] = new TextView[9];
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        int x =0;
        textviews[0] = (TextView) findViewById(R.id.textView40);
        textviews[1] = (TextView) findViewById(R.id.textView41);
        textviews[2] = (TextView) findViewById(R.id.textView42);
        textviews[3] = (TextView) findViewById(R.id.textView43);
        textviews[4] = (TextView) findViewById(R.id.textView44);
        textviews[5] = (TextView) findViewById(R.id.textView45);
        textviews[6] = (TextView) findViewById(R.id.textView46);
        textviews[7] = (TextView) findViewById(R.id.textView47);
        textviews[8] = (TextView) findViewById(R.id.textView48);
        bundle = getIntent().getExtras();
        for (int i=0; i<4; i++){
            if(Integer.parseInt(bundle.getString("cants"+i)) != 0){
                Log.i("PRUEBSSAADDA",""+bundle.getString("tipo"+i));
                textviews[x++].setText(bundle.getString("tipo"+i));
                textviews[x++].setText(bundle.getString("cants"+i));
            }else{
                textviews[x++].setText("-");
                textviews[x++].setText(bundle.getString("0"));
            }
        }
        textviews[8].setText("Total: " + bundle.getString("totals"));
    }
}
