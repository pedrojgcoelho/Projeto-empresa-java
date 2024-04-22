package projeto.empresa.projetoempresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import projeto.empresa.projetoempresa.model.Cargo;
import projeto.empresa.projetoempresa.model.DAO.CargoDAO;

@SpringBootApplication
public class ProjetoempresaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoempresaApplication.class, args);
	}

	@Autowired
	CargoDAO dcargo;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("###### iniciando carregamento dos dados");

		Cargo carg = new Cargo();
		carg.setNome("Diretor");
		carg.setSalario(10000.0);
		dcargo.save(carg);
	}
}
