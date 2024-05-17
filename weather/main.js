
const apiKey = "5523e6b097514a7dba7d617576dec79f"
const apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=hanoi"

// const searchBox = document.querySelector('.search input');
// const searchBtn = document.querySelector('.search button');





async function weather() {
   const response = await fetch(apiUrl + `&appid={apiKey}`)
   var data = await response.json();
   console.log(data);

//   document.querySelector(".city").innerHTML = data.city;
//   document.querySelector(".temp").innerHTML = Math.round(data.main.temp) + "°C";
//   document.querySelector(".humidity").innerHTML = data.main.humidity + "%";
//   document.querySelector(".wind").innerHTML = data.main.speed + "km/h";
}



weather();