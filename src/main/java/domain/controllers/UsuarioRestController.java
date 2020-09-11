package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.daos.DAOHibernate;
import spark.Request;
import spark.Response;

public class UsuarioRestController {
    private Repositorio<Usuario> repoUsuarios;

    public UsuarioRestController() {
        this.repoUsuarios = new Repositorio<>(new DAOHibernate<>(Usuario.class));
    }

//    public Response login(Request request, Response response){
//        //if usuario es valido
//        request.session(true);
//        request.session().attribute("id",1);
//    }

    public String mostrar(Request request, Response response){
        Integer idUsuario = new Integer(request.params("id"));
        Usuario usuario = this.repoUsuarios.buscar(idUsuario);

        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(usuario);

//        response.type("application/json");

        return jsonUsuario;
    }

}
