package br.uni.mette_service.Controller.Cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.uni.mette_service.Model.Cliente;
import android.widget.EditText;

public class ValidarCliente extends CadastroClienteActivity {
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

	public String validarCampos(Cliente clienteValidado) {

		String verificacao;
		verificacao = "CamposValidos";
		if (clienteValidado.getEmail().toString().equals("pedronks")) {
			verificacao = "ATENÇÃO: Email invalido!";
		}
		if (validEmail(clienteValidado) == false) {
			verificacao = "ATENÇÃO: Email incorreto!";
		}

//		if (clienteValidado.getCpf().length() < 11
//				|| validaCPF(clienteValidado) == false) {
//			verificacao = "ATENÇÃO: cpf incorreto, leia seu cartao de cpf!";
//		}

		return verificacao;
	}

	// METODO PARA VALIDAR SE O FORMATO DO EMAIL ESTA CORRETO " ###@###.### "
	public boolean validEmail(Cliente cliente) {
		System.out.println("Metodo de validacao de email");
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(cliente.getEmail());
		if (m.find()) {
			// System.out.println("O email "+email.toString()+" e valido");
			return true;
		} else {
			// System.out.println("O E-mail "+email.toString()+" é inválido");
			return false;
		}
	}

	private static boolean validaCPF(Cliente cli) {
		boolean ret = false;
		String base = "000000000";
		String digitos = "00";
		// if (cli.get.length() <= 11) {
		// if (cpf.length() < 11) {
		// cpf = base.substring(0, 11 - cpf.length()) + cpf;
		// base = cpf.substring(0, 9);
		// }
		base = cli.getCpf().substring(0, 9);
		digitos = cli.getCpf().substring(9, 11);
		int soma = 0, mult = 11;
		int[] var = new int[11];
		// Recebe os números e realiza a multiplicação e soma.
		for (int i = 0; i < 9; i++) {
			var[i] = Integer.parseInt("" + cli.getCpf().charAt(i));
			if (i < 9)
				soma += (var[i] * --mult);
		}
		// Cria o primeiro dígito verificador.
		int resto = soma % 11;
		if (resto < 2) {
			var[9] = 0;
		} else {
			var[9] = 11 - resto;
		}
		// Reinicia os valores.
		soma = 0;
		mult = 11;
		// Realiza a multiplicação e soma do segundo dígito.
		for (int i = 0; i < 10; i++)
			soma += var[i] * mult--;
		// Cria o segundo dígito verificador.
		resto = soma % 11;
		if (resto < 2) {
			var[10] = 0;
		} else {
			var[10] = 11 - resto;
		}
		if ((digitos.substring(0, 1).equalsIgnoreCase(new Integer(var[9]).toString()))
				&& (digitos.substring(1, 2).equalsIgnoreCase(new Integer(var[10]).toString()))) {
			ret = true;
		}
		return ret;
	}

}
