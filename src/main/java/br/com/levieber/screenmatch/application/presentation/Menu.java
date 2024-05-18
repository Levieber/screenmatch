package br.com.levieber.screenmatch.application.presentation;

import br.com.levieber.screenmatch.application.presentation.controllers.EpisodeController;
import br.com.levieber.screenmatch.application.presentation.controllers.SeriesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {
    @Autowired
    SeriesController seriesController;
    @Autowired
    EpisodeController episodeController;
    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            System.out.println("""
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por nome
                    5 - Buscar séries por ator
                    6 - Listar top 5 séries
                    7 - Buscar por um gênero
                    8 - Buscar séries curtas
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
                    seriesController.index().forEach(System.out::println);
                    System.out.println("Digite o nome da série:");
                    String seriesName = scanner.nextLine();
                    var series = episodeController.index(seriesName);
                    if (series.isPresent()) {
                        System.out.println(series.get());
                    } else {
                        System.out.println("Série não encontrada!");
                    }
                    break;
                }
                case 3: {
                    var seriesList = seriesController.index();
                    seriesList.forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.println("Digite o nome da série:");
                    String seriesName = scanner.nextLine();
                    var series = seriesController.findByName(seriesName);
                    if (series.isPresent()) {
                        System.out.println(series.get());
                    } else {
                        System.out.println("Série não encontrada!");
                    }
                }
                case 5: {
                    System.out.println("Digite o nome do autor:");
                    String actorName = scanner.nextLine();
                    System.out.println("Digite a nota mínima das séries:");
                    double minRating = scanner.nextDouble();
                    scanner.nextLine();
                    var seriesList = seriesController.findByActor(actorName, minRating);
                    if (!seriesList.isEmpty()) {
                        System.out.printf("Séries em que %s trabalhou:%n", actorName);
                        seriesList.forEach(s -> System.out.printf("Série %s com nota %.1f%n", s.getName(), s.getRating()));
                    } else {
                        System.out.println("Séries com esse critério não encontradas!");
                    }
                    break;
                }
                case 6: {
                    var top5Series = seriesController.findTop5();
                    if (!top5Series.isEmpty()) {
                        System.out.println("Top 5 séries:");
                        top5Series.forEach(s -> System.out.printf("Série %s com nota %.1f%n", s.getName(), s.getRating()));
                    } else {
                        System.out.println("Não há séries. Busque algumas séries.");
                    }
                    break;
                }
                case 7: {
                    System.out.println("Digite um gênero:");
                    var genre = scanner.nextLine();
                    var seriesList = seriesController.findByGenre(genre);
                    if (!seriesList.isEmpty()) {
                        System.out.printf("Séries do gênero %s:%n", genre);
                        seriesList.forEach(s -> System.out.printf("Série %s com nota %.1f%n", s.getName(), s.getRating()));
                    } else {
                        System.out.println("Não foi encontrado séries desse gênero.");
                    }
                    break;
                }
                case 8: {
                    System.out.println("Digite o máximo de temporadas:");
                    int maxSeasons = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite a nota mínima das séries:");
                    double minRating = scanner.nextDouble();
                    scanner.nextLine();
                    var seriesList = seriesController.findShort(maxSeasons, minRating);
                    if (!seriesList.isEmpty()) {
                        System.out.printf("Séries com %d temporadas ou menos para maratonar:%n", maxSeasons);
                        seriesList.forEach(s -> System.out.printf("Série %s com nota %.1f%n", s.getName(), s.getRating()));
                    } else {
                        System.out.println("Séries com esse critério não encontradas!");
                    }
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
