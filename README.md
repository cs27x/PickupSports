PickupSports
============

### Team Members

* High Master Justin Paul
* Sith Lord Clark Perkins
* Pod Racer Michael "Now this is pod racing" Esparza
* Youngling Jeremey Key
* Jedi Knight Chad Ryan
* Jedi Cowboy Sean O'Reilly
* Yoda Cameron Ridgewell
* Darth Aston King
* Han "Han Brolo" Solo


### User Stories

1. As a user, I want to be able to create an event that people can join.
  
  -"Create new event" option available in GUI
2. As a user, I want to be able to find other peoples events and say I am going.
  
  -App has the ability to search events and an option to say a user is attending
3. As a user, I want to be able to play with certain rules.
  
  -Events have descriptions of how strictly they adhere to rules
4. As a user I want to know if I need to bring equipment to my pickup game.
  
  -Events have a provided vs required equipment section
5. As a user, I want to know how many people are coming/going to an event.
  
  -Events have an attendence field that can be updated by users who are going
5. As a user, I want to play with other people of my skill level.
  
  -Events have a skill level field
6. As a user, I want to be able to find people playing a game nearby.
  
  -Events can be searched by location, which they display
7. As a user, I want a description for a given meetup.
  
  -Events have a description field that displays when they are selected
8. As a user I want to know what time I need to be at the sports pickup game.
  
  -Events have a time field for what time an event occurs
9. As a user, I want to know where the game is taking place. 
  
  -Events have a location field that is filled in by the event creator
10. As a broke college student, I would like to choose pickup games based on the cost of the event.
  
  -Events have an optional field for event personal cost
11. As a frequent player, I want to be able to use a schedule to post numerous events. 
  
  -Option in "Create Event" that allows an event to be posted multiple times in a day, week, or month 
12. As a user I want to add a pickup game that I want to participate in to my calendar so that I don't forget.
  
  -Export to calender button on event description
13. As a user I want to be notified when a pickup game is started near me and it is a sport I like
  
  -Optional push notifications when favorited sports event criteria are starting near current location
14. As a user, I want to be able to login and have a rating based on my attendence and skill.
  
  -User accounts with ratings that are improved (or reduced) by other users


###Process
1. Front end will be using Android Studio
2. Two different main branches for front end and back end. Each person will branch off the main branch to create a feature. Once the feature completed we will branch again to test the feature. If the feature is properly tested we will submit a pull request so everyone can review the change. The feature will then be merged into the main branch. At the end of the project we will merge both the front and back end projects into master. Branches will be named in the following fashion feature_name/frontend/feat or feature_name/frontend/test.
3. All Android Activities will have associated Java classes so we can just test the java classes instead of the android classes.
4. One jUnit test class per java class.
5. Each person in the team will review the pull request before it merges into the main branch.
6. After a feature is completed, the commit log will indicate which user story was being worked on. All user stories will be a Github issue and when one is completed we will close out the issue.
7. We will use javadoc comments
8. We will decide on 5 user stories per build cycle to complete.
9. We will communicate when Master Justin rings the gong and we recieve smoke signals from Gondor (aka the backend team). When Theodin sounds the hord of Rohan we will stop working. We will also use GroupMe.
10. We will give others our application and ask them to try it out. Then they will tell us what stinks and we will change it.
11. We will divy up the work so that people who have heavy schedules can work less when they have other class work and vice versa.
12. The riskiest parts of this assignment is setting up a server which most of us have never done. We will all be dressed in risky business atire (jackets and tighty whities).

###HTTP API



__POST__ /events
   
   Parameters:
      - A JSON request body representing a foo object. Example: 
      
 ```
 {
    "sport": "asdf",
    "attendance": "",
    "skill_level": "",
    "equipment": "",
    "location": "",
    "time": "",
    "description": "",
    "free": true
 }
 ```
      
   Response:
      - 201 if successful with an empty body
      - 400 if there is an error in the request or request JSON
      - 500 if there is a server error
      
      
__GET__ /events

   Parameters: none

   Reponse:
      - 200 if successful with a body that is a list of json objects matching the above description.  Example: 
      
 ```
 [
     {
         "id": 3,
         ...
     },
     ...
 ]
 ```
      - 400 if there is an error with the request (not very likely)
      - 500 if there is a server error
      

__GET__ /events/\<id\>
      
      Parameters: none
      
      Response:
         - 200 if an object with the given ID exists, with a body of the json of that event
         - 400 if there was a bad request
         - 404 if an event with the given ID does not exist
         - 500 if there is a server error
