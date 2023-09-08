package io.igorcossta;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeRepository;
import io.igorcossta.user.User;
import io.igorcossta.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RecipeRepository recipeRepository) {
        return args -> {
            User user = userRepository.save(new User("a@a.com", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq"));
            User user2 = userRepository.save(new User("b@b.net", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq"));

            for (int i = 0; i < 2; i++) {
                recipeRepository.save(new Recipe(user, "recipe name " + i, "recipe description", "Massa:\n" +
                        "\n" +
                        "    1/2 xícara (chá) de óleo\n" +
                        "    2 cenouras médias\n" +
                        "    4 ovos\n" +
                        "    2 xícaras (chá) de açúcar\n" +
                        "    2 e 1/2 xícaras (chá) de farinha de trigo\n" +
                        "    1 colher (sopa) de fermento em pó\n" +
                        "\n" +
                        "Calda de chocolate durinha:\n" +
                        "\n" +
                        "    1 xícara (chá) de açúcar\n" +
                        "    1 xícara (chá) de achocolatado ou chocolate em pó\n" +
                        "    4 colheres (sopa) de margarina ou manteiga\n" +
                        "    1/2 xícara (chá) de leite\n", "Modo de preparo:\n" +
                        "\n" +
                        "    Comece fazendo a massa do bolo. Para isso, escolha bem as cenouras e higienizá-las adequadamente.\n" +
                        "    Em seguida, corte-as em pedaços médios e leve para o liquidificador junto com o açúcar, os ovos e o óleo. Bata bem até que toda a cenoura tenha sido triturada, pode deixar batendo por uns 5 minutos em velocidade máxima.\n" +
                        "    Quando tiver um creme homogêneo, transfira para uma tigela e acrescente a farinha de trigo peneirada aos poucos.\n" +
                        "    Misture com ajuda de um batedor de arames, apenas o suficiente para incorporar a farinha.\n" +
                        "    Finalize adicionando o fermento em pó e misturando bem.\n" +
                        "    Despeje a massa em uma forma untada e enfarinhada e leve para assar em forno preaquecido a 180º C por 35 a 40 minutos. Faça o teste do palito para confirmar o ponto.\n" +
                        "    Se for desenformar o bolo, espere esfriar primeiro, para não correr o risco de quebrar.\n" +
                        "\n" +
                        "Calda de chocolate durinha:\n" +
                        "\n" +
                        "    Fique bem atento ao modo de preparo e siga tudo direitinho para garantir que a sua calda de chocolate sairá perfeita, crocante e craquelada.\n" +
                        "    Em uma panela, adicione a manteiga em fogo baixo, depois o açúcar, o achocolatado e então o leite.\n" +
                        "    Misture tudo muito bem e deixe cozinhar em fogo bem baixo até o ponto de brigadeiro. É importante que seja em fogo baixo!\n" +
                        "    Assim que der o ponto, despeje a calda por cima do bolo rapidamente, espalhando com agilidade para cobrir todo o bolo.\n" +
                        "    Essa calda de chocolate endurece muito rápido, então não pode deixar esfriar!\n" +
                        "    Depois que tiver espalhado pelo bolo, é só levar para a geladeira para gelar por 1 hora antes de comer se preferir geladinho. Do contrário, é só devorar!\n"));
            }
            for (int i = 0; i < 2; i++) {
                recipeRepository.save(new Recipe(user2, "Focaccia " + i, "recipe description", "Massa:\n" +
                        "\n" +
                        "    1/2 xícara (chá) de óleo\n" +
                        "    2 cenouras médias\n" +
                        "    4 ovos\n" +
                        "    2 xícaras (chá) de açúcar\n" +
                        "    2 e 1/2 xícaras (chá) de farinha de trigo\n" +
                        "    1 colher (sopa) de fermento em pó\n" +
                        "\n" +
                        "Calda de chocolate durinha:\n" +
                        "\n" +
                        "    1 xícara (chá) de açúcar\n" +
                        "    1 xícara (chá) de achocolatado ou chocolate em pó\n" +
                        "    4 colheres (sopa) de margarina ou manteiga\n" +
                        "    1/2 xícara (chá) de leite\n", "Modo de preparo:\n" +
                        "\n" +
                        "    Comece fazendo a massa do bolo. Para isso, escolha bem as cenouras e higienizá-las adequadamente.\n" +
                        "    Em seguida, corte-as em pedaços médios e leve para o liquidificador junto com o açúcar, os ovos e o óleo. Bata bem até que toda a cenoura tenha sido triturada, pode deixar batendo por uns 5 minutos em velocidade máxima.\n" +
                        "    Quando tiver um creme homogêneo, transfira para uma tigela e acrescente a farinha de trigo peneirada aos poucos.\n" +
                        "    Misture com ajuda de um batedor de arames, apenas o suficiente para incorporar a farinha.\n" +
                        "    Finalize adicionando o fermento em pó e misturando bem.\n" +
                        "    Despeje a massa em uma forma untada e enfarinhada e leve para assar em forno preaquecido a 180º C por 35 a 40 minutos. Faça o teste do palito para confirmar o ponto.\n" +
                        "    Se for desenformar o bolo, espere esfriar primeiro, para não correr o risco de quebrar.\n" +
                        "\n" +
                        "Calda de chocolate durinha:\n" +
                        "\n" +
                        "    Fique bem atento ao modo de preparo e siga tudo direitinho para garantir que a sua calda de chocolate sairá perfeita, crocante e craquelada.\n" +
                        "    Em uma panela, adicione a manteiga em fogo baixo, depois o açúcar, o achocolatado e então o leite.\n" +
                        "    Misture tudo muito bem e deixe cozinhar em fogo bem baixo até o ponto de brigadeiro. É importante que seja em fogo baixo!\n" +
                        "    Assim que der o ponto, despeje a calda por cima do bolo rapidamente, espalhando com agilidade para cobrir todo o bolo.\n" +
                        "    Essa calda de chocolate endurece muito rápido, então não pode deixar esfriar!\n" +
                        "    Depois que tiver espalhado pelo bolo, é só levar para a geladeira para gelar por 1 hora antes de comer se preferir geladinho. Do contrário, é só devorar!\n"));
            }
        };
    }
}
