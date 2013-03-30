package br.uni.mete_service.Controller.Cliente;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import br.uni.mete_service.R;
import br.uni.mete_service.model.Cliente;

public class ControladorCLiente extends CadastroClienteActivity{
	
//	public boolean validarCampo(EditText editText) {
//        boolean retorno = true;
//        
//        if (editText.getText().toString().equals("")) {
//            editText.setError("Campo Obrigatório");
//            editText.requestFocus();
//            retorno = false;   
//        }         
//        return retorno; 
//    }

	
	public boolean valida (){
		boolean retorno = true;
		EditText nome = (EditText) findViewById(R.id.edtNomeCliente);
		if(nome.getText().toString().equals("pedro1")){
			nome.setError("campo errado");
			nome.requestFocus();
			retorno = false;	
		}
		
		return retorno;
		
	}

	}
