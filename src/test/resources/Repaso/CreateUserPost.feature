Feature: Dado que se necesita crear usuario
  And validar el estado de respuesta
  And el nombre

  @RepasoPost
  Scenario Outline:
    Given que el usuario tiene acceos a la api crear usuario
    When envía los datos por archivo json
    Then la api responde con el <statusCode>
    And valida el <nombre> del nuevo usuario en el response

    Examples:
      | statusCode | nombre  |
      | 201        | Alber11 |
