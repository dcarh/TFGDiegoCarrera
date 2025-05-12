# Aplicación

La autenticación a nivel de aplicación generalmente se consideraría la forma predeterminada de autenticarse en la API. La Versión 3 está controlada por uno de los dos parámetros de consulta, **api_key**, o mediante el uso de tu token de acceso como un token **Bearer**.

### Clave de API
Una vez que se te haya asignado una clave, un ejemplo de solicitud basada en clave de API se ve así:

    Ejemplo de cURL

    curl --request GET \
        --url 'https://api.themoviedb.org/3/movie/11?api_key=API_KEY'

### Token Bearer
Otra opción para autenticarte es utilizando tu token de acceso. Si vas a tu página de cuenta, en la sección de configuración de la API, verás un nuevo token llamado "API Read Access Token". Se espera que este token se envíe como una cabecera **Authorization**. Un ejemplo sencillo de cURL usando este método se ve así:

    Ejemplo de cURL

    curl --request GET \
        --url 'https://api.themoviedb.org/3/movie/11' \
        --header 'Authorization: Bearer ACCESS_TOKEN'
Usar el token Bearer tiene la ventaja adicional de ser un único proceso de autenticación que puedes utilizar en ambos métodos v3 y v4. Ninguno de ellos proporciona más o menos acceso, por lo que la elección entre ellos depende completamente de ti.