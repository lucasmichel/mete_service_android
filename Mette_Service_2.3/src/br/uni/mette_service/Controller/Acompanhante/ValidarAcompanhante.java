package br.uni.mette_service.Controller.Acompanhante;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.uni.mette_service.Model.Acompanhante;


import android.widget.EditText;

public class ValidarAcompanhante extends CadastroAcompanhanteActivity{
	 Acompanhante acompanhante;
	 
	public boolean validarCampo(EditText editText) {
        boolean retorno = true;
        
        if (editText.getText().toString().equals("")) {
            editText.setError("Campo Obrigat�rio");
            editText.requestFocus();
            retorno = false;   
        } 

        return retorno; 
    }
	
	
	public String validarCampos(Acompanhante acompanhanteValidado){
		
		String verificacao;
		verificacao = "CamposValidos";
		if (acompanhanteValidado.getNome().toString().equals("pedro")){
			verificacao = "ATEN��O: Nome invalido!";
		}
		if (acompanhanteValidado.getEmail().toString().equals("pedronks")){
			verificacao = "ATEN��O: Email invalido!";
		}
		if (validEmail(acompanhanteValidado) == false){
			verificacao = "ATEN��O: Email incorreto!";
		}
		if (validIdade(acompanhanteValidado) == false){
			verificacao = "ATEN��O: Proibido o cadastro de menores de 20 anos!";
		}
		if (validOlhos(acompanhanteValidado) == false){
			verificacao = "ATEN��O: Cor dos olhos inexistente!";
		}
		
		if (validHorario_Atendimento(acompanhanteValidado) == false){
			verificacao = "ATEN��O: Formato de horas inv�lido (HH:MM)";
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
//			      System.out.println("O E-mail "+email.toString()+" � inv�lido");
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
		

		 private boolean validHorario_Atendimento(Acompanhante acompanhante) {
				String horario = acompanhante.getHorario_atendimento();
				
				if (horario.length() != 5){
					return false;
				}
				return true;
			}

	
	
}
