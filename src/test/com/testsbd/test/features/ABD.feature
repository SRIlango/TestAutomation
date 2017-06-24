Feature: Verify for errors when a boarding pass is scanned

  @Errors @SSBDUnavailableForYourFlight
  Scenario: Self-service bag drop is unavailable for your flight
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                                       |
      | M1QWE/QWE             EBYWNJA MELSYDTT 0264 149 010B0009 11E>3180KK    BTT              00 |
    Then I validate the outcomes
      | Error Message                                        |
      | Self-service bag drop is unavailable for your flight |



  @SinglePassenger-OneBag
  Scenario: Check in Bags
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1TEST/RAW            EXM1Y5R OOLMELJQ 0445 158 001A0001 100     KK |
    Then I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002397 |       6000 |
    Then I Check in the Bag
    And Check whether bag is checked-in successfully



  @Errors @MissingBagTag
  Scenario: Bag Tag is missing
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1TEST/TEST           EJG2GPT OOLSYDJQ 0415 159 001A0001 100     KK |
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      |          0 |       5500 |
    Then I try to check in the Bag
    Then I validate the outcomes
      | Error Message                            |
      | Sorry we are unable to read your bag tag |
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002413 |       5500 |
    And I Click on Continue Button
    Then I Check in the Bag
    And Check whether bag is checked-in successfully

  @Errors @AlreadyCheckedIn
  Scenario: Bag Tag already Checked In
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1OOOO/OOO            EGLDRJR OOLMELJQ 0445 158 001D0004 100     KK |
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002404 |       8500 |
    Then I try to check in the Bag
    Then I validate the outcomes
      | Error Message                  |
      | Your bag is already checked-in |

  @Errors @HeavyTBagTag
  Scenario: Heavy Bag Checkin
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                      |
      | M1TEST/TEST           EJG2GPT OOLSYDJQ 0415 159 001A0001 100     KK       |
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002412 |      25500 |
    Then I validate the outcomes
      | Error Message           |
      | Please Attach Heavy Tag |
    And I Click on Continue Button
    Then I Check in the Bag
    And Check whether bag is checked-in successfully

   @AgentScan @VerifyCheckedInBags
     Scenario: Verify the last 5 check in bags using Agent card scan
     Given: I am on Boarding Pass scan screen
     And I activate the APP
     Then I scan Agent card and enter Password
      |  Agent card Barcode  | Password |
      | Vedaleon*JQ_VBD_TEST |     1234 |
     Then I verify the last checked in bags

  @Errors @ExceededAllowance
    Scenario: Exceeded Baggage Allowance
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                |
      | M1TEST/TEST           EJG2GPT OOLSYDJQ 0415 159 001A0001 100     KK |
    Then I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002397 |       7000 |
    Then I validate the outcomes
      | Error Message                               |
      | You have exceeded your baggage allowance by |

  @Quit
    Scenario: Close All