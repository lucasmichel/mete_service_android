package br.uni.mete_service.Controller.Cliente;

import java.io.Serializable;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.uni.mete_service.R;
import br.uni.mete_service.model.Cliente;

public class ControladorCLiente extends CadastroClienteActivity{
	 Cliente cliente;
	 
	public boolean validarCampo(EditText editText) {
        boolean retorno = true;
        
        if (editText.getText().toString().equals("")) {
            editText.setError("Campo Obrigatório");
            editText.requestFocus();
            retorno = false;   
        } 

        return retorno; 
    }
		public String validarCampos(Cliente clienteValidado){
		
		String verificacao;
		verificacao = "CamposValidos";
		if (clienteValidado.getNome().toString().equals("pedro")){
			verificacao = "ATENÇÃO: Nome invalido!";
		}
		if (clienteValidado.getEmail().toString().equals("pedronks")){
			verificacao = "ATENÇÃO: Email invalido!";
		}
		
		Log.i("PEDROOOOOOOO", "NOMEEpedri" + clienteValidado.getNome());
		Log.i("PEDROOOOOOOO", "NOMEEpedri" + clienteValidado.getEmail());
		
		return verificacao;
	}
	
	
}
