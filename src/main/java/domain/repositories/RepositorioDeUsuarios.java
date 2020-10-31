package domain.repositories;

import domain.entities.Usuario;
import domain.repositories.daos.BusquedaCondicional;
import domain.repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioDeUsuarios extends Repositorio<Usuario>{

    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe(String username,String password){
        return buscarUsuario(username,password) != null;
    }

    public Usuario buscarUsuario(String username, String password){
        return this.dao.buscar(condicionUsuarioYContrasenia(username,password));
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String username,String password){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);
        //SELECT U.* FROM usuario

        Predicate condicionUsername = criteriaBuilder.equal(condicionRaiz.get("username"), username);
        //U.username = ''

        Predicate condicionPassword = criteriaBuilder.equal(condicionRaiz.get("password"), password);
        //U.password = ''

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionUsername,condicionPassword);
        //U.username = '' AND U.password = ''

        usuarioQuery.where(condicionExisteUsuario);
        //SELECT U.* FROM usuario u where U.username = '' AND U.password = ''

        return new BusquedaCondicional(usuarioQuery,null);
    }

}
