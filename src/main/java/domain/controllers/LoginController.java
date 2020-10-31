package domain.controllers;

import com.google.common.hash.Hashing;
import config.Inyector;
import domain.entities.Usuario;
import domain.repositories.RepositorioDeUsuarios;
import domain.repositories.daos.DAOHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.nio.charset.StandardCharsets;

public class LoginController {


    public ModelAndView inicio(Request request, Response response){
        return new ModelAndView(null, "login.hbs");
    }

    public Response login(Request request, Response response) {
        RepositorioDeUsuarios repoUsuarios = new RepositorioDeUsuarios(new DAOHibernate<>(Usuario.class));

        String username = request.queryParams("nombreDeUsuario");
        String password = request.queryParams("password");
        password = Inyector.getInstance().estrategiaDeHashing().generarHash(password);

        if(repoUsuarios.existe(username,password)){
            Usuario usuario = repoUsuarios.buscarUsuario(username,password);

            request.session(true);
            request.session().attribute("id",usuario.getId());

            response.redirect("/usuarios");
        }else{
            response.redirect("/");
        }
        //    public Response login(Request request, Response response){
//        //if usuario es valido
//        request.session(true);
//        request.session().attribute("id",1);
//    }

        return response;


    }

    public Response logout(Request request, Response response) {

        request.session().invalidate();
        response.redirect("/");

        return response;

    }
}
