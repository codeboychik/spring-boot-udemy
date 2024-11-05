<h1>User API Test Task</h1>

Zadáním je implementace rozhrání, umožňující CRUD operace nad uživateli.

Prvním krokem bude volba a konfigurace databáze. Vzhledem k osobním zkůšenostem a k tomu,
že H2 se obvykle nedoporučuje jako zdroj dat v produkci kvůli chybějicím funkcím a možným bugům,
byla zvolena varianta B.

Pro případné nastavení oprávnění bylo vytvořeno schema <tt>api</tt>. Script s potřebnými
tabulkami a dalšími databázovými objekty je v <tt>resources/db-schema.sql</tt>.

Dále byla vytvořena vrstva business logiky, která provádí základní operace nad uživateli: entity User a Address,
Repository, Service a Controller.

Jako první operace byla naimplementována defaultni <tt>GET /api/users</tt>, vrácející všechny uživatele. Zatím vrací bez stránkování,
nápad na vylepšení. Dále byly přidány endpointy <tt>POST /add</tt> na přidání a <tt>PUT /{userId}/edit</tt>
na editaci zákládních údajů uživatele. Na práci s adresami byly implementovány samostatné 
endpointy <tt>POST /{userId}/newAddress </tt> (nová adresa do seznamu) a <tt>PUT /{userId}/editAddress/{addressId}</tt> (editace existující) a 
<tt>DELETE /{userId}/deleteAddress/{addressId}</tt>. Jako poslední operace byla implementována <tt>DELETE /{userId}</tt>, která kaskádově maže 
uživatele a jeho adresy.


<h2>Nápady na výlepšení</h2>
<ul>
    <li>Stránkování defaultní routy /api/users</li>
    <li>Testování Docker-In-Docker</li>
    <li>Swagger pro přehlednost endpointů</li>
    <li>Podrobnějí specifikovat chyby v rámci exception handling</li>
    <li>Automatizovat rebuild docker containerů na zmeny v projektu</li>
</ul>


<h2>Návod na spuštění</h2>
<ul>
    <li>Naklonovat repozitář a přepnout na větev <tt>feature</tt></li>
    <li>Spustit <tt>docker-compose up --build</tt></li>
</ul>

Databázové schema by se mělo naimportovat automaticky. Pokud by to tak nestalo, 
provést <tt>psql -U pg -h localhost -p 5434 -d postgres -f ./src/main/resources/db-schema.sql</tt>.
Heslo naleznete v <tt>docker-compose.yml</tt>.
