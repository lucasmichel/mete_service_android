package br.uni.mete_service.Controller.Cliente;

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
//        if (editText.getText().toString().trim() != null){
//        	editText.setError("Campo invalido");
//            editText.requestFocus();
//            retorno = false;
//        }
        return retorno; 
    }
//	public boolean validarTexto (Cliente cli){
//		boolean retorno = true;
//		if (cli.getNome().toString().equals("pedronks")){
//		Log.i("PEDRO", "NOMEEEEEEEEEEEEE");
//		retorno = false;
//		}
//		return retorno;
//	}


	}
