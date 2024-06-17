Feature: HomePage of GlobalLogic Feature
  # We are checking Polish verion of the Home Page of Globallogic

  Scenario: Verify HomePage of GlobalLogic appears and check available regions
    Given User opens HomePage
    When User clicks on language selector
    Then User can see all the available regions and languages
  | Global / English    |
  | Croatia / Croatian   |
  | EMEA / English    |
  | Germany / Deutsch    |
  | India / English    |
  | Israel / English    |
  | Japan / 日本語      |
  | Latam / Español    |
  | Nordic / English    |
  | Poland / Polski     |
  | Romania / English    |
  | Slovakia / Slovensko  |
  | Ukraine / українська |
  | United Kingdom & Ireland / English |


Scenario: Verify the footer of the Home Page
  Given User opens HomePage
  When User scrolls to the bottom of the page
  Then User can see the footer of the page
  And User verifies social links are present
  | LinkedIn  |
  | Twitter   |
  | Facebook  |
  | YouTube   |
  | Instagram |
  And User verifies redirection links are present
  | Oferta                   |
  | Projekty                 |
  | Nasz punkt widzenia      |
  | Kariera                  |
  | O nas                    |
  | Prywatność               |
  | Compliance & Disclosures |
  | Strategia podatkowa      |
  | Tax strategy             |
  | Kontakt                  |
 And User verifies copyright tag: "2024 Copyright GlobalLogic Inc. Wszystkie prawa zastrzeżone"
 When User clicks on "Go to top" button
 Then User is on top of the Home Page

Scenario: Verify hover over navigation on main nav bar is working and buttons are enabled
  Given User opens HomePage
  Then User hovers over "Usługi" on navbar and checks if hovered navbar buttons from "buttons1_headers.csv" and their buttons from file "buttons1.csv" are enabled

