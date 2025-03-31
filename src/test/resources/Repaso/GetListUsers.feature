Feature: Dado que necesitan listar usuarios por pagina
And quiere validar la descripción de un dato de un usuario

  @RepasoGet
  Scenario Outline: Listar usuarios por pagina y validar su nombre
    Given que el usuario tiene conexión a la api
    When envía el <id> de la página de usuarios a listar
    Then recibe el estatus <respuesta>
    And en el response se valida el <nombre> del usuario

    Examples:
      | id | respuesta | nombre  |
      | 2  | 200       | Michael |
