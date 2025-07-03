# URLs de ejemplo para endpoints de usuarios

- http://localhost:8080/users   (GET)

- http://localhost:8080/users?sort_by=most_popular   (GET)

- http://localhost:8080/user/2   (GET)

- http://localhost:8080/user/3/profile   (GET)

- http://localhost:8080/user/7/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "username": "Diego Carrera",
            "password": "89nkj43nhj_9j22az",
            "email": "d.carrerah.2019@alumnos.urjc.es",
            "biography": "Hey there! I'm user Diego Carrera",
            "location": "Fuenlabrada, Madrid, España"
        }

- http://localhost:8080/user/-29/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "username": "Diego Carrera",
            "password": "89nkj43nhj_9j22az",
            "email": "d.carrerah.2019@alumnos.urjc.es",
            "biography": "Hey there! I'm user Diego Carrera",
            "location": "Fuenlabrada, Madrid, España"
        
        }

- http://localhost:8080/user/2/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "username": "Diego Carrera",
            "password": "89nkj43nhj_9j22az",
            "email": "d.carrerah.2019@alumnos.urjc.es",
            "biography": "Hey there! I'm user Diego Carrera",
            "location": "Fuenlabrada, Madrid, España"
        
        }

- http://localhost:8080/user/7/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "username": "User2",
            "password": "89nkj43nhj_9j22az",
            "email": "d.carrerah.2019@alumnos.urjc.es",
            "biography": "Hey there! I'm user Diego Carrera",
            "location": "Fuenlabrada, Madrid, España"
        
        }

- http://localhost:8080/user/7/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "username": "Diego Carrera",
            "password": "89nkj43nhj_9j22az",
            "email": "user4@gmail.com",
            "biography": "Hey there! I'm user Diego Carrera",
            "location": "Fuenlabrada, Madrid, España"
        
        }

- http://localhost:8080/user/3/edit   (PUT, con el siguiente JSON como cuerpo de la petición)

        {
        "username": "Diego Carrera",
        "password": "89nkj43nhj_9j22az",
        "email": "nombredecuenta@gmail.com",
        "biography": "Hey there! I'm user Diego Carrera",
        "location": "Fuenlabrada, Madrid, España"
        }

- http://localhost:8080/user/3/delete   (DELETE)