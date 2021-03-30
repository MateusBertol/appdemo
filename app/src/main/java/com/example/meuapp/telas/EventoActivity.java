package com.example.meuapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.meuapp.R;
import com.example.meuapp.controladores.EventoDao;
import com.example.meuapp.ferramentas.Tools;
import com.example.meuapp.modelos.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventoActivity extends AppCompatActivity {


    EditText txtNome, txtData;
    Button btnExcluir;
    Context context;
    Evento objEvento;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        codigo = "";
        context = EventoActivity.this;
        txtNome = findViewById(R.id.txtNome_evento);
        txtData = findViewById(R.id.txtData_evento);
        btnExcluir = findViewById(R.id.btnExcluir_evento);

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                Date data;

                try{
                    if(txtData.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtData.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                }

                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(context, android.R.style.Theme_Material_Dialog_Alert
                        , mDateSetListener, ano, mes, dia).show();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventoDao objDao = new EventoDao(context);
                boolean retorno = objDao.excluir(Integer.parseInt(codigo));
                if (retorno) {
                    Tools.exibirMensagem(context, "Evento excluído com Sucesso");
                    finish();
                } else {
                    Tools.exibirMensagem(context, "Erro ao Excluir Evento");
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            codigo = extras.getString("codigo");

            EventoDao objDao = new EventoDao(context);
            objEvento = objDao.buscarEvento(Integer.parseInt(codigo));

            txtNome.setText(objEvento.getNome());
            txtData.setText(objEvento.getData());
            btnExcluir.setVisibility(View.VISIBLE);
        } else {
            btnExcluir.setVisibility(View.INVISIBLE);
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String data = String.valueOf(String.format("%02d", dayOfMonth)) + "/"+ String.valueOf(String.format("%02d", monthOfYear + 1)) + "/" + String.valueOf(String.format("%04d", year));

            txtData.setText(data);
        }

    };

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_ok:

                Evento objeto = new Evento();
                objeto.setNome(txtNome.getText().toString());
                objeto.setData(txtData.getText().toString());

                EventoDao objDao = new EventoDao(context);

                if (codigo.equals("")) {
                    objDao.inserir(objeto);
                } else {
                    objeto.setCodigo(Integer.parseInt(codigo));
                    objDao.alterar(objeto);
                }

                finish();

            case R.id.action_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
