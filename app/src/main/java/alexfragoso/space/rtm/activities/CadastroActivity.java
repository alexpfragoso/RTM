package alexfragoso.space.rtm.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import alexfragoso.space.rtm.R;
import alexfragoso.space.rtm.banco.Conexao;

import static alexfragoso.space.rtm.R.drawable.invisibilityeye2;
import static alexfragoso.space.rtm.R.drawable.visibilityeye2;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{


    private Button botaoCriarConta;
    private Button botaoJaTenhoConta;
    private Button botaoBack;
    private Button botaoMudarVisibilidadeSenha;

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;

    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botaoCriarConta = (Button) findViewById(R.id.buttonCriarContaCadastro);
        botaoJaTenhoConta = (Button) findViewById(R.id.buttonJaTemContaCadastro);
        botaoBack = (Button) findViewById(R.id.buttonVoltar);
        botaoMudarVisibilidadeSenha = (Button) findViewById(R.id.buttonSetVisibilityPasswordCadastro);

        editTextNome = (EditText) findViewById(R.id.editTextNomeCadastro);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailCadastro);
        editTextSenha = (EditText) findViewById(R.id.editTextSenhaCadastro);


        botaoCriarConta.setOnClickListener(this);
        botaoJaTenhoConta.setOnClickListener(this);
        botaoBack.setOnClickListener(this);
        botaoMudarVisibilidadeSenha.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void criarUsuario(String email, String senha){

        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    alert("Usuário cadastrado com sucesso!");

                    Intent telaIntroducaoIntent = new Intent(CadastroActivity.this, NavigationDrawerActivity.class);
                    startActivity(telaIntroducaoIntent);
                    finish();

                }else{
                    alert("Erro de cadastro!");
                }
            }
        });
    }

    private void alert(String mensagem){
        Toast.makeText(CadastroActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        Button botao = (Button) v;

        if (botao == botaoBack || botao == botaoJaTenhoConta) {

            finish();

        }else{

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

                    if (botao == botaoCriarConta) {

                        String email = editTextEmail.getText().toString().trim();
                        String senha = editTextSenha.getText().toString().trim();

                        criarUsuario(email, senha);

                        //Intent telaCadastroIntent = new Intent(this, MeuPerfilActivity.class);
                        //startActivity(telaCadastroIntent);
                    }
                }

        }

    }
}
