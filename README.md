# api-pricing-service

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas

> Instalacion de commit lint

````bash
chmod +x .git/hooks/commit-msg
````

> Configuracion de la de lint

````js
git commit -m "actualizacion"
# ❌ será rechazado

git commit -m "fix(api): corrige error de zona horaria"
# ✅ será aceptado
````
