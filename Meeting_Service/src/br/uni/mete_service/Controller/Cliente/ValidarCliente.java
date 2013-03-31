package br.uni.mete_service.Controller.Cliente;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.uni.mete_service.R;
import br.uni.mete_service.model.Cliente;

public class ValidarCliente extends CadastroClienteActivity{
	 Cliente cliente;
	 
	public boolean validarCampo(EditText editText) {
        boolean retorno = true;
        
        if (editText.getText().toString().equals("")) {
            editText.setError("Campo Obrigat�rio");
            editText.requestFocus();
            retorno = false;   
        } 

        return retorno; 
    }
	
	
	public String validarCampos(Cliente clienteValidado){
		
		String verificacao;
		verificacao = "CamposValidos";
		if (clienteValidado.getNome().toString().equals("pedro")){
			verificacao = "ATEN��O: Nome invalido!";
		}
		if (clienteValidado.getEmail().toString().equals("pedronks")){
			verificacao = "ATEN��O: Email invalido!";
		}
		if (validEmail(clienteValidado) == false){
			verificacao = "ATEN��O: Email incorreto!";
		}
		
		if (validaCPF(clienteValidado)== false ){
			verificacao = "ATEN��O: cpf incorreto, leia seu cartao de cpf!";
		}
		
//		Log.i("TESTEE", "NOMEEpedro" + clienteValidado.getNome());
//		Log.i("TESTEE", "NOMEEpedro" + clienteValidado.getEmail());
		
		return verificacao;
	}
	
	
//		METODO PARA VALIDAR SE O FORMATO DO EMAIL ESTA CORRETO " ###@###.### "
		 public boolean validEmail(Cliente cliente) {
			    System.out.println("Metodo de validacao de email");
			    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
			    Matcher m = p.matcher(cliente.getEmail()); 
			    if (m.find()){
//			      System.out.println("O email "+email.toString()+" e valido");
			      return true;
			    }
			    else{
//			      System.out.println("O E-mail "+email.toString()+" � inv�lido");
			      return false;
			    }  
			 }	
		 
		 
//		 private static boolean ValidaCPF(Cliente cli) {
//		        if (cli.getCpf().equals("00000000000") || cli.getCpf().equals("11111111111") ||
//		        	cli.getCpf().equals("22222222222") || cli.getCpf().equals("33333333333") ||
//		        	cli.getCpf().equals("44444444444") || cli.getCpf().equals("55555555555") ||
//		        	cli.getCpf().equals("66666666666") || cli.getCpf().equals("77777777777") ||
//		        	cli.getCpf().equals("88888888888") || cli.getCpf().equals("99999999999")){
//		            return false;
//		        }
//		 
//		        char dig10, dig11;
//		        int sm, i, r, num, peso;
//		 
//		        try {
//		            sm = 0;
//		            peso = 10;
//		            for (i = 0; i < 9; i++) {
//		 
//		                num = (int) (cli.getCpf().charAt(i) - 48);
//		                sm = sm + (num * peso);
//		                peso = peso - 1;
//		            }
//		 
//		            r = 11 - (sm % 11);
//		            if ((r == 10) || (r == 11))
//		                dig10 = '0';
//		            else
//		                dig10 = (char) (r + 48);
//		 
//		            sm = 0;
//		            peso = 11;
//		            for (i = 0; i < 10; i++) {
//		                num = (int) (cli.getCpf().charAt(i) - 48);
//		                sm = sm + (num * peso);
//		                peso = peso - 1;
//		            }
//		 
//		            r = 11 - (sm % 11);
//		            if ((r == 10) || (r == 11))
//		                dig11 = '0';
//		            else
//		                dig11 = (char) (r + 48);
//		 
//		            if ((dig10 == cli.getCpf().charAt(9)) && (dig11 == cli.getCpf().charAt(10)))
//		                return (true);
//		            else
//		                return (false);
//		        } catch (Exception erro) {
//		            return (false);
//		        }
//		 
//		    }
		 private static boolean validaCPF(Cliente cli) {  
		      boolean ret = false;  
		      String base = "000000000";  
		      String digitos = "00";  
//		      if (cli.get.length() <= 11) {  
//		         if (cpf.length() < 11) {  
//		            cpf = base.substring(0, 11 - cpf.length()) + cpf;  
//		            base = cpf.substring(0, 9);  
//		         }  
		         base = cli.getCpf().substring(0, 9);  
		         digitos = cli.getCpf().substring(9, 11);  
		         int soma = 0, mult = 11;  
		         int[] var = new int[11];  
		         // Recebe os n�meros e realiza a multiplica��o e soma.  
		         for (int i = 0; i < 9; i++) {  
		            var[i] = Integer.parseInt("" + cli.getCpf().charAt(i));  
		            if (i < 9)  
		               soma += (var[i] * --mult);  
		         }  
		         // Cria o primeiro d�gito verificador.  
		         int resto = soma % 11;  
		         if (resto < 2) {  
		            var[9] = 0;  
		         } else {  
		            var[9] = 11 - resto;  
		         }  
		         // Reinicia os valores.  
		         soma = 0;  
		         mult = 11;  
		         // Realiza a multiplica��o e soma do segundo d�gito.  
		         for (int i = 0; i < 10; i++)  
		            soma += var[i] * mult--;  
		         // Cria o segundo d�gito verificador.  
		         resto = soma % 11;  
		         if (resto < 2) {  
		            var[10] = 0;  
		         } else {  
		            var[10] = 11 - resto;  
		         }  
		         if ((digitos.substring(0, 1).equalsIgnoreCase(new Integer(var[9])  
		               .toString()))  
		               && (digitos.substring(1, 2).equalsIgnoreCase(new Integer(  
		                     var[10]).toString()))) {  
		            ret = true;  
		         }  
		  
		      return ret;  
		   }
	
	
}
