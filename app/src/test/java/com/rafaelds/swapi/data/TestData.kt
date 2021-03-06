package com.rafaelds.swapi.data

import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.data.model.films.FilmDTO
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDTO
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetDTO
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.data.model.species.SpecieDTO
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.data.model.starships.StarshipDTO
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.data.model.vehicles.VehicleDTO

object TestData {
    val PERSON_DTO = PersonDTO(
        name = "Luke",
        gender = "male",
        url = "http://api/people/42",
        height = "120",
        mass = "60",
        skin_color = "fair",
        eye_color = "blue",
        hair_color = "blond",
        birth_year = "1990",
        homeworld = "http://api/planets/1",
    )
    val PERSON = Person(
        id = 42,
        name = "Luke",
        gender = "male",
        appUri = "swapi://api/people/42",
        height = "120",
        mass = "60",
        skinColor = "fair",
        eyeColor = "blue",
        hairColor = "blond",
        birthYear = "1990",
        planetData = null
    )
    val PLANET = Planet(
        id = 42,
        name = "tatooine",
        rotationPeriod = "1",
        orbitalPeriod = "2",
        diameter = "3",
        gravity = "4",
        terrain = "terrain",
        surfaceWater = "5",
        population = "100",
        residents = listOf(),
        films = listOf(),
        appUri = "swapi://planets/42"
    )

    val PLANET_DTO = PlanetDTO(
        name = "tatooine",
        rotation_period = "1",
        orbital_period = "2",
        diameter = "3",
        gravity = "4",
        terrain = "terrain",
        surface_water = "5",
        population = "100",
        residents = listOf("resident"),
        films = listOf("film"),
        url = "http://planets/42"
    )

    val FILM_DTO = FilmDTO(
        title = "star wars",
        episode_id = 2,
        director = "director",
        producer = "producer",
        release_date = "2010",
        species = listOf(),
        starships = listOf(),
        vehicles = listOf(),
        characters = listOf(),
        planets = listOf(),
        url = "http://films/42",
        opening_crawl = "crawl"
    )

    val FILM = Film(
        title = "star wars",
        episode = "2",
        openingCrawl = "crawl",
        director = "director",
        producer = "producer",
        releaseDate = "2010",
        species = listOf(),
        starships = listOf(),
        vehicles = listOf(),
        characters = listOf(),
        planets = listOf(),
        appUri = "swapi://films/42",
        id = 42
    )

    val STARSHIP_DTO = StarshipDTO(
        name = "Starship",
        model = "model",
        starship_class = "Starship Class",
        manufacturer = "Manufacturer",
        cost_in_credits = "32131",
        length = "3123",
        crew = "32131",
        passengers = "4211",
        max_atmosphering_speed = "94831",
        hyperdrive_rating = "1",
        MGLT = "MGLT",
        cargo_capacity = "9000",
        consumables = "5 years",
        films = listOf(),
        pilots = listOf(),
        url = "http://starships/42"
    )

    val STARSHIP = Starship(
        name = "Starship",
        model = "model",
        starshipClass = "Starship Class",
        manufacturer = "Manufacturer",
        costInCredits = "32131",
        length = "3123",
        crew = "32131",
        passengers = "4211",
        maxAtmoshperingSpeed = "94831",
        hyperdriveRating = "1",
        MGLT = "MGLT",
        cargoCapacity = "9000",
        consumables = "5 years",
        films = listOf(),
        pilots = listOf(),
        appUri = "swapi://starships/42",
        id = 4
    )

    val VEHICLE_DTO = VehicleDTO(
        name = "Vehicle",
        model = "model",
        vehicle_class = "Vehicle Class",
        manufacturer = "Manufacturer",
        cost_in_credits = "32131",
        length = "3123",
        crew = "32131",
        passengers = "4211",
        max_atmosphering_speed = "94831",
        cargo_capacity = "9000",
        consumables = "5 years",
        films = listOf(),
        pilots = listOf(),
        url = "http://vehicles/42"
    )

    val VEHICLE = Vehicle(
        name = "Vehicle",
        model = "model",
        vehicleClass = "Vehicle Class",
        manufacturer = "Manufacturer",
        costInCredits = "32131",
        length = "3123",
        crew = "32131",
        passengers = "4211",
        maxAtmoshperingSpeed = "94831",
        cargoCapacity = "9000",
        consumables = "5 years",
        films = listOf(),
        pilots = listOf(),
        appUri = "swapi://vehicles/42",
        id = 4
    )

    val SPECIE_DTO = SpecieDTO(
        name = "Vehicle",
        films = listOf(),
        url = "http://vehicles/42",
        language = "language",
        homeworld = "http://planets/2",
        designation = "designation",
        classification = "classification",
        average_height = "50",
        average_lifespan = "100",
        eye_colors = "blue",
        hair_colors = "black",
        skin_colors = "fair",
        people = listOf()
    )

    val SPECIE = Specie(
        name = "Vehicle",
        films = listOf(),
        language = "language",
        designation = "designation",
        classification = "classification",
        averageHeight = "50",
        averageLifespan = "100",
        eyeColors = "blue",
        hairColors = "black",
        skinColors = "fair",
        people = listOf(),
        appUri = "swapi://species/42",
        id = 4
    )
}