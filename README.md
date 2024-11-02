<h1>User API Test Task</h1>

Zadáním je implementace rozhrání, umožňující CRUD operace nad uživateli.

Prvním krokem bude volba a konfigurace databáze. Vzhledem k osobním zkůšenostem a k tomu,
že H2 se obvykle nedoporučuje jako zdroj dat v produkci kvůli chybějicím funkcím a možným bugům,
byla zvolena varianta B.

Pro případné nastavení oprávnění bylo vytvořeno schema <tt>api</tt>. Script s potřebnými
tabulkami a dalšími databázovými objekty je v <tt>resources/db-schema.sql</tt>.