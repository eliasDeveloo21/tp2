package br.ufop.trabalho;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	public static boolean verificaStringPreenchida(String texto){
		if(texto != null && texto.trim().isEmpty()){
			return false;
		}
		return true;
	}
	public static boolean verificaListaStringPreenchida(String ... strings ){
		for(String s: strings){
			if(verificaStringPreenchida(s) == false)
				return false;
		}
		return true;
	}
	public static boolean senhaComNumero (String senha){
		//FALTA IMPLEMENTAR
		return true;
	}
	
	public static int leInteiroConsole(Scanner in){
		int r = 0;
		boolean continua = false;
		do{
			try{
				r = in.nextInt();
				continua = false;		
			}
			catch(InputMismatchException e){
				System.out.println("Erro ao ler número!\nDigite novamente:");
				in.nextLine();
				continua = true;
			}
		}while(continua);
		return r;
	}
			
	
}