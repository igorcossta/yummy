package io.igorcossta;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeRepository;
import io.igorcossta.user.Role;
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
            User user = userRepository.save(new User("a@a.com", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq", Role.USER));
            User user2 = userRepository.save(new User("b@b.com", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq", Role.ADMIN));

            for (int i = 0; i < 1; i++) {
                recipeRepository.save(new Recipe(user, "Bolo de Cenoura " + user.getUsername(), "Bolo de Cenoura Description", "1/2 xícara (chá) de óleo:2 cenouras médias:4 ovos:2 xícaras (chá) de açúcar:2 e 1/2 xícaras (chá) de farinha de trigo:1 colher (sopa) de fermento em pó", "Comece fazendo a massa do bolo. Para isso, escolha bem as cenouras e higienizá-las adequadamente.:Em seguida, corte-as em pedaços médios e leve para o liquidificador junto com o açúcar, os ovos e o óleo. Bata bem até que toda a cenoura tenha sido triturada, pode deixar batendo por uns 5 minutos em velocidade máxima.:Quando tiver um creme homogêneo, transfira para uma tigela e acrescente a farinha de trigo peneirada aos poucos. Misture com ajuda de um batedor de arames, apenas o suficiente para incorporar a farinha.:Finalize adicionando o fermento em pó e misturando bem.:Despeje a massa em uma forma untada e enfarinhada e leve para assar em forno preaquecido a 180º C por 35 a 40 minutos. Faça o teste do palito para confirmar o ponto.:Se for desenformar o bolo, espere esfriar primeiro, para não correr o risco de quebrar.", 40, 8));
            }
            for (int i = 0; i < 1; i++) {
                recipeRepository.save(new Recipe(user2, "Bolo de Cenoura " + user2.getUsername(), "Bolo de Cenourinha Description", "1/2 xícara (chá) de óleo:2 cenouras médias:4 ovos:2 xícaras (chá) de açúcar:2 e 1/2 xícaras (chá) de farinha de trigo:1 colher (sopa) de fermento em pó", "Comece fazendo a massa do bolo. Para isso, escolha bem as cenouras e higienizá-las adequadamente.:Em seguida, corte-as em pedaços médios e leve para o liquidificador junto com o açúcar, os ovos e o óleo. Bata bem até que toda a cenoura tenha sido triturada, pode deixar batendo por uns 5 minutos em velocidade máxima.:Quando tiver um creme homogêneo, transfira para uma tigela e acrescente a farinha de trigo peneirada aos poucos. Misture com ajuda de um batedor de arames, apenas o suficiente para incorporar a farinha.:Finalize adicionando o fermento em pó e misturando bem.:Despeje a massa em uma forma untada e enfarinhada e leve para assar em forno preaquecido a 180º C por 35 a 40 minutos. Faça o teste do palito para confirmar o ponto.:Se for desenformar o bolo, espere esfriar primeiro, para não correr o risco de quebrar.", 40, 8));
            }
        };
    }
}
