package cadastropoo;

import cadastropoo.model.entidades.PessoaFisica;
import cadastropoo.model.entidades.PessoaJuridica;
import cadastropoo.model.gerenciadores.PessoaFisicaRepo;
import cadastropoo.model.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;

public class CadastroPOOTestes {
    public static void main(String[] args) {
        try {
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            PessoaFisica pessoa1 = new PessoaFisica(1, "Ewerton", "938.489.620-98", 18);
            PessoaFisica pessoa2 = new PessoaFisica(2, "Ricardo", "277.047.440-54", 41);
            repo1.inserir(pessoa1);
            repo1.inserir(pessoa2);
            repo1.persistir("pessoasFisicas.bin");
            System.out.println("Dados de Pessoas Físicas Armazenados");
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("pessoasFisicas.bin");
            System.out.println("Dados de Pessoas Físicas Recuperados");
            for (PessoaFisica pessoa : repo2.obterTodos()) {
                pessoa.exibir();
            }
            
            // Testando repositório de pessoas jurídicas
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            PessoaJuridica empresa1 = new PessoaJuridica(1, "Empresa A", "77.154.419/0001-22");
            PessoaJuridica empresa2 = new PessoaJuridica(2, "Empresa B", "76.111.810/0001-87");
            repo3.inserir(empresa1);
            repo3.inserir(empresa2);
            repo3.persistir("pessoasJuridicas.bin");
            System.out.println("Dados de Pessoas Juridicas Armazenados");
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("pessoasJuridicas.bin");
            System.out.println("Dados de Pessoas Juridicas Recuperados");

            for (PessoaJuridica empresa : repo4.obterTodos()) {
                empresa.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
