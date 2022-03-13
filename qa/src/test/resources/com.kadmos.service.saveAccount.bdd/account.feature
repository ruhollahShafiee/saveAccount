Feature: account functionalities
  This feature contains a list of functionalities related to saving account

  Scenario: get a amount by accountNumber

    Given the collection of accounts:
      | accountNumber                        | amount   |
      | c81d4e2e-bcf2-11e6-869b-7df92533d2db | 20.3     |

    When accountNumber c81d4e2e-bcf2-11e6-869b-7df92533d2db is passed in to retrieve the balance

    Then The account detail is retrieved
      | accountNumber                        | amount   |
      | c81d4e2e-bcf2-11e6-869b-7df92533d2db | 20.3     |
