# Meal Planit

[Javadoc](docs/index.html)


### Motivations
There are a handful of meal logging apps but there aren't many meal planning apps. Meal Planit was an approach to trying to
create a simple, easy to use app that could help create a plan for you based off of things such as calories, diet, and allergies/dislikes.
It was designed with ease of use in mind so that anyone would be able to intuitively use it to help with nutritional meal planning 
for various needs.

### Current State
The current state of the app is a user can create an account specified with daily calories, diet, and an allergy all from a predetermined
list. It allows them to set a user picture from their phone and to change those settings, it will then use those settings to 
auto-generate a plan when a day is clicked for that date. The plan can be regenerated if the user wishes by clicking the generate button. 
The individual meals can also be clicked on to send the user to to a recipe summary of that meal.

#### * Unemplemented/Incomplete elements:
* If going through menus too fast fragments can lock up or overlay.
* No database table implemented for calories.
* Join queries not implemented.
* Visually shows the current day as the same date for each year when going through the calendarview.
* Currently keeping track of a user through shared preferences and database queries instead of keeping an object with those items.

### Devices Tested On
* Android 7.1.1 (Pixel XL API 25)
* Android 7.1.1 (Pixel C API 25)
* Android 7.0 (Nvidia K1 API 24)
* Android 5.1.1 (Nexus 5X API 21)

### UI Improvements
* Ability to see picture being uploaded while in settings before implemented.
* Better placement of login screen.
* Less unused Realestate in settings.

### Stretch goals
* Implementing the user being able to have multiple allergies.
* Implementing a table for dislikes in Restrictions table.
* Allow the ability to customize the meal plan from a list of options.
* A search function for a list of various meals.
* Including budget taken into account as a modifier for meal plans.
* Search recipes by ingredients and/or name.

### 3rd Party Licenses
* [Retrofit Library](https://square.github.io/retrofit/) & [License](https://github.com/square/retrofit/blob/master/LICENSE.txt)
* [Gson Library](https://github.com/google/gson) & [License](https://github.com/google/gson/blob/master/LICENSE)
* [Joda Time Library](https://github.com/dlew/joda-time-android) & [License](https://github.com/dlew/joda-time-android/blob/master/LICENSE)

### External Services
* [Spoonacular Food API](https://rapidapi.com/spoonacular/api/Recipe%20-%20Food%20-%20Nutrition)


### License

Copyright 2018 &copy;, Donny Stout

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
