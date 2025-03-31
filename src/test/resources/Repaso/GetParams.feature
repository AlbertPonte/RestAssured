Feature: Get con parametros de entrada

  @Getparameters
  Scenario Outline: Consultar Lista de usarios
    Given el usuario ha tenido acceso a la api
    When solicita la pagina <numPagina>
    Then se muestra el estatus <numStatus>

    Examples:

      | numPagina | numStatus |
      | 2         | 200       |