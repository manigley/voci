# voci
> Webapp um den Wortschatz in einer Fremdsprache zu trainieren. Bietet die Möglichkeit falsche Übersetzungen erneut abzufragen. Die Reihenfolge der Übersetzungen ist zufällig. 

## Installation

#### Anforderungen
- Java 8 (JDK)
- Apache Maven
- Web Browser
#### Builden mit Maven
```mvn clean install```  
Embedded Tomcat (für Entwickler): ```mvn clean install tomcat7:run ```

## Starten
Wichtig ist das die Applikation immer aus dem selben Verzeichnis aus gestartet wird.  
Alle Übersetzungen werden im Verzeichnis ```./database``` abgelegt.

```java -jar target/voci\#\#1.0.jar -httpPort=7070```

#### Benutzen
Der Benutzer wird beim ersten Login erstellt
  - Login [http://localhost:7070/voci](http://localhost:7070/voci)


## Beschreibung
- Login 
  - der Benutzer wird beim ersten Login erstellt
- Sprachen
  - Hier können die Sprachen erfasst werden (Deutsch, Französisch, Englisch usw.)
- Kapitel
  - Ein Kapitel beinhaltet mehrere Übersetzungen
- Übersetzungen
  - Steht in Beziehung zu einem Kapitel
  - Steht in Beziehung mit einer Mutter- und einer Fremdsprache
  - Für jede der Sprachen wird ein Text (Wort, Satz usw.) erfasst
  - Bereits erfasste Texte werden mit Auto complete vervollständigt)
- Lernen
  - Hier können Lernsessions erstellt werden.
  - Es wird nur von Muttersprache in die Fremdsprache gelernt
  - Eine Lernsession beinhaltet eines oder mehrere Kapitel
  - Die Übersetzungen werden zufällig abgefragt.
  - Wass willst du lernen
    - LERN_ALL alle Übersetzungen der LernSession
    - LERN_FAILED_AND_NOT_LERNED alle zuvor falsch gemachte und noch nicht geübte Übersetzungen der LernSession
    - LERN_NOT_LERNED alle noch nicht geübte Übersetzungen der LernSession
  - Buttons beim Lernen
    - prüfen: Zeigt ob richtig oder falsch Übersetzt
    - überspringen: Wird übersprungen und wird als falsch vermerkt
    - lösung: Zeigt die korrekte Übersetzung, muss zuvor mindestens einmal falsch beantwortet sein.
- Logout
## to do
- Multiuser
- Bessere Datstellung der Grids
  - mit Suchfunktion
  - Nicht nut toString() Methode ausgeben
- Lernen  
  - Fremdsrache -> Muttersprache
- i18n
