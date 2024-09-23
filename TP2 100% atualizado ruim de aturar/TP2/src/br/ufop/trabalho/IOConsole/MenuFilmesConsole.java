package br.ufop.trabalho.IOConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;

public class MenuFilmesConsole {
	private Controle controle;
	private Scanner input;
	
	public MenuFilmesConsole(Controle controle, Scanner input){
		this.controle = controle;
		this.input = input;
	}
	
	public void exibeMenuFilmes(){
		boolean continua = true;
		int op  = 0;
		do{	
			// A opção 5 não é necessária. Foi inserida apenas para teste.
			System.out.println("\nDigite a opção:\n\t1 - Cadastrar filme\n\t2 - Buscar filme\n\t3 - imprime Lista de filmes\n\t4 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					leDadosFilmes();
				break;
				
				case 2:
					leDadosBuscaFilmes();
				break;
				
				case 3:
					//Esta opção não foi solicitada no enunciado. É apenas para testes
					imprimeListaFilmes();
				break;
				
				case 4:
					System.out.println("Você está saindo da área de Filmes...\n");
					continua = false;
				break;
				
				default:
					System.out.println("Opção Inválida!\n");
				break;
			}		
		}while(continua == true);
	}

	private void leDadosFilmes(){
		input.nextLine();
		String titulo, genero, tipoFilme;  
		int anoLancado, quantidadeDvds, quantidadeBluerays;
		System.out.println("\nDigite o nome do filme: ");
		titulo = input.nextLine();
		System.out.println("Digite o genero do filme: ");
		genero = input.nextLine();
		System.out.println("Digite o tipo do filme: ");
		tipoFilme = input.nextLine();
		System.out.println("Digite o ano lançado do filme: ");
		anoLancado = Util.leInteiroConsole(input);
		System.out.println("Digite a quantidade de DVD's do filme: ");
		quantidadeDvds = Util.leInteiroConsole(input);
		System.out.println("Digite a quantidade de Bluerays do filme: ");
		quantidadeBluerays = Util.leInteiroConsole(input);
		input.nextLine();
		int retorno = controle.cadastrarFilme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
		String msg = "";
		switch(retorno){
			case Constantes.ERRO_TIPO_FILME:
				msg = "\nTipo de filme inválido. Deve ser Lançamento, Novo ou Antigo.";
				break;
			case Constantes.FILME_REPETIDO:
				msg = "\nEsse filme já existe em seu cadastro!";
				break;
			case Constantes.ERRO_CAMPO_VAZIO:
					msg = "\nTodos os campos devem ser preenchidos!";
					break;
			case Constantes.RESULT_OK:
				msg = "\nFilme cadastrado com sucesso!";
				break;
		}
		System.out.println(msg);
		
	}
	
	private void leDadosBuscaFilmes() {
		input.nextLine();
		boolean continua = true;
		int op  = 0, disponibilidade;
		String titulo, genero;
		do{	
			System.out.println("Digite a opção de busca:\n\t1 - Buscar filme por nome\n\t2 - Buscar filme por genero\n\t3 - Buscar filme por disponibilidade\n\t4 - Voltar\n");
			op = Util.leInteiroConsole(input);
			List<Filme> resultado = new ArrayList<>();
			input.nextLine();
			switch(op){
				case 1:
					System.out.println("Nome: ");
					titulo = input.nextLine();
					resultado = controle.buscarFilme(titulo);
					break;
				case 2:
					System.out.println("Genero: ");
					genero = input.nextLine();
					resultado = controle.buscarFilme(genero);
					break;
				case 3:
					System.out.println("Disponibilidade: ");
					disponibilidade = input.nextInt();
					resultado = controle.buscarFilme(disponibilidade);
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!");
			}
			exibirFilmes(resultado);
		}while(continua == true);
	}
		
	private void exibirFilmes(List<Filme> filmes) {
        if (filmes.isEmpty()) {
            System.out.println("Não existe esse filme em seu cadastro");
        } else {
            int i = 1;
            for (Filme f : filmes) {
                System.out.println(i + " - Nome: " + f.getTitulo() +
                        " | Genero: " + f.getGenero() +
                        " | Tipo: " + f.getTipoFilme() +
                        " | Ano: " + f.getAnoLancado() +
                        " | DVD's disponiveis: " + f.getQuantidadeDvds() +
                        " | Bluerays disponiveis: " + f.getQuantidadeBluerays());
                i++;
            }
        }
        if (!filmes.isEmpty()) {
            System.out.println("Escolha o n° do filme que deseja modificar: ");
            int op =  Util.leInteiroConsole(input);
            if (op >= 1 && op <= filmes.size()) {
                Filme filmeEscolhido = filmes.get(op - 1);
                modificarFilmes(filmeEscolhido, op);
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
	
	public void modificarFilmes(Filme filme, int filmeEscolhindo) {
		input.nextLine();
		boolean continua = true;
		int op  = 0, i = 1, escolhaCliente = 0, tipo = 0;
		do{	
			System.out.println("Digite a opção de busca:\n\t1 - Editar filme\n\t2 - Excluir filme\n\t3 - Locar para cliente\n\t4 - Voltar\n");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					input.nextLine();
					String titulo, genero, tipoFilme, nome;  
					int anoLancado, quantidadeDvds, quantidadeBluerays;
					System.out.println("Digite o nome do filme: ");
					titulo = input.nextLine();
					System.out.println("Digite o genero do filme: ");
					genero = input.nextLine();
					System.out.println("Digite o tipo do filme: ");
					tipoFilme = input.nextLine();
					System.out.println("Digite o ano lançado do filme: ");
					anoLancado = Util.leInteiroConsole(input);
					System.out.println("Digite a quantidade de DVD's do filme: ");
					quantidadeDvds = Util.leInteiroConsole(input);
					System.out.println("Digite a quantidade de Bluerays do filme: ");
					quantidadeBluerays = Util.leInteiroConsole(input);
					input.nextLine();
					
					if (titulo.isBlank() || genero.isBlank() || tipoFilme.isBlank()) {
	                    System.out.println("Todos os campos devem ser preenchidos!");
	                } else {
	                	Filme atualizarFilme = new Filme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
	                	if (!atualizarFilme.setTipoFilme(tipoFilme)) {
	            			System.out.println("\nTipo de filme inválido. Deve ser Lançamento, Novo ou Antigo.");
	            		} else if (controle.verificarFilmeRepetido(atualizarFilme)) {
	                        System.out.println("Esse filme já existe em seu cadastro!");
	                    	} else {
		                        filme.setTitulo(titulo);
		                        filme.setGenero(genero);
		                        filme.setTipoFilme(tipoFilme);
		                        filme.setAnoLancado(anoLancado);
		                        filme.setQuantidadeDvds(quantidadeDvds);
		                        filme.setQuantidadeBluerays(quantidadeBluerays);
		                        System.out.println("Filme atualizado com sucesso!");
		                    }
	                }
								
					break;
				case 2:
					controle.exluirFilmes(filme);
					System.out.println("Filme removido com sucesso!");
					return;
				case 3:
					Filme filmeEscolhido = controle.getFilmes().get(filmeEscolhindo - 1);
					System.out.println("Deseja locar um DVD ou Blu-ray?\n\t1 - DVD\n\t2 - Blu-ray");
	                tipo = Util.leInteiroConsole(input);
	                input.nextLine();	              
	                
	                if (tipo == 1 || tipo == 2) {
	                	System.out.println("Digite o nome do cliente para locar o filme: ");
		                nome = input.nextLine();
		                List<Cliente> clientesEncontrados = new ArrayList<>();
		                for(Cliente c : controle.getClientes()) {
		                    if(c.getNome().equalsIgnoreCase(nome)) {
		                        clientesEncontrados.add(c);
		                    }
		                }
		                if (clientesEncontrados.isEmpty()) {
		                    System.out.println("Cliente não encontrado.");
		                } else if(clientesEncontrados.size() == 1) {
		                    Cliente cliente = clientesEncontrados.get(0);
		                    if (controle.locarFilmeCliente(cliente, filmeEscolhido, tipo) == false) {
	    	                	System.out.println("Filme indisponivel no momento");
	    	                } else {
			                    System.out.println("Filme locado com sucesso!");
		                    }
		                } else if(clientesEncontrados.size() > 1) {
		                    System.out.println("Foram encontrados múltiplos clientes com esse nome:");
		                    for (i = 0; i < clientesEncontrados.size(); i++) {
		                        Cliente cliente = clientesEncontrados.get(i);
		                        System.out.println((i + 1) + ". Nome: " + cliente.getNome() + " | Código: " + cliente.getCodigo() + " | CPF: " + cliente.getCpf());
		                    }
		                    System.out.println("Escolha o número do cliente que deseja locar o filme: ");
		                    escolhaCliente = Util.leInteiroConsole(input) - 1;
		                    
		                    if(escolhaCliente >= 0 && escolhaCliente < clientesEncontrados.size()) {
		                        Cliente clienteEscolhido = clientesEncontrados.get(escolhaCliente);
		                        if (controle.locarFilmeCliente(clienteEscolhido, filmeEscolhido, tipo) == false) {
		    	                	System.out.println("Filme indisponivel no momento");
		    	                } else {
			                        controle.locarFilmeCliente(clienteEscolhido, filmeEscolhido, tipo);
			                        System.out.println("Filme locado com sucesso!");	
		    	                }
		                    } else {
		                        System.out.println("Opção inválida.");
		                    }
		                }
		                
	                } else {
                        System.out.println("Opção inválida.");
                    }
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!");
			}
		}while(continua == true);
	}
	

	private void imprimeListaFilmes() {
		System.out.println("******** LISTA DE FILMES CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdFilmes(); i++){
			//É preciso implementar o toString corretamente.
			System.out.println(controle.getFilmesNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE FILMES  *********");
	}
}