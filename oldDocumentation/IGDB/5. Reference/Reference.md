# Reference

## Images

**Note**: Images that are removed or replaced from IGDB.com exist for 30 days before they are removed. Keep that in mind when designing cache logic.

### Examples

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields screenshots.*;
        where id = 1942;

Here we retrieve the image properties of the game with the id “1942”

    [{
        "id": 1942,
        "screenshots": [{
                "id": 9742,
                "game": 1942,
                "height": 1080,
                "image_id": "mnljdjtrh44x4snmierh",
                "url": "//images.igdb.com/igdb/image/upload/t_thumb/mnljdjtrh44x4snmierh.jpg",
                "width": 1920
            },
            {
                "id": 9743,
                "game": 1942,
                "height": 1080,
                "image_id": "em1y2ugcwy2myuhvb9db",
                "url": "//images.igdb.com/igdb/image/upload/t_thumb/em1y2ugcwy2myuhvb9db.jpg",
                "width": 1920
            }
        ]
    }]

#### Image url structure:

    https://images.igdb.com/igdb/image/upload/t_screenshot_med_2x/dfgkfivjrhcksyymh9vw.jpg

#### Break down:

    https://images.igdb.com/igdb/image/upload/t_{size}/{hash}.jpg

size is one of the interchangeable size types listed below. hash is the id of the image. The image sizes are all maximum size but by appending _2x to any size, you can get retina (DPR 2.0) sizes (cover_small_2x).

Name|Size|Extra
-|-|-
cover_small|90 x 128|Fit
screenshot_med|569 x 320|Lfill, Center gravity
cover_big|264 x 374|Fit
logo_med|284 x 160|Fit
screenshot_big|889 x 500|Lfill, Center gravity
screenshot_huge|1280 x 720|Lfill, Center gravity
thumb|90 x 90|Thumb, Center gravity
micro|35 x 35|Thumb, Center gravity
720p|1280 x 720|Fit, Center gravity
1080p|1920 x 1080|Fit, Center gravity

## Fields

### What?

Fields are properties of an entity. For example, a Game field would be genres or release_dates. Some fields have properties of their own, for example, the genres field has the property name.

### Where?

Fields can be used on any entity that has sub-properties such as Games, Companies, People etc.

### How?

Fields are requested in a comma separated list. For example, to get some information for some Games, Genres, Themes or anything else, you could request it like this:

### Apicalypse

    where id = (4356,189,444);
    fields name,release_dates,genres.name,rating

### Legacy Parameters

    /games/4356,189,444?fields=name,release_dates,genres.name,rating

Note in Apicalypse the name property of genres can be accessed directly with a dot (genres.name).

A full list of fields can be obtained by passing a * as a field. Alternatively you can use the meta postfix: /games/meta to get a list of all fields.

### Shorthand

Another way of writing fields is to use the shorthand f which achieves the same result.

    f name,release_dates,genres.name,rating;
    w id = (4356,189,444);

## Exclude

### What?

Exclude is a complement to the regular fields which allows you to request all fields with the exception of any numbers of fields specified with exclude.

### How?

Fields to be excluded are specified as a comma separated list. For example, to get all fields excpect for screenshots, you could request it like this:

### Apicalypse

    fields *;
    exclude screenshots;

### Shorthand

Another way of writing exclude is to use the shorthand x which achieves the same result.

    f *;
    x screenshots;

## Expander

### What?

Some fields are actually ids pointing to another endpoint. The expander feature is a convenient way to go into these other endpoints and access more information from them in the same query, instead of having to do multiple queries.

### Where?

Expands are specificed among the regular fields in the body of the query.

### How?

Fields can be expanded with a dot followed by the fields you want to access from a certain endpoint.

### Examples

In the example below we request the fields name and genres for the game The Witcher 3 with id 1942.

fields name,genres;
where id = 1942;

But this query will only return ids for the genres, which can be seen in the first response to the right:

    "First example response showing genre ids"
    [
        {
            "id ": 1942,
            "genres":[
                12,
                31
            ],
            "name": "The Witcher 3: Wild Hunt"
        }
    ]

For some use cases the id is all that is needed, but other times more data is needed, This is when the expander features comes in handy.

    fields name,genres.name;
    where id = 1942;

This example with expander retrieves the name of each genre which can be seen in the second response to the right.

    "Second example response showing genre ids and name"
    [
        {
            "id": 1942,
            "genres": [
                {
                    "id": 12,
                    "name": "Role-playing (RPG)"
                },
                {
                    "id": 31,
                    "name": "Adventure"
                }
            ],
            "name": "The Witcher 3: Wild Hunt"
        }
    ]

And lastly lets take a look at how you can use a wildcard character * to retrieve all data from genres in the previous example.

    fields name,genres.*;
    where id = 1942;

See the third response to the right where all available data for each genre is included in the response.

    "Third example response showing all available genre data"
    [
        {
            "id": 1942,
            "genres": [
                {
                    "id": 12,
                    "created_at": 1297555200,
                    "name": "Role-playing (RPG)",
                    "slug": "role-playing-rpg",
                    "updated_at": 1323216000,
                    "url": "https://www.igdb.com/genres/role-playing-rpg"
                },
                {
                    "id": 31,
                    "created_at": 1323561600,
                    "name": "Adventure",
                    "slug": "adventure",
                    "updated_at": 1323561600,
                    "url": "https://www.igdb.com/genres/adventure"
                }
            ],
            "name": "The Witcher 3: Wild Hunt"
        }
    ]

## Filters

### What?

Filters are used to sift through results to get what you want. You can exclude and include results based on their properties. For example you could remove all Games where the rating was below 80 (where rating >= 80).

### How?

Filters are parameter arrays so must be added using special keys like this:

- Address:

        https://api.igdb.com/v4/games/

- Body:

        search “zelda”;
        where rating >= 80 & release_dates.date > 631152000;

### Where?

Filters can be used on any entity that has sub-properties such as Games, Companies, People etc.

### Available Postfixes

- = Equal: Exact match equal.

- != Not Equal: Exact match equal.

- [>] Greater than (works only on numbers).

- [>=] Greater than or equal to (works only on numbers).

- < Less than (works only on numbers).

- <= Less than or equal to (works only on numbers).

- = "Your input string"* Prefix: Exact match on the beginning of the string, can end with anything. (Case sensitive).

- ~ "Your input string"* Prefix: Exact match on the beginning of the string, can end with anything. (Case insensitive).

- = *"Your input string" Postfix: Exact match at the end of the string, can start with anything. (Case sensitive).

- ~ *"Your input string" Postfix: Exact match at the end of the string, can start with anything. (Case insensitive).

- = *"Your input string"* Infix Exact match in the middle of the string, can start and end with anything. (Case sensitive).

- ~ *"Your input string"* Infix Exact match in the middle of the string, can start and end with anything. (Case insensitive).

- != null The value is not null.

- = null The value is null.

- [V1,V2,...Vn] The value exists within the (comma separated) array (AND between values).

- ![V1,V2,...Vn] The values must not exist within the (comma separated) array (AND between values).

- (V1,V2,...Vn) The value has any within the (comma separated) array (OR between values).

- !(V1,V2,...Vn) The values must not exist within the (comma separated) array (OR between values).

- {V1,V2,...V2} Exact match on arrays. (Does not work on ids, strings, etc).

### Examples

#### Filter by multiple platforms

To get games that are released on PS4 OR XBOX ONE OR PC

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where release_dates.platform = (48,49,6);

Similarly if you want games released on PS4 AND XBOX ONE AND PC

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where release_dates.platform = [48,49,6];

If you want games released only on PC

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where release_dates.platform = 6;

And if you want games released for PC OR any other platform

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where release_dates.platform = (6);

### Combining Multiple Filters

It is possible to to use logical operators between filters, which could look something like this:

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name,platforms,genres.name;
        where (platforms = [6,48] & genres = 13) | (platforms = [130,48] & genres = 12);

The response from this example query will be games that fulfil one or both of two sets or requirements:

Games released for for both PC (6), and PS4 (48) and also has the genre simulator (13).

Games released for for both Switch (130), and PS4 (48) and also has the genre Role-Playing (13).

### Prefix, Postfix and Infix

#### Prefix

Filtering for game names beginning with “Super” (this will return games such as for example Super Mario World)

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where name = "Super"*;

#### Postfix

Filtering for game names ending with with “World” (this will also return games such as for example Super Mario World)

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where name = *"World";

#### Infix

Filtering for game names containing the string “Smash” anywhere (this will return games such as for example Super Smash Bros)

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where name = *"Smash"*;

#### case insensitive version

Filtering for game names containing the string “Smash” (this will return games such as for example Super Smash Bros)

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where name ~ *"Smash"*;

### Removing erotic games from API responses

Some queries may return games with erotic themes. All erotic games in the database has the theme ’erotic’ (id = 42). So by adding a simple filter like the one below you can remove them from your responses.

- Address:

        https://api.igdb.com/v4/games/

- Body:

        fields name;
        where themes != (42);

## Sorting

### What?

Sorting is used to order results by a specific field.

### How?

You can order results like this:

Address:

https://api.igdb.com/v4/games/

- Body:

        sort release_dates.date desc;
        where rating >= 80;

Notice the appended :desc (descending) which could also be :asc (ascending) if required.

#### Order by rating

Rating parameter for games. You can access it like this:

- Address:

    https://api.igdb.com/v4/games/

- Body:

        fields name,rating;
        sort rating desc;
        where rating != null;

### Where?

Ordering can be used on any entity.

## Search

### What?

Search based on name, results are sorted by similarity to the given search string.

### Where?

Searchable endpoints:

- Characters

- Collections

- Games

- People

- Platforms

- Themes

### How?

You specify which endpoint to search through in the Address field of your request. The search string is then entered in the body of the request by typing search, blank space followed by the string you wish to search for.

- Address:

        https://api.igdb.com/v4/games/

- Body:

        search “zelda”;

## Pagination

Here is an example for how to use limit. 
The default limit is 10. The maximum value you can set for limit is 500.

- Address:

        https://api.igdb.com/v4/platforms/

- Body:

        limit 33;

There is also an offset. This will start the list at position 22 and give 33 results.

- Address:

        https://api.igdb.com/v4/platforms/

- Body:

        limit 33;
        offset 22;

## Protocol Buffers

Google Protocol Buffers is a language neutral method for serializing structured data.

The IGDB API supports responses in this format so you do not have to write your own serialization libraries, but instead you could just generate one.

Since this is langage neutral it is supported by a variatey of languages.

### How?

Generate the objects in your language of choise with our own Protobuf file, here

This file contains the mapping of the entire IGDB API and can be used to generate wrappers, code and tooling in any programming language.

The protobuf file is created in accordance with the proto3 specification

There are plenty of examples on how to do this Online and on the Protobuf Site.

### Where?

To start recieving protobuf compatible responses from then api all you need to do is add .pb at the end of your request:

https://api.igdb.com/v4/games.pb

Then use your generated files to parse the response into the expected object.

## Tag Numbers

Tag numbers are automatically generated numbers which provide a compact and fast way to do complex filtering on the IGDB API. The number calculation can be easily achieved with any programming language.

The basis of the calculation is a 32bit integer, where the first 4 bits contain the object type ID, and the remaining 28 bits represent the ID of the object we are generating the tag number for.

Using this method a flat index of custom object ‘hashes’ can be maintained in which index the search and filtering is faster than using conventional methods.

Currently the following object types use tags:

Type ID|Name
-|-
0|Theme
1|Genre
2|Keyword
3|Game
4|Player Perspective

Let’s see two examples for tag number calculation.

    // Javascript

    const genreTypeID = 1; // The type ID from the table above

    const shooterGenreID = 5; // The Shooter genre's ID, coming from the genres endpoint.

    let tagNumber = genreTypeID << 28; // Bit-shifting the genre's type ID by 28 bits, ensuring that it will get into the first four bits. The result will be 268435456

    tagNumber |= shooterGenreID; // Adding the Shooter genre ID to the tag number with a bitwise OR operation. The result will be 268435461.

We try to find all the games which relate to the Shooter genre. The tag number generation in Javascript would look something like the example on the right.

Javascript example query:

- Address:

        https://api.igdb.com/v4/games/

- Body:

        where tags = (268435461);

Python example query:

- Address:

        https://api.igdb.com/v4/games/

- Body:

        where tags = (536871060);

## Multi-Query

Multi-Query is a new way to request a huge amount of information in one request! With Multi-Query you can request multiple endpoints at once, it also works with multiple requests to a single endpoint as well.

A Multi-Query is made by making a POST request to: https://api.igdb.com/v4/multiquery.

**Syntax Structure** The Multi-Query syntax is made up of three pieces; “Endpoint name”, “Result Name (Given by you)”, and the APICalypse query inside the body {}.

**important** You can only run a maximum of 10 queries.

### Example 1:

Get the count of platforms in the api.

    query platforms/count "Count of Platforms" {
    // here we can have additional filters
    };

This above query will give us the following result:

    [
    {
        "name": "Count of Platforms",
        "count": 155
    }
    ]

### Example 2:

Get Playstation 4 Exclusives

    query games "Playstation Games" {
        fields name,platforms.name;
        where platforms !=n & platforms = {48};
        limit 1;
    };

This above query will give us the following result:

    [
        {
            "name": "Playstation Games",
            "result": [
                {
                    "id": 52826,
                    "name": "Skate 4",
                    "platforms": [
                        {
                            "id": 48,
                            "name": "PlayStation 4"
                        }
                    ]
                }
            ]
        }
    ]

### Example 3:

Combining the queries of example 1 and 2.

    query platforms/count "Count of Platforms" {
    // here we can ahve additional filters
    };

    query games "Playstation Games" {
        fields name,platforms.name;
        where platforms !=n & platforms = {48};
        limit 1;
    };
    [
        {
            "name": "Count of Platforms",
            "count": 155
        },
        {
            "name": "Playstation Games",
            "result": [
                {
                    "id": 52826,
                    "name": "Skate 4",
                    "platforms": [
                        {
                            "id": 48,
                            "name": "PlayStation 4"
                        }
                    ]
                }
            ]
        }
    ]

## APICalypse

### APICalypse cheatsheet

APICalypse is a new language used for this api which greatly simplifies how you can query your requests compared to the url parameters used in API V2.

### Fields

Fields are used to select which fields you want back from your request to the api.

To select fields you need the APICalypse command fields or its shorthand f.

Popular wildcard is to add * instead of a field, this will give you all of the fields.

    fields name,release_dates,genres.name,rating;
    f name,release_dates,genres.name,rating;

### Exclude

Commonly used with selecting all fields with the wildcard * this command will exclude the fields that you select.

To exclude fields you don’t need the APICalypse command exclude or its shorthand x.

    fields *;
    exclude tags,keywords;

    f *;
    x tags,keywords;

### Where

Where is easiest described as a filter. With where you can filter on specific fields.

To filter your results use the APICalypse command where or its shorthand w.

    fields *;
    where genres = 4;

    f *;
    w genres = 4;

### Limit

Limit describes how many results you will get back from the api, the standard value is 10.

To set a new limit use the APICalypse command limit or it’s shorthand l.

    fields *;
    limit 50;

    f *;
    l 50;

### Offset

Offset describes how many results you will skip over, standard is 0.

To set a new offset use the APICalypse command offset or it’s shorthand o.
Offset is often used together with Limit for pagination.

    limit 50;
    offset 50;

    l 50;
    o 50;

### Sort

Use Sort to order the results to your liking.

To order the results use the APICalypse command sort or it’s shorthand s.
Sort has two accompaning commands for “direction”; asc Ascending order and desc Decending order.

    fields *;
    sort rating asc;

    f *;
    s rating desc;

### Search

To find a specific title you can use Search.

To use search use the APICalypse command search, it has no shorthand :(. Search has it’s own endpoint where it is good to use a filter for specific kinds of results, example where game != null; for only games.

    search "Halo";
    fields name;

    search "Halo";
    f name;

### Other shorts

Null can be written null or n. Booleans can be written as true or t and false or f