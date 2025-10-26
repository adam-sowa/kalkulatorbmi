Kalkulator BMI

1. Opis projektu:
  - Aplikacja „Kalkulator BMI” została stworzona w języku Java przy użyciu Android Studio. Jej celem jest obliczanie wskaźnika masy ciała (Body Mass Index – BMI) na podstawie wagi i wzrostu użytkownika.

2. Funkcjonalności:
  - Obliczanie BMI – użytkownik wprowadza wagę (kg) i wzrost (cm), a aplikacja wyświetla wynik.
  - Wybór płci – możliwość zaznaczenia płci (Mężczyzna / Kobieta), co wpływa na opis wyniku.
  - Kolorowy wynik – wynik BMI jest wyróżniony kolorem:
      * Niebieski – niedowaga
      * Zielony – waga prawidłowa
      * Pomarańczowy – nadwaga
      * Czerwony – otyłość
  - Zapis ostatniego wyniku – wynik BMI można zapisać i później odczytać po ponownym uruchomieniu aplikacji.
  - Obliczanie idealnej wagi – aplikacja oblicza przedział wagowy odpowiadający prawidłowemu BMI (18.5–24.9) dla podanego wzrostu.

3. Wykorzystane technologie i komponenty:
  - Język programowania: Java
  - Środowisko: Android Studio
  - UI: EditText, TextView, Button, ImageView, RadioGroup, ScrollView
  - Logika: obliczenia matematyczne BMI
  - Zapisywanie danych: SharedPreferences
  - Kolory i style: Color API
