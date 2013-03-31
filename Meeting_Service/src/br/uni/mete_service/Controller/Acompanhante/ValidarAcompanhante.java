package br.uni.mete_service.Controller.Acompanhante;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.widget.EditText;
import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.Cliente;

public class ValidarAcompanhante extends CadastroAcompanhanteActivity{
	 Acompanhante acompanhante;
	 
	public boolean validarCampo(EditText editText) {
        boolean retorno = true;
        
        if (editText.getText().toString().equals("")) {
            editText.setError("Campo Obrigatório");
            editText.requestFocus();
            retorno = false;   
        } 

        return retorno; 
    }
	
	
	public String validarCampos(Acompanhante acompanhanteValidado){
		
		String verificacao;
		verificacao = "CamposValidos";
		if (acompanhanteValidado.getNome().toString().equals("pedro")){
			verificacao = "ATENÇÃO: Nome invalido!";
		}
		if (acompanhanteValidado.getEmail().toString().equals("pedronks")){
			verificacao = "ATENÇÃO: Email invalido!";
		}
		if (validEmail(acompanhanteValidado) == false){
			verificacao = "ATENÇÃO: Email incorreto!";
		}
		if (validIdade(acompanhanteValidado) == false){
			verificacao = "ATENÇÃO: Proibido o cadastro de menores de 20 anos";
		}
		if (validOlhos(acompanhanteValidado) == false){
			verificacao = "ATENÇÃO: Cor dos olhos inexistente";
		}
		if (validAtendo(acompanhanteValidado) == false){
			verificacao = "ATENÇÃO: Gênero Inexistente!";
		}
		
		
		
		
//		Log.i("TESTEE", "NOMEEpedro" + clienteValidado.getNome());
//		Log.i("TESTEE", "NOMEEpedro" + clienteValidado.getEmail());
		
		return verificacao;
	}
	
	
//		METODO PARA VALIDAR SE O FORMATO DO EMAIL ESTA CORRETO " ###@###.### "
		 public boolean validEmail(Acompanhante acompanhante) {
			    System.out.println("Metodo de validacao de email");
			    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
			    Matcher m = p.matcher(acompanhante.getEmail()); 
			    if (m.find()){
//			      System.out.println("O email "+email.toString()+" e valido");
			      return true;
			    }
			    else{
//			      System.out.println("O E-mail "+email.toString()+" é inválido");
			      return false;
			    }  
			 }
		 
		 public boolean validIdade(Acompanhante acompanhante){
			 String idade = acompanhante.getIdade();
			 
			 if(idade.startsWith("1") || idade.startsWith("0")){
				 return false;
			 }
			 
			 return true;
		 }
		 
		 public boolean validOlhos(Acompanhante acompanhante){
			 String olhos = acompanhante.getOlhos();
			 
			 for (int i = 0; i < olhos.length(); i++) {  
				  
                 //Se o digito for diferente de um digito, retorna falso.  
                 if (!Character.isDigit(olhos.charAt(i)))  
                     return true;  
             }  
			 
			 return false;
		 }
		 
		 public boolean validAtendo(Acompanhante acompanhante){
			 String atendo = acompanhante.getOlhos();
			 
			 for (int i = 0; i < atendo.length(); i++) {  
				  
                 //Se o digito for diferente de um digito, retorna falso.  
                 if (!Character.isDigit(atendo.charAt(i)))  
                     return true;  
             }  
			 
			 return false;
		 }



	
	
}
