# URLs de ejemplo para endpoints de listas de contenidos

- http://localhost:8080/media_lists   (GET)

- http://localhost:8080/media_lists?sort_by=newest   (GET)

- http://localhost:8080/media_lists?sort_by=newest_created   (GET)

- http://localhost:8080/media_lists?sort_by=newest_updated   (GET)

- http://localhost:8080/media_lists?sort_by=most_liked   (GET)

- http://localhost:8080/media_list/2   (GET)

- http://localhost:8080/media_list/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 8
            },
            "userId": {
                "value": 4
            },
            "title": "New List",
            "description": "This is a new list",
            "mediaIds": [
                {
                "type": "MovieId",
                "value": 12983
                },
                {
                "type": "TvShowId",
                "value": 9865
                },
                {
                "type": "VideogameId",
                "value": 254
                }
            ],
            "visibility": "Public",
            "allowReplies": true,
            "ranked": true,
            "creationDate": "2024-01-10T18:37:28",
            "updateDate": "2024-01-10T18:37:28",
            "likes": [

            ],
            "replies": []
        }

- http://localhost:8080/media_list/4/edit   (PUT, con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 4
            },
            "userId": {
                "value": 2
            },
            "title": "Movies, games, etc. to get to know me",
            "description": "Hope you enjoy them as much as I've done",
            "mediaIds": [
                {
                    "type": "VideogameId",
                    "value": 144022
                },
                {
                    "type": "MovieId",
                    "value": 10402
                },
                {
                    "type": "MovieId",
                    "value": 376867
                },
                {
                    "type": "MovieId",
                    "value": 712454
                },
                {
                    "type": "VideogameId",
                    "value": 7346
                },
                {
                    "type": "VideogameId",
                    "value": 253148
                },
                {
                    "type": "VideogameId",
                    "value": 113112
                },
                {
                    "type": "VideogameId",
                    "value": 21865
                },
                {
                    "type": "TvShowId",
                    "value": 135918
                },
                {
                    "type": "TvShowId",
                    "value": 1402
                },
                {
                    "type": "BookId",
                    "value": "kHh_EAAAQBAJ"
                }
            ],
            "visibility": "Followers",
            "allowReplies": true,
            "ranked": false,
            "creationDate": "2024-11-03T19:12:18",
            "updateDate": "2024-11-03T19:12:18",
            "likesIds": [
                {
                    "value": 2
                }
            ],
            "repliesIds": []
        }

- http://localhost:8080/media_list/1/delete   (DELETE)