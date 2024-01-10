const FLIGHT_PARAMETERS_FORM = document.getElementById('flight-parameters-form');
const RANDOM_FLIGHT_DIV = document.querySelector(".preload-random-flight-div");
const INPUT_PARAMETERS_DIV = document.getElementById('main-input-div');
const DEPARTURE_DIV = document.getElementById('departure-div');
const DESTINATION_DIV = document.getElementById('destination-div');
const FLIGHT_INFO_DIV = document.getElementById('flight-info-div');

FLIGHT_PARAMETERS_FORM.addEventListener('submit', eventListenerRef => {
    eventListenerRef.preventDefault();

    const MAX_FLIGHT_HOURS = document.querySelector("input[name='hours']:checked").value;
    const PLANE_TYPE = document.querySelector("input[name='type']:checked").value;
    const PARAMETERS = JSON.stringify({"maxFlightHours" : MAX_FLIGHT_HOURS, "planeType": PLANE_TYPE});
    //loading screen
    INPUT_PARAMETERS_DIV.removeAttribute('class');
    INPUT_PARAMETERS_DIV.innerHTML = `
    <div class="lds-ellipsis">
        <div>
            </div>
                <div></div>
                <div></div>
            <div>
        </div>  
    </div>`;
    fetch('http://localhost:8080/api/v1/flight/custom', {
        method : "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body : PARAMETERS
    })
        .then(response => response.json())
        .then(dataRef => {
            INPUT_PARAMETERS_DIV.remove();
            RANDOM_FLIGHT_DIV.removeAttribute('class');
            RANDOM_FLIGHT_DIV.classList.add('random-flight-div');
            DEPARTURE_DIV.innerHTML = formattedAirportData(dataRef.airport1, 'departure');
            DESTINATION_DIV.innerHTML = formattedAirportData(dataRef.airport2, 'arrival');
            FLIGHT_INFO_DIV.innerHTML = formattedFlightData(dataRef);
        }).catch(function(){
        window.alert('Cannot connect to server');
        location.reload();
        return;
    });
});

function formattedFlightData(flightInfo){
    return (`
        <h2><i class="fa-solid fa-earth-americas"></i>Flight Info and Plane</h2>
        <hr/>
        <p>Estimated Flight Time: <span>${flightInfo.flightHours}</span></p>
        <p>Flight Distance: <span>${flightInfo.flightDistanceInMiles} miles</span></p>
        <p>Plane: <span>${flightInfo.plane.name}</span></p>
        <p>Cruising Speed: <span>${flightInfo.plane.speedInKnots} knots</span></p>
        <p>Type of Plane: <span>${flightInfo.plane.type}</span></p`);
}

function formattedAirportData(airport, airportDirection){
    let regionNames = new Intl.DisplayNames(['en'], {type: 'region'});
    return (`
        <h2><i class="fa-solid fa-plane-${airportDirection}"></i>${airportDirection}</h2>
        <hr/>
        <p>Airport Name: <span>${airport.airportName}</span></p>
        <p>ICAO Code: <span>${airport.icaoCode}</span></p>
        <p>Country: <span>${regionNames.of(airport.country)}</span></p>
        <p>Continent: <span>${airport.continent}</span></p>
        <p>Coordinates: <span>${airport.latitude} , ${airport.longitude}</span></p>`);
}