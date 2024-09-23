package br.ufop.trabalho.IOConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;

public class MenuClienteConsole {
	private Controle controle;
	private Scanner input;
	
	public MenuClienteConsole(Controle controle, Scanner input){
		this.controle = controle;
		this.input = input;
	}
	
	public void exibeMenuClientes(){
		boolean continua = true;
		int op  = 0;
		do{	
			// A opção 5 não é necessária. Foi inserida apenas para teste.
			System.out.println("\nDigite a opção:\n\t1 - Cadastrar Cliente\n\t2 - Buscar clientes\n\t3 - imprime Lista de Clientes\n\t4 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					leDadosCliente();
					break;
				case 2:
					if(controle.getQtdClientes()!=0)
					{
						leDadosBuscaCliente();
					}
					else{
						System.out.println("Nao ha clientes cadastrados!");
					}
					break;
				case 3:
					//Esta opção não foi solicitada no enunciado. É apenas para testes
					imprimeListaClientes();
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!\n");
			}		
		}while(continua == true);
	}

	/**
	 * Este método permitirá a entrada dos dados de um cliente. 
	 * 		MÉTODO INCOMPLETO. NÃO CADASTRA TODOS OS DADOS.
	 */
	private void leDadosCliente(){
		//Limpa o buffer já que leu um inteiro
		input.nextLine();
        String nome, end, cpf;
        int codigo;
        LocalDate dataNascimento;
        System.out.println("Digite o nome do cliente");
        nome = input.nextLine();
        System.out.println("Digite o endereco do cliente");
        end = input.nextLine();
        System.out.println("Digite o codigo do cliente");
        codigo = Util.leInteiroConsole(input);
        input.nextLine();
        System.out.println("Digite o CPF do cliente");
        cpf = input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy)");
            String dataString = input.nextLine();
            try {
                dataNascimento = LocalDate.parse(dataString, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        } 
        Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
        int retorno = controle.cadastrarCliente(nome, end, codigo, cpf, data);
	    
		String msg = "";
		switch(retorno){
		//Verificação do retorno do método de adição de cliente
			case Constantes.CLIENTE_REPETIDO:
				msg = "Já exite um cliente com esse cadastro";
				break;
			case Constantes.ERRO_CAMPO_VAZIO:
				msg = "Todos os campos devem ser preenchidos!";
				break;
			case Constantes.RESULT_OK:
				leDadosDependente(); 
				msg = "Cliente cadastrado com sucesso!";
				break;
		}
		System.out.println(msg);

	}
	
	private void leDadosBuscaCliente(){
		int op = 0, codigo;
		String nome, dependente;
		boolean rodando=true;
		while(rodando){
			List<Cliente> resultado = new ArrayList<>();
			System.out.println("Como deseja buscar o cliente?");
			System.out.println("1 - Nome");
			System.out.println("2 - Codigo");
			System.out.println("3 - Nome do dependente");
			op = Util.leInteiroConsole(input);
			input.nextLine();
			switch(op){
				case 1:
					System.out.println("Digite o nome do cliente: ");
					nome = input.nextLine();
					resultado = controle.buscaCliente(nome);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				case 2:
					System.out.println("Digite o codigo: ");
					codigo = Util.leInteiroConsole(input);
					resultado = controle.buscaCliente(codigo);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				case 3:
					System.out.println("Digite o nome de um dependente: ");
					dependente = input.nextLine();
					resultado = controle.buscaDependentes(dependente);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				default:
					System.out.println("Opcao invalida!");
					break;
			}
		}
	}
	
	private void exibirBuscaClientes(List<Cliente> clientes){
		if(clientes.isEmpty()){
			System.out.println("Cliente inexistente.\n");
		}
		else{
			int count=1;
			System.out.println("Clientes encontrados:");
			for (Cliente c : clientes){
				System.out.println(count + " - " + c.getNome());
				count++;
			}
			count=1;
			System.out.println("\nEscolha um.");
			int op = Util.leInteiroConsole(input);
			for(Cliente c : clientes){
				if(count==op){
					exibeOpcoesCliente(c);
				}
				count++;
			}
		}
	}

	private void exibeOpcoesCliente(Cliente cliente){
		controle.setCliente(cliente);
		int op = 0;
		imprimirCliente(cliente);
		boolean rodando=true;
		while(rodando){
			System.out.println("1 - Editar cliente");
			System.out.println("2 - Adicionar dependente");
			System.out.println("3 - Excluir dependente");
			System.out.println("4 - Locar filme");
			System.out.println("5 - Devolver filme");
			System.out.println("6 - Pagar multa");
			System.out.println("7 - Voltar");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					System.out.println("Insira as novas informacoes: ");
					editarCliente(cliente);
					imprimirCliente(cliente);
					break;
				
				case 2:
					leDadosDependente();
					System.out.println("\nAtuais dependentes:");
					imprimeListaDependentes(cliente);
					break;
				
				case 3:
					removerDependente(cliente);
					System.out.println("\nAtuais dependentes:");
					imprimeListaDependentes(cliente);
					break;

				case 4:
					if(cliente.getMulta()==0){
						if(cliente.getFilmes().size()<5){
							locarFilme(cliente);
						}
						else{
							System.out.println("O cliente atingiu o numero maximo de filmes locados!");
						}
					}
					else{
						System.out.println("O cliente possui debitos nao pagos. Pague as multas pendentes para poder locar mais filmes!");
					}
					break;

				case 5:
					if(!cliente.getFilmes().isEmpty()){
						devolverFilme(cliente);
					}
					else{
						System.out.println("O cliente nao possui filmes locados.");
					}
					break;

				case 6:
					if(cliente.getMulta()==0){
						System.out.println("Nao ha multa para ser paga.");
					}
					else{
						cliente.setMulta(0);
						System.out.println("Multa paga com sucesso!");
					}
					break;

				case 7:
					rodando=false;
					break;

				default:
					System.out.println("Opcao invalida!");
					break;
			}
		}
	}
	
	private void imprimirCliente(Cliente cliente){
		System.out.println("Dados do cliente:\n");
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Codigo: " + cliente.getCodigo());
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Data de nascimento: " + cliente.getDataNascimento());
		System.out.println("Endereco: " + cliente.getEndereco());
		System.out.println("Filmes locados:\n");
		imprimirFilmesLocados(cliente);
		System.out.println("\nMulta: R$" + cliente.getMulta());
		System.out.println("Dependentes:\n"); 
		imprimeListaDependentes(cliente);
	}

	private void editarCliente(Cliente cliente){
		input.nextLine();
		String nome, end, cpf;
		int codigo;
		LocalDate dataNascimento;
		boolean rodando = true;
		while(rodando){
			System.out.println("Digite o nome do cliente");
			nome = input.nextLine();

			System.out.println("Digite o endereco do cliente");
			end = input.nextLine();

			System.out.println("Digite o codigo do cliente");		
			codigo = Util.leInteiroConsole(input);
			
			input.nextLine();
			System.out.println("Digite o CPF do cliente");
			cpf = input.nextLine();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			while (true) {
				System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy)");
				String dataString = input.nextLine();
				try {
					dataNascimento = LocalDate.parse(dataString, formatter);
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Formato de data inválido. Tente novamente.");
				}
			}
			Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
			if(Util.verificaListaStringPreenchida(nome, end, cpf) == false ){
				System.out.println("Preencha todos os campos.");
			}
			else{
				Cliente teste = new Cliente(nome, end, codigo, cpf, null);
				if(controle.verificarClienteRepetido(teste)){
					System.out.println("Ja existe um cliente com estes dados.");
				}
				else{
					cliente.setNome(nome);
					cliente.setEndereco(end);
					cliente.setCodigo(codigo);
					cliente.setCpf(cpf);
					cliente.setDataNascimento(data);
					rodando=false;
				}
			}
		}
	}
	
	private void leDadosDependente() {
	    boolean continua = true;
	    int contadorDependentes = 0; // Contador para controlar o número de dependentes cadastrados

	    do {
	        if (contadorDependentes < 3) {
	            System.out.println("Deseja cadastrar dependente? \n\t1 - Cadastrar dependente\n\t2 - Não cadastrar dependente\n");
	            int resp = input.nextInt();
	            switch (resp) {
	                case 1:
	                    input.nextLine();
	                    String nome, end, cpf;
	                    LocalDate dataNascimento;
	                    System.out.println("Digite o nome do dependente");
	                    nome = input.nextLine();
	                    System.out.println("Digite o endereço do dependente");
	                    end = input.nextLine();
	                    System.out.println("Digite o CPF do dependente");
	                    cpf = input.nextLine();
	                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                    while (true) {
	                        System.out.println("Digite a data de nascimento do dependente (dd/MM/yyyy)");
	                        String dataString = input.nextLine();
	                        try {
	                            dataNascimento = LocalDate.parse(dataString, formatter);
	                            break;
	                        } catch (DateTimeParseException e) {
	                            System.out.println("Formato de data inválido. Tente novamente.");
	                        }
	                    }
	                    Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
	                    int retorno = controle.cadastrarDependente(nome, end, cpf, data);
	                    String msg = "";
	                    switch (retorno) {
		                    case Constantes.ERRO_CLIENTE_NAO_SELECIONADO:
	                            msg = "Nenhum cliente selecionado.";
	                            break;
	                        case Constantes.DEPENDENTE_REPETIDO:
	                            msg = "Esse dependente já está cadastrado para este cliente";
	                            break;
	                        case Constantes.ERROR_LIMITE_DEPENDENTE:
	                            return;
	                        case Constantes.ERRO_CAMPO_VAZIO:
	                            msg = "Todos os campos devem ser preenchidos!";
	                            break;
	                        case Constantes.RESULT_OK:
	                            contadorDependentes++;
	                            msg = "Dependente cadastrado com sucesso!";
	                            break;
	                    }
	                    System.out.println(msg);
	                    break;
	                case 2:
	                    return;
	                default:
	                    System.out.println("Opção Inválida!");
	            }
	        } else {
	            System.out.println("Número máximo de dependentes (3) já cadastrado para este cliente.");
	            return;
	        }
	    } while (continua);
	}
	
	private void removerDependente(Cliente cliente){
		if(cliente.getDependentes() != null){
			input.nextLine();
			System.out.println("Digite o nome do dependente que deseja remover ");
			String nome = input.nextLine();
			if(Util.verificaListaStringPreenchida(nome)!=false){
				boolean dependenteEncontrado=false;
				Dependentes ctrl = new Dependentes();
				for(Dependentes d : cliente.getDependentes()){
					if(d.getNome().equals(nome)){
						ctrl=d;
						dependenteEncontrado=true;
						break;
					}
				}
				if(dependenteEncontrado==true){
					cliente.excluirDependente(ctrl);
					System.out.println("Exclusao bem sucedida!");
				}
				else{
					System.out.println("Nao existe dependente correspondente.");
				}
			}
			else{
				System.out.println("O campo deve estar preenchido!");
			}

		}
		else{
			System.out.println("Nao existem dependentes deste cliente!");
		}
	}
	
	public void locarFilme(Cliente cliente){
		input.nextLine();
		System.out.println("Digite o nome do filme que deseja locar");
		String nome = input.nextLine();
		if(controle.buscarFilme(nome).isEmpty()){
			System.out.println("Sem resultados disponíveis para este filme.");
		}
		else{
			Filme filme = new Filme();
			filme = controle.buscarFilme(nome).get(0);
			if(filme.getQuantidadeBluerays()==0 && filme.getQuantidadeDvds()==0){
				System.out.println("Desculpe. Todas copias deste filme ja estao locadas!");
			}
			else{
				System.out.println("Deseja locar um DVD ou Blu-ray?\n1 - DVD\n2 - Blu-ray");
				int op = Util.leInteiroConsole(input);
				switch(op){
					case 1:
						if(filme.getQuantidadeDvds()==0){
							System.out.println("Nao existe DVD disponiveil para este filme");
						}
						else{
							//pode ser reaproveitado para a classe entradas
							//criar uma função para impressão e chamar ela daqui
							System.out.println("\nCliente: " + cliente.getNome());
							System.out.println("Filme: " + filme.getTitulo());
							System.out.println("Valor: R$" + controle.getValorLocacaoDiaria(filme));
							//System.out.println("Data: " + funcaoQueColocaADataDeHoje());
							filme.setQuantidadeDvds(filme.getQuantidadeDvds()-1);
							cliente.getTipoMidiaLocada().add("DVD");
							cliente.getFilmes().add(filme);	
							System.out.println("Filme locado com sucesso!");
						}
						break;

					case 2:
						if(filme.getQuantidadeBluerays()==0){
							System.out.println("Nao existe Blu-ray disponivel para este filme");
						}
						else{
							//pode ser reaproveitado para a classe entradas
							//criar uma função para impressão e chamar ela daqui
							System.out.println("\nCliente: " + cliente.getNome());
							System.out.println("Filme: " + filme.getTitulo());
							System.out.println("Valor: R$" + controle.getValorLocacaoDiaria(filme));
							//System.out.println("Data: " + funcaoQueColocaADataDeHoje());
							filme.setQuantidadeBluerays(filme.getQuantidadeBluerays()-1);
							cliente.getTipoMidiaLocada().add("Blu-ray");
							cliente.getFilmes().add(filme);	
							System.out.println("Filme locado com sucesso!");
						}
						break;

					default:
						System.out.println("Opcao invalida");
						break;
				}
			}
		}
	}

	private void devolverFilme(Cliente cliente){
		imprimirFilmesLocados(cliente);
		if(!cliente.getFilmes().isEmpty()){
			input.nextLine();
			System.out.println("Digite o nome do filme que deseja devolver:");	
			String nome = input.nextLine();
			int count = 0;
			for(Filme f : cliente.getFilmes()){
				if(nome.toLowerCase().equals(f.getTitulo().toLowerCase())){
					if(cliente.getTipoMidiaLocada().get(count).equals("DVD")){
						cliente.getTipoMidiaLocada().remove(count);
						cliente.getFilmes().remove(f);
						f.setQuantidadeDvds(f.getQuantidadeDvds()+1);
					}
					else{
						cliente.getTipoMidiaLocada().remove(count);
						cliente.getFilmes().remove(f);
						f.setQuantidadeBluerays(f.getQuantidadeBluerays()+1);
					}
					boolean rodando = true;
					while(rodando){
						System.out.println("Houve atraso na devolucao?\n1 - Sim\n2 - Nao");
						int op = Util.leInteiroConsole(input);
						switch(op){
							case 1:
								input.nextLine();
								System.out.println("Quantos dias de atraso?");
								op = Util.leInteiroConsole(input);
								controle.aplicarMulta(cliente, op);
								rodando = false;
								break;

							case 2:
								rodando=false;
								break;

							default:
								System.out.println("Digite uma opcao valida.");
								break;
						}
					}
					return;
				}
				count++;
			}
			System.out.println("Voce nao possui filme correspondente locado!");
		}
	}
	
	private void imprimirFilmesLocados(Cliente cliente){
		if(!cliente.getFilmes().isEmpty() && !cliente.getTipoMidiaLocada().isEmpty()){
			int count=0;
			for(Filme f : cliente.getFilmes()){
				System.out.println(count+1 + " - " + f.getTitulo() + " (" + cliente.getTipoMidiaLocada().get(count) + ")");
				count++;
			}
		}
		else{
			System.out.println("Nenhum filme locado");
		}
	}
	
	private void imprimeListaClientes() {
		System.out.println("******** LISTA DE CLIENTES CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdClientes(); i++){
			//É preciso implementar o toString corretamente.
			System.out.println(controle.getClienteNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
	}
	
	private void imprimeListaDependentes(Cliente c){
		List<Dependentes> dependentes = new ArrayList<>();
		dependentes = c.getDependentes();
		int count = 1;
		if(!dependentes.isEmpty()){
			for(Dependentes d : dependentes){
				System.out.println(count + " - " + d.getNome());
				count++;
			}
			System.out.println("\n");
		}
		else{
			System.out.println("Nenhum dependente\n");
		}
	}	
	

}