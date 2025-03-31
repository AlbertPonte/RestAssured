Feature: Registrar usuario con el método POST

  @PostUser
  Scenario Outline: Registrar usuario <Test>
    Given usuario ya tiene acceso a la api
    When envía los datos del nuevo usuario a registrar
    Then la api responde el estado <cod>

    Examples:
      | Test     | cod |
      | Prueba 1 | 200 |
