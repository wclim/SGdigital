Feature: User to be able to search on the open vacancies in the careers page
  So that they can quickly find the open vacancies in Singapore that they are interested in

  Scenario Outline: User searches for vacancies
    Given Browser is opened
    And User is at "https://www.sgdigital.com/careers"
    And search bar is loaded
    When user enters "<Search Term>" in the search menu 
    And press enter
    When user clicks on location filter
    Then a dropdown list is displayed
    When User selects "<Location>" from the location dropdown
    Then entries with "<Search Result>" and "<Location Result>" are displayed

	Examples:
	  | Search Term        | Location | Search result	 | Location Result|
	  | Software Engineer  | 		  | Software Engineer| All		      |
	  |                    | Singapore| All				 | Singapore	  |
	  | Software Engineer  | Singapore| Software Engineer| Singapore      |
	  | Software #Engineer#| 		  | Software Engineer| All		      |