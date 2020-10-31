package domain.repositories.daos;

import javax.persistence.criteria.CriteriaQuery;
import java.util.function.Predicate;

public class BusquedaCondicional {

    private CriteriaQuery condicionCriterio;    //Hibernate
    private Predicate condicionPredicado;       //Colecciones en memoria -> Lambdas

    public BusquedaCondicional(CriteriaQuery condicionCriterio, Predicate condicionPredicado) {
        this.condicionCriterio = condicionCriterio;
        this.condicionPredicado = condicionPredicado;
    }

    public CriteriaQuery getCondicionCriterio() {
        return condicionCriterio;
    }

    public void setCondicionCriterio(CriteriaQuery condicionCriterio) {
        this.condicionCriterio = condicionCriterio;
    }

    public Predicate getCondicionPredicado() {
        return condicionPredicado;
    }

    public void setCondicionPredicado(Predicate condicionPredicado) {
        this.condicionPredicado = condicionPredicado;
    }
}
