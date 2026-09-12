```mermaid
classDiagram
    Direction LR
    Library --> Database
    Library --> Media
    Database --> Media
    Media --* Book
    Media --* Film
    Media --* Music
    
    class Library {

    }

    class Database {

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