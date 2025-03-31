Feature: I go to List a user

  @ListSingle
  Scenario Outline: Listar usuario por ID
    Given he accedido a la api single user
    When envio el <Id> del usario
    Then se muestra el <nombre> de usuario

    Examples:
      | Id | nombre |
      | 2  | Janet  |

