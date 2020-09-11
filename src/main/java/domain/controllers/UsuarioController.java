package domain.controllers;

import domain.entities.Rol;
import domain.entities.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.daos.DAOHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {
    private Repositorio<Usuario> repoUsuarios;
    private Repositorio<Rol> repoRoles;

    public UsuarioController() {
        this.repoUsuarios = new Repositorio<>(new DAOHibernate<>(Usuario.class));
    }

    public ModelAndView mostrar(Request request, Response response) {
        Integer idUsuarioBuscado = new Integer(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuarios.buscar(idUsuarioBuscado);

        Repositorio<Rol> repoRoles = new Repositorio<>(new DAOHibernate<>(Rol.class));
        List<Rol> roles = repoRoles.buscarTodos();

        Map<String,Object> parametros = new HashMap<>();
        parametros.put("usuario", usuarioBuscado);
        parametros.put("roles", roles);

        return new ModelAndView(parametros, "usuario.hbs");
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Usuario> usuarios = this.repoUsuarios.buscarTodos();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarios", usuarios);

        return new ModelAndView(parametros, "usuarios.hbs");

    }


    public ModelAndView saludar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("persona", request.queryParams("nombre"));
        return new ModelAndView(parametros, "saludo.hbs");
    }

    public ModelAndView crear(Request request, Response response) {

        Repositorio<Rol> repoRoles = new Repositorio<>(new DAOHibernate<>(Rol.class));
        List<Rol> roles = repoRoles.buscarTodos();

        Map<String,Object> parametros = new HashMap<>();
        parametros.put("roles", roles);

        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response eliminar(Request request, Response response) {

        Integer idUsuarioBuscado = new Integer(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuarios.buscar(idUsuarioBuscado);

        this.repoUsuarios.eliminar(usuarioBuscado);

        return response;
    }


    public Response modificar(Request request, Response response) {
        Integer idUsuario = new Integer(request.params("id"));
        Usuario usuarioAModificar = repoUsuarios.buscar(idUsuario);

        asignarAtributosA(usuarioAModificar,request);

        this.repoUsuarios.modificar(usuarioAModificar);

        response.redirect("/usuarios");

        return response;
    }

    public Response guardar(Request request, Response response) {
        Usuario usuario = new Usuario();

        asignarAtributosA(usuario,request);

        this.repoUsuarios.agregar(usuario);

        response.redirect("/usuarios");

        return response;
    }



    private void asignarAtributosA(Usuario usuario, Request request){
        usuario.setNombre(request.queryParams("nombre"));
        usuario.setApellido(request.queryParams("apellido"));
        usuario.setEmail(request.queryParams("email"));
        usuario.setUsername(request.queryParams("nombreDeUsuario"));
        usuario.setPassword(request.queryParams("password"));

        if(request.queryParams("telefono") != null && !request.queryParams("telefono").isEmpty()){
            usuario.setTelefono(new Integer(request.queryParams("telefono")));
        }



    }



}
