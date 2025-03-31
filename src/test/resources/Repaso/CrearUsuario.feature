Feature: repaso de Post

  @Albert
  Scenario Outline: Repaso
    Given que el usuario ya tiene acceso a la API
    When crear el usuario
    Then responde con el status <numStatus>

    Examples:
      | numStatus |
      | 201       |