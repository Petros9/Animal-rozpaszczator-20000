# Animal-rozpaszczator-20000
Autor: Piotr Świderski (305435) grupa:  środa: 9:35 - 11:05

<img width="590" alt="zdjęcie" src="https://user-images.githubusercontent.com/58508596/71313306-ececd780-2436-11ea-8ce5-80a2b8821f98.png">


Za co sa odpowiedzialne poszczególne klasy:

Animal:
klasa odpwiedzialna za przechowywanie informacji o zwierzakach. Są w niej metody definiujące, jak zwierzak ma się poruszać i jak rozmnarzać.

AnimalAtOnePosition:
klasa, której celem jest utworzenie listy zwierzaków, które znajdują się na danej pozycji (deklarjemy ją w konstruktorze). Przy wstawianiu zwierzaka do listy jest on od razu sortowany (tak, by energia zwierzaków z nowej listy malała).

Genome:
klasa zawierająca genotyp danego zwierzęcia. Jej celem jest określenie losowego kierunku ruchu każdego zwierzaka oraz stworzenie nowego genotypu dla dziecka, opartego na genotypie rodziców. W związku z tym, że tablica genów nie jest nigdy sortowana, pozwolilem sobie pierwsze 8 miejsc każdego genotypu zarezerwować dla każdeo genu.

Grass:
klasa definiująca obiekt trawy w świecie gry.

Jungle:
klasa definiująca położenie dżungli na mapie. Jeżeli ktoś poda rozmiar dżungli, którego odwrotność będzie większa od 1, to program uznaje, że dżungla ma zajmować całą mapę (traktuje to jak 1), analogicznie dla mniejszego od 0.

MapDirection:
typ wyliczniowy, który określa orientacje zwierzęcia. Pomaga przy określaniu ruchu zwierzęcia.

MapElement:
interfetj mający na celu umożliwiene wspólnego przechowywania zwierząt i traw w mapie.
Planet:

ProgammeWindow:
klasa pośrednicząca w wyświetlaniu wizualizacji mapy. Znajduje się w niej renderer oraz JLabele, które informują użytkownika o danych statystycznych mapy (np. średnia energia, liczba zwierząt, liczba traw, itd.)

Renderer:
specjalna klasa, która wykonuje malowanie (wizualizację) planety w sekcji ProgrammeWindow

StartWindow:
klasa, która zawiera w sobie metodę main. Po uruchomieniu pojawia się okno, w którym możemy wpisać parametry definiujące mapę. Jeżeli użytkownik się pomyli w deklarowaniu parametrów (np. poda literę), to program jest uruchamiany z domyślnymi wartościami, dla danego parametru.

Vector2d:
klasa, która określa położenie obiektu (zwierzęcia, trawy) na mapie.

