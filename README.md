# Couchbase Spark Connector 
A few getting started samples showing the integration between Couchbase and Spark. 
```
Most of the programs in the src directory leverage the travel-sample dataset available in Couchbase by default.
The Data directory contains some spark-shell examples using json data.
The slide deck (CouchbaseSpark_LA_Meetup_v3.pptx) is a general walkthrough of the technologies.
The slide deck (Spark Code.pptx) is published by our dev advocacy team and a good summary refrence.
The directory DockerDemo contains docker-compose environment that is still a work in progress. 
```

### Sample Data Model

### Airlines
```json
airline_10_
{
  "id": 10,
  "type": "airline",
  "name": "40-Mile Air",
  "iata": "Q5",
  "icao": "MLA",
  "callsign": "MILE-AIR",
  "country": "United States"
}
```

### Airports
```json
airport_3577
{
    "travel-sample": {
        "airportname": "Seattle Tacoma Intl",
        "city": "Seattle",
        "country": "United States",
        "faa": "SEA",
        "geo": {
            "alt": 433,
            "lat": 47.449,
            "lon": -122.309306
        },
        "icao": "KSEA",
        "id": 3577,
        "type": "airport",
        "tz": "America/Los_Angeles"
    }
}
```

### Routes
```json
route_5966                         //This is the key, which also acts as a primary key
{
    "id": "5966",
    "type": "route",              //This is the type identifier for the document
    "airline": "AA",
    "airlineid": "airline_24",     //This is the foreign key identifier to an airline document
    "sourceairport": "MCO",
    "destinationairport": "SEA",
    "stops": "0",
    "equipment": "737",
    "schedule": [
        {"day": 1, "utc": "13:25:00", "flight": "AA788"}, 
        {"day": 4, "utc": "13:25:00", "flight": "AA419"},
        {"day": 5, "utc": "13:25:00", "flight": "AA519"}
    ]
}
```

### Routes
```json
landmark_21661
{
    "activity": "sleep",
    "address": "12 Rue Boulainvilliers",
    "alt": null,
    "checkin": null,
    "checkout": null,
    "city": null,
    "content": "Small three star hotel (33 rooms).",
    "country": "France",
    "directions": null,
    "email": null,
    "fax": null,
    "geo": {
        "lat": 48.853,
        "lon": 2.27593
    },
    "hours": null,
    "id": 21661,
    "image": null,
    "name": "Hotel Eiffel Kennedy",
    "phone": "+33 1 45 24 45 75",
    "price": "~\u20ac150",
    "state": "Ile-de-France",
    "title": "Paris/16th arrondissement",
    "tollfree": null,
    "type": "landmark",           
    "url": "http://ww.eiffelkennedy.com"
}
```


 
