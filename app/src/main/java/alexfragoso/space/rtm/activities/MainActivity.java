package alexfragoso.space.rtm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import alexfragoso.space.rtm.R;

import static alexfragoso.space.rtm.R.drawable.invisibilityeye2;
import static alexfragoso.space.rtm.R.drawable.visibilityeye2;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Variaveis

    private Button botaoCadastrar;
    private Button botaoEsqueciSenha;
    private Button botaoEntrar;
    private Button botaoMudarVisibilidadeSenha;

    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoCadastrar = (Button) findViewById(R.id.buttonCadastreLogin);
        botaoEntrar = (Button) findViewById(R.id.buttonEntrarLogin);
        botaoEsqueciSenha = (Button) findViewById(R.id.buttonEsqueceuSenhaLogin);
        botaoMudarVisibilidadeSenha = (Button) findViewById(R.id.buttonSetVisibilityPasswordLogin);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenhaLogin);


        botaoCadastrar.setOnClickListener(this);
        botaoEntrar.setOnClickListener(this);
        botaoEsqueciSenha.setOnClickListener(this);
        botaoMudarVisibilidadeSenha.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Button botao = (Button) v;

                  if (botao == botaoCadastrar) {


                      Intent telaCadastroIntent = new Intent(this, CadastroActivity.class);
                      startActivity(telaCadastroIntent);

                }else {
                      if (botao == botaoMudarVisibilidadeSenha) {


                          //CÓDIGO PARA VARIAR CAMPO DE SENHA TORNADO-O INVISÍVEL OU VISÍVEL AO USUÁRIO

                          if (editTextSenha.getInputType() == TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){

                              //deixa visível
                              editTextSenha.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                              botaoMudarVisibilidadeSenha.setBackground(getDrawable(visibilityeye2));


                          }else{
                              //deixa invisível com asteriscos
                              editTextSenha.setInputType(TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                              botaoMudarVisibilidadeSenha.setBackground(getDrawable(invisibilityeye2));

                          }

                      }else{

                          if (botao == botaoEntrar) {

                              Intent telaIntroducaoIntent = new Intent(this, NavigationDrawerActivity.class);
                              startActivity(telaIntroducaoIntent);

                          }else{

                          }
                      }
                  }
        }


}
