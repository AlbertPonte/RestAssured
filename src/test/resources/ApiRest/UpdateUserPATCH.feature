Feature: Solo se podrá actualizar un descripción del usuario por el metodo PATCH

  @UpdatePatch
  Scenario Outline: Actualizar algún datos descriptivo del usuario
    Given tengo conexion con el api usuario
    When actualice una <name>
    Then obtengo el <statusCode>

    Examples:
      | name   | statusCode |
      | Albert | 200        |

