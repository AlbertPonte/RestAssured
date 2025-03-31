Feature: Necesitamos obtener la lista de usuarios

  Scenario Outline: Obtener lista de usuario
    Given Se conecta a la API users
    When obtiene la lista <Id>
    Then se obtiene el status <Status>
    * validamos los 6 items de la lista
    * verificamos <usuario> en la lista

    Examples:
      | Id | Status | usuario |
      | 2  | 200    | Michael |