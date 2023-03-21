package rmr.java.mouredev22;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText etTexto1, etTexto2;
    private Button btStart;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.etTexto1 = findViewById(R.id.etTexto1);
        this.etTexto2 = findViewById(R.id.etTexto2);
        this.tvResult = findViewById(R.id.tvResult);
        this.btStart = findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (isAnagram(etTexto1.getText().toString(), etTexto2.getText().toString())) {
                    tvResult.setText("SON ANAGRAMAS");
                    tvResult.setTextColor(Color.GREEN);
                    tvResult.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setText("NO SON ANAGRAMAS");
                    tvResult.setTextColor(Color.RED);
                    tvResult.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isAnagram(String texto1, String texto2) {
        //llevamos a lowerCase porque no nos interesa que sea case sensitive
        texto1 = texto1.toLowerCase(Locale.getDefault()).replace(" ", "").replace(",", "");
        texto2 = texto2.toLowerCase(Locale.getDefault()).replace(" ", "").replace(",", "");
        //creamos un mapa para cada palabra
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        //introducimos cada caracter  en los mapas y cuantas veces aparece
        //getOrDefault es nuestro amigo, ya que sirve para enumerar las veces que sale la clave aunque todavía no esté todas las veces
        for (char letra : texto1.toCharArray()) {
            map1.put(letra, map1.getOrDefault(letra, 0) + 1);
        }
        for (char letra : texto2.toCharArray()) {
            map2.put(letra, map2.getOrDefault(letra, 0) + 1);
        }
        //ahora comparamos ambos mapas para ver si en efecto son un anagrama
        return map1.equals(map2);
    }
}