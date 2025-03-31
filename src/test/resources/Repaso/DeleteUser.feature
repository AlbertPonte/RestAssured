Feature: Dado que quieren eliminar usuarios
And validar el estado 201

  @RepasoDelete
  Scenario Outline: Elimar usuario por Id
    Given que el usuario tiene acceso a la api
    When envía el <Id> del usuario a eliminar
    Then el response envía el <statusCode>

    Examples:
      | Id | statusCode |
      | 2  | 204        |
