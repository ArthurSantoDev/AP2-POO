import java.util.Scanner;

import repository.FuncionarioRepository;
import service.FuncionarioService;
import view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService(new FuncionarioRepository());
        FuncionarioView view = new FuncionarioView(service, new Scanner(System.in));
        view.iniciar();
    }
}
