Feature: This feture is responsible for checking the flight closed error message

  @Errors @NoBaggageAllowance
  Scenario: No Baggage Allowance in the booking
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1ABD/ABD             EHDMPYM OOLSYDJQ 0411 158 001A0001 100     KK |
    Then I validate the outcomes
      | Error Message                    |
      | Booking has no baggage allowance |