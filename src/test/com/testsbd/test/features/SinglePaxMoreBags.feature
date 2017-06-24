Feature: Verify for errors when a boarding pass is scanned

  @Errors @MissingBagTag
  Scenario: Bag Tag missing
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

  @SinglePax-MoreThanOneBag
  Scenario: Single Passenger booking with more than one Bag
    Given I am on Boarding Pass scan screen
    And I activate the APP
    When I scan the Boarding pass
      | Boarding Pass Stream                                                      |
      | M1OOOO/OOO            EGLDRJR OOLMELJQ 0445 158 001D0004 100     KK       |
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002402 |       5000 |
    And I Check in the Bag
    And I check for Place Next Bag Screen
    And I enter the Bag Tag No and Weight
      | Bag Tag No | Bag Weight |
      | 9041002403 |       6500 |
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

