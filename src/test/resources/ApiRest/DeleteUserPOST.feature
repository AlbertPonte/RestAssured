Feature: Eliminar registro mediante el id del usuario
@Delete
  Scenario Outline: Eliminar usuario
    Given tenemos acceso a la api delete
    When ingreso el <IdUsuario> a eliminar
    Then obtengo un status cod <statusCode>

    Examples:
      | IdUsuario | statusCode |
      | 1         | 204        |
