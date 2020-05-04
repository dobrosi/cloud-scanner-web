# cloud-scanner-web

## Projekt célja
A cloud-scanner projekt célja egy olyan rendszer készítése, amivel felhasználók vonalkódokat menthetnek le, valamint küldhetnek át. Egy egyszerű felhasználói regisztráció után böngésző, vagy mobiltelefon segítségével vonalkódokat lehet rögzíteni. A program segítségével lehetőség lesz későbbiekben, hogy a telefonunkat mint egy vonalkód olvasóként használva a számítógépünkre, vagy egyéb távoli eszközre vonalkódot küldjünk. Az összes leolvasott vonalkód a "felhőbe" kerül mentésre, amelyek későbbiekben visszakereshetőek, valamint törölhetőek.

## Szempontok
Html/Javascript alapú keretrendszer
Spring
PostgresSql
Futtató környezet Docker (docker hub-on elérehtő imagekkel vagy ezeken alapuló saját image build fájlokkal)
 Az alkalmazás fejlesztéséhez használj git repot (ehhez szükség szerint tudunk biztosítani git-et, de local is lehet amit majd elküldesz). A commitok és mergek tükrözzék egy valós fejlesztés mintáját.

Az alkalmazás nem szükséges, hogy jelentős üzleti logikát tartalmazzon. A felsorolt pár alap funkciókat tartalmazza:
·       login
·       adatok listázása
·       adatok módosítása
·       validálás
·       mentés
·       hibakezelés
