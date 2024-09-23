package br.ufop.trabalho.controle;

/**
 * Esta interface será utilizada apenas para armazenar constantes. Cada constante deverá ser estática e final e para seguirmos a convenção
 * elas também deverão ter todas as letras maiúsculas. 
 * 
 * As constantes inteiras servem para representar alguma informação importante de forma mais simples. Por exemplo: 
 * se considerarmos a chamada a um método para cadastro de Cliente passando todos os dados referentes ao cadastro do cliente. 
 * O método deverá fazer várias verificações com o objetivo de garantir que os dados passados estão corretos. Caso haja algum erro o 
 * método de cadastro de cliente deverá "informar" a quem o chamou o erro que ocorreu para que providências possam ser tomadas 
 * (pedir que o usuário digite os dados novamente). Uma alternativa para executar tal procedimento é o retorno de uma String contendo a 
 * descrição do erro. Essa solução ruim pois exige a todo momento a comparação entre Strings que é algo custoso computacionalmente. 
 * Uma alternativa mais simples é criar um inteiro para retorno e atribuir um identificador (nome da variavel) que pode diretamente indicar 
 * qual erro ocorreu. Para retornar o erro de cadastro de cliente com nome vazio deverá ser executado o código
 * abaixo:
 * 
 *  				Constantes. ERRO_CAMPO_VAZIO
 *  
 *  No retorno bastará fazer uma verificação para qual tipo de retorno ocorreu erro ou RESULT_OK.
 *  
 *  IMPORTANTE: OS NúMEROS QUE SERÃO RETORNADOS EM UM MESMO MÉTODO DEVEM OBRIGATORIAMENTE SER DIFERENTES.
 * 
 * @author Filipe
 *
 */
public interface Constantes {
	
public static final int RESULT_OK = 1;
	
	public static final int ERRO_CAMPO_VAZIO = 2;
	
	public static final int ERROR_LIMITE_DEPENDENTE = 3;
	
	public static final int ERRO_CLIENTE = 4;
	
	public static final int FILME_REPETIDO = 5;
	
	public static final int CLIENTE_REPETIDO = 6;

	public static final int CLIENTE_SEM_DEPENDENTES = 7;

	public static final int DEPENDENTE_INEXISTENTE = 8;
	
	public static final int DEPENDENTE_REPETIDO = 9;
	
	public static final int ERRO_TIPO_FILME = 10;
	
	public static final int ERRO_LIMITE_FILME = 11;
	
	public static final int ERRO_CLIENTE_NAO_SELECIONADO = 12;
}

