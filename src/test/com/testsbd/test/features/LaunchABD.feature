@ABDLaunch
Feature: Verify for errors when a boarding pass is scanned

  @LaunchABD
  Scenario: Launch the ABD
    Given I set up the correct launch link
      | Airline | Port | Terminal |
      | JQ      | OOL  | T1       |
    And I initialize the Configurations with Unit Name "KIOSK_AT"