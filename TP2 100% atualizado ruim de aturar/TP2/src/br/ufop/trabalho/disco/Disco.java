package br.ufop.trabalho.disco;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;

public class Disco {

	Controle c = new Controle();
	
	public Disco(Controle c) {
		super();
		this.c = c;
	}

	public void salvarClientes(String nomeArquivo) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
        	obj.writeObject(c.getClientes()); // Clientes é o ArrayList que você quer salvar
            System.out.println("Clientes salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarClientes(String nomeArquivo) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
        	ArrayList<Cliente> clientesCarregados = (ArrayList<Cliente>) obj.readObject(); // Lê e atribui os clientes
        	c.setClientes(clientesCarregados);
            System.out.println("Clientes carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }


    public void salvarFilmes(String nomeArquivo) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
        	obj.writeObject(c.getFilmes()); // Filmes é o ArrayList que você quer salvar
            System.out.println("Filmes salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar filmes: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
	public void carregarFilmes(String nomeArquivo) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
        	ArrayList<Filme> filmesCarregados = (ArrayList<Filme>) obj.readObject();
           c.setFilmes(filmesCarregados);
            System.out.println("Filmes carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar filmes: " + e.getMessage());
        }
    }
	
}
