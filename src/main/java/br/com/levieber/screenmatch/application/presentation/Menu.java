package br.com.levieber.screenmatch.application.presentation;

import br.com.levieber.screenmatch.application.presentation.controllers.SeasonController;
import br.com.levieber.screenmatch.application.presentation.controllers.SeriesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {
    @Autowired
    SeriesController seriesController;
    @Autowired
    SeasonController seasonController;
    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            System.out.println("""
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    -1 - Sair
                    Escolha uma opção:""");
            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == -1) {
                System.out.println("Encerrando...");
                break;
            }

            switch (option) {
                case 1: {
                    System.out.println("Digite o nome da série:");
                    String seriesName = scanner.nextLine();
                    var series = seriesController.get(seriesName);
                    System.out.println(series);
                    break;
                }
                case 2: {
                    System.out.println("Digite o nome da série:");
                    String seriesName = scanner.nextLine();
                    var series = seriesController.get(seriesName);
                    var seasons = seasonController.index(series);
                    seasons.forEach(System.out::println);
                    break;
                }
                case 3: {
                    var seriesList = seriesController.index();
                    seriesList.forEach(System.out::println);
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
