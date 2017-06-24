Feature: This feture is responsible for checking the flight closed error message

  @Errors @FlightClosed
  Scenario: Your Flight is Closed
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1OOL/SYD             EBCPF3Z OOLSYDJQ 0407 158 001A0001 100     KK |
    Then I validate the outcomes
      | Error Message          |
      | Your flights is closed |