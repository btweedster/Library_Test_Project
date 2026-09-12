```mermaid
classDiagram
    Direction LR
    Library --> Database
    Database --> Media
    Database --> Patron
    Patron <--> Media
    Media --* Book
    Media --* Film
    Media --* Music
    
    class Library {

    }

    class Database {

    }

    class Patron {

    }

    class Media {

    }

    class Book {

    }

    class Film {

    }

    class Music {

    }
```