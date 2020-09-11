package server;

import domain.controllers.UsuarioController;
import domain.controllers.UsuarioRestController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() {
        UsuarioController usuarioController = new UsuarioController();
        UsuarioRestController usuarioRestController = new UsuarioRestController();

        //Spark.get("/hola", ((request, response) -> "Hola Eze"));

        //Spark.get("/hola", ((request, response) -> "Hola " + request.queryParams("nombre")));

        //Spark.get("/hola/:nombre", ((request, response) -> "Hola " + request.params("nombre")));

        //Spark.get("/saludo", usuarioController::saludar, engine);

        Spark.get("/usuario", usuarioController::crear,engine);

        Spark.get("/usuario/:id", usuarioController::mostrar, engine);

        Spark.post("/usuario/:id",usuarioController::modificar);

        Spark.post("/usuario",usuarioController::guardar);

        Spark.get("/usuarios", usuarioController::mostrarTodos,engine);

        Spark.delete("/usuario/:id", usuarioController::eliminar);

        Spark.get("/api/usuario/:id", usuarioRestController::mostrar);

        Spark.before("/api/*", (request, response) -> response.type("application/json"));


    }
}
