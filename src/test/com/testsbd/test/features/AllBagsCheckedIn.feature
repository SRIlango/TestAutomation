Feature: Verify for errors when a boarding pass is scanned

  @Errors @AllBagsCheckedIn
  Scenario: All Bags Checked In
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1TEST/RAW            EXM1Y5R OOLMELJQ 0445 158 001A0001 100     KK |
    Then I validate the outcomes
      | Error Message       |
      | All bags checked-in |