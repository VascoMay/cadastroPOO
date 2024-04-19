/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import cadastropoo.model.entidades.PessoaFisica;
import cadastropoo.model.entidades.PessoaJuridica;
import cadastropoo.model.gerenciadores.PessoaFisicaRepo;
import cadastropoo.model.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroPOO {

    public static void main(String[] args) {
        PessoaFisicaRepo repoPessoaFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoPessoaJuridica = new PessoaJuridicaRepo();

        int opcao;
        String tipoPessoa;
        String prefixo;
        Integer id;
        do {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Finalizar");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    tipoPessoa = pessoaFisicaOuPessoaJuridica(scanner);
                    switch (tipoPessoa) {
                        case "F":
                            try{
                                PessoaFisica pessoaFisica = lerDadosPessoaFisica(scanner);
                                repoPessoaFisica.inserir(pessoaFisica);
                            } catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "J":
                            try{
                                PessoaJuridica pessoaJuridica = lerDadosPessoaJuridica(scanner);
                                repoPessoaJuridica.inserir(pessoaJuridica);
                            } catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        default:
                            alertaOpçãoInvalida();
                            break;
                    }
                    break;

                case 2:
                    tipoPessoa = pessoaFisicaOuPessoaJuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpçãoInvalida();
                        break;
                    }
                    switch (tipoPessoa) {
                        case "F":
                            PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                            if (isPessoaValida(pessoaFisica)){
                                pessoaFisica.exibir();
                                
                                pessoaFisica = alterarDadosPessoaFisica(scanner, pessoaFisica);
                                repoPessoaFisica.alterar(pessoaFisica);
                            } else alertaPessoaInvalida();
                            
                            break;
                        case "J":
                            PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                            if (isPessoaValida(pessoaJuridica)){
                                pessoaJuridica.exibir();
                                
                                pessoaJuridica = alterarDadosPessoaJuridica(scanner, pessoaJuridica);
                                repoPessoaJuridica.alterar(pessoaJuridica);
                            } else alertaPessoaInvalida();
                            
                            break;
                        default:
                            alertaOpçãoInvalida();
                            break;
                    }
                    break;
                case 3:
                    tipoPessoa = pessoaFisicaOuPessoaJuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpçãoInvalida();
                        break;
                    }
                    
                    switch (tipoPessoa) {
                        case "F":
                            if (repoPessoaFisica.excluir(id))
                                System.out.println("Pessoa excluida com sucesso.");
                            else
                                System.out.println("Falha ao excluir.");
                            
                            break;
                        case "J":
                            if (repoPessoaJuridica.excluir(id))
                                System.out.println("Empresa excluida com sucesso.");
                            else
                                System.out.println("Falha ao excluir.");
                            
                            break;
                        default:
                            alertaOpçãoInvalida();
                            break;
                    }
                    
                    break;
                case 4:
                    tipoPessoa = pessoaFisicaOuPessoaJuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpçãoInvalida();
                        break;
                    }
                    
                    switch (tipoPessoa) {
                        case "F":
                            PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                            if (isPessoaValida(pessoaFisica))
                                pessoaFisica.exibir();
                            else alertaPessoaInvalida();
                            
                            break;
                        case "J":
                            PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                            if (isPessoaValida(pessoaJuridica))
                                pessoaJuridica.exibir();
                            else alertaPessoaInvalida();
                            
                            break;
                        default:
                            alertaOpçãoInvalida();
                            break;
                    }
                    
                    break;
                case 5:
                    tipoPessoa = pessoaFisicaOuPessoaJuridica(scanner);
                    switch (tipoPessoa) {
                        case "F":
                            for (PessoaFisica pessoa : repoPessoaFisica.obterTodos()) {
                                pessoa.exibir();
                            }
                            
                            break;
                        case "J":
                            for (PessoaJuridica empresa : repoPessoaJuridica.obterTodos()) {
                                empresa.exibir();
                            }
                            
                            break;
                        default:
                            alertaOpçãoInvalida();
                            break;
                    }
                    
                    break;
                case 6:
                    prefixo = obterPrefixo(scanner);
                    
                    try {
                        repoPessoaFisica.persistir(prefixo +".fisica.bin");
                        System.out.println("Dados de Pessoas Físicas Armazenados");
                        
                        repoPessoaJuridica.persistir(prefixo +".juridica.bin");
                        System.out.println("Dados de Pessoas Juridica Armazenados");
                    } catch (IOException ex) {
                        Logger.getLogger(CadastroPOO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                case 7:
                    prefixo = obterPrefixo(scanner);
                    
                    try {
                        repoPessoaFisica.recuperar(prefixo +".fisica.bin");
                        System.out.println("Dados de Pessoas Físicas Recuperados");

                        repoPessoaJuridica.recuperar(prefixo +".juridica.bin");
                        System.out.println("Dados de Pessoas Juridica Armazenados");
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(CadastroPOO.class.getName()).log(Level.SEVERE, null, ex);
                    }
  
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    alertaOpçãoInvalida();
                    break;
            }
        } while (opcao != 0);
    }
    
    private static void alertaOpçãoInvalida(){
        System.out.println("Opção inválida. Tente novamente.");
    }
    
    private static void alertaPessoaInvalida(){
        System.out.println("Pessoa/Empresa não encontrada.");
    }
    
    private static boolean isPessoaValida(Object obj){
        return obj != null;
    }
    
    private static String pessoaFisicaOuPessoaJuridica(Scanner scanner){
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        return scanner.next();
    }
    
    private static String obterPrefixo(Scanner scanner){
        System.out.println("Informe o prefixo do arquivo a ser salvo");
        return scanner.next();
    }
    
    private static Integer getId(Scanner scanner){
        System.out.println("Digite o id da pessoa:");
        try{
            return scanner.nextInt();
        } catch (Exception e) {
            return null;
        }
    }

    private static PessoaFisica lerDadosPessoaFisica(Scanner scanner) throws Exception{
        try{
            System.out.println("Insira os dados...");
            System.out.println("Digite o id da pessoa");
            int id = scanner.nextInt();

            System.out.println("Digite o nome da pessoa");
            String nome = scanner.next();

            System.out.println("Digite o cpf da pessoa");
            String cpf = scanner.next();

            System.out.println("Digite a idade da pessoa");
            int idade = scanner.nextInt();

            return new PessoaFisica(id, nome, cpf, idade);
        } catch(Exception e){
            throw new Exception("Dado digitado está incorreto. Tente novamente.");
        }
    }

    private static PessoaJuridica lerDadosPessoaJuridica(Scanner scanner) throws Exception{
        try{
            System.out.println("Digite o id da empresa");
            int id = scanner.nextInt();

            System.out.println("Digite o nome da empresa");
            String nome = scanner.next();

            System.out.println("Digite o cnpj da empresa");
            String cnpj = scanner.next();

            return new PessoaJuridica(id, nome, cnpj);
        } catch(Exception e){
            throw new Exception("Dado digitado está incorreto. Tente novamente.");
        }
    }
   
    private static PessoaFisica alterarDadosPessoaFisica(Scanner scanner, PessoaFisica pessoa){
        boolean continuar = true;
        
        while(continuar){
            System.out.println("Selecione uma opção:");
            System.out.println("N - Nome");
            System.out.println("C - CPF");
            System.out.println("I - Idade");
            System.out.println("F - Finalizar");
            String opcao = scanner.next();

            try{
                switch (opcao) {
                    case "N":
                        System.out.println("Digite o novo nome:");
                        String nome = scanner.next();
                        pessoa.setNome(nome);
                        break;
                    case "C":
                        System.out.println("Digite o novo CPF:");
                        String cpf = scanner.next();
                        pessoa.setCpf(cpf);
                        break;
                    case "I":
                        System.out.println("Digite a nova idade:");
                        int idade = scanner.nextInt();
                        pessoa.setIdade(idade);
                        break;
                    case "F":
                        continuar = false;
                        break;
                    default:
                        alertaOpçãoInvalida();
                }
            } catch(Exception e){
                alertaOpçãoInvalida();
            }
        }
        
        return pessoa;
    }

    private static PessoaJuridica alterarDadosPessoaJuridica(Scanner scanner, PessoaJuridica empresa){
        boolean continuar = true;
        
        while(continuar){
            System.out.println("Selecione uma opção:");
            System.out.println("N - Nome");
            System.out.println("C - CNPJ");
            System.out.println("F - Finalizar");
            String opcao = scanner.next();

            try{
                switch (opcao) {
                    case "N":
                        System.out.println("Digite o novo nome:");
                        String nome = scanner.next();
                        empresa.setNome(nome);
                        break;
                    case "C":
                        System.out.println("Digite o novo CNPJ:");
                        String cnpj = scanner.next();
                        empresa.setCnpj(cnpj);
                        break;
                    case "F":
                        continuar = false;
                        break;
                    default:
                        alertaOpçãoInvalida();
                }
            } catch(Exception e){
                alertaOpçãoInvalida();
            }
        }
        
        return empresa;
    }
}
