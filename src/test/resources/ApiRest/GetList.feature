Feature: Listar usuarios con List<>

  @GetListUser
  Scenario Outline: Listar usuarios Get
    Given que he accedido a api List Users
    When solicito lista por <pagina>
    Then el status es <estadoResponse>
    And la lista de usuarios

    Examples:
      | pagina | estadoResponse |
      | 2      | 200            |
