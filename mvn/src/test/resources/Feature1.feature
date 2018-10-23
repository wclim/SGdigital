Feature: Users to be able to quickly navigate to open vacancies page
  So they can quickly view the open vancancies in SGDigital

  Scenario: User navigate to career vacancies page to view open vacancies
    Given Browser is opened
    When User navigates to "https://www.sgdigital.com/"
    Then Browser navigates to "SG Digital" webpage
    When User clicks on "Careers" menu tab
    Then Browser will navigate to careers section and display list of available vancancies
