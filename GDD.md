
# THE GAME

## MORG

# Development Manual

**Document v.0.0.2**

Dated 7/17/2020

## Introduction:

This is the beginning of the game development document for “THE GAME”, the game with no name yet. There are suggestions to name the development company as Endtime Entrepreneurs. The initial collaborators are Daniel Masker and Michael Clinesmith. There is no name for a company yet. The idea started from the idea to improve the game project Daniel and his classmates created at school at Michigan Tech. Daniel is a college student, Michael is a 40+ year old graduate from Michigan State University with degrees in Computer Science and Applied Mathematics who would like to refresh his skills and get into programming.

## Game Concepts:

The concept developed so far is a multiplayer Role Playing Game (RPG). The graphics are 2-D, with a map players can move around on. Players can improve their characters by improving skills, levelling up and increasing stats. If players die they can respawn. The world was suggested to have a medieval setting with humor. The world will have monsters to battle, quests to complete, events, world and character histories, guilds controlling land masses, characters to creature transformations, items, status effects, equipment, players and NPC interactions, craft items, and ways to develop and improve the world.

## Game Development Environment/Assets:

The people involved in developing the game need to have access to the internet either through a phone or computer. IntelliJ is being used as the development environment and the program is being written in Java. Files are uploaded to GitHub and version control is maintained.

### Individual assets:

Daniel – Has a computer, laptop, phone, headset, good internet, can program in any language, create stopmotion animation and is familiar with hardware

Michael – Has a computer, phone, headset, has degrees in computer science and mathematics, has clerk experience, and programming experience

Jade – Has a webcam, and familiarity with web development, python, programming and hardware.

Bethany – Has a phone and organizing experience

## Company Roles:

Early suggestion of company roles is that Michael and Daniel will focus on programming, Jade on pixel art and Bethany on organization.

## Development Schedule:

Weekly meetings are scheduled, with reports of progress and discussion about the development of the game and game engine, and their features.

## Game Design:

### Achievements:

Achievements were discussed but none have been developed yet.

### AI:

AIs will need to be developed to control the actions of hostile monsters, other creatures, and NPCs.

### Body:

The player and other creatures will have body classes, composed of different body objects. These objects will have values such as length, creature type, color, other features, items worn, conditions, bonuses, and may add skill abilities. Bodies will keep track of hunger, fatigue, health and thirst.

### Client/Server:

The game will be client/server based, with one server responding to many client requests. Packets sent will with a file length and message type.

### Conditions:

Some conditions can be placed on players.

### Crafting:

Some items can be crafted from other items. Some NPCS and monsters can craft items.

### Curses:

Some items may be cursed, that is they may have detrimental effects to statuses, may lower stat values, or may lock onto a player’s body when equipped.

### Equipment:

Equipment exists and can be equipped to various body parts. Equipment may be limited by creature type, and may generate effects (including slow transformations.) Cursed equipment is possible.

### Events:

Periodic events will occur in the world, giving players the chance to complete unique quests and obtain items.

### The Game World:

The world has 2-D maps to explore. There are permanent city areas than can be developed, different levels of maps/mazes can be randomly generated; there will be quests for players to follow and NPCS to interact with

### Guilds:

The world will have guilds that are in control of certain areas of a land mass and have various rules in them that are enforced. The may be wars that expand or decrease the size of the guilds.

### History:

There will be a history of various events in the server, and players will have memories of various historical events they are involved in.

### Inventory:

A player has an inventory. The current suggestion is that inventory is saved as a list with weight being a limiting factor, and that the same items can be saved as a group.

### Items:

There will be many items in the world. Items have name, weight and descriptions/lore. Various subclasses of items have been imagined. Food, having hunger and thirst fields. Equipment containing stat changes, effects/curses, durability and charges, material, legendary items uniquely created, consumable items with Numberofuses, effects, and status fields, potions as a subclass of consumable items, interactables, and holders containing a Maxweight field. Items can be placed in a player’s inventory.

### Loading/Saving:

A player will be able to save and load their character to control be able to use their character when they choose. A login will be used to connect players to their characters.

### Maps:

Maps will be randomly generated as mazes in a 2-D environment, with players and monsters occupying spaces on the map.

### Monsters:

Monsters will be initially generated randomly when a 2-D map is generated. Monsters will be able to use items and craft items and interact with players, sometimes attacking them. Loot drops from monsters are uncertain at this time. Monsters have health and have skills that can create conditions on a player and may cause transformation effects

### NPCS:

NPCs exist that can chat with players, craft items, give quests, and buy or sell items. The prices of items can vary by NPC and over time due to supply and demand.

### Quests:

Quests have been discussed but none have been developed as of yet.

### Skills:

There are numerous skills that players have, some by player brain, some by body part. Skills have a level and experience ranking. Level ranges from 0 (unskilled) to 100 (maximum skill). Skills are trained through use. There are super and subskills, such as “Harvest” and “Harvest Corn.” Superskills only gain a percentage of experience through use of a subskill. Skills are all initially locked; skills that can be used need to be unlocked in some way, such as being an ability of body part (e.g. slash with claw), item (e.g. pick-axe unlocks mine ore), or something else. These skills, include speaking and understanding various race languages, writing and wielding objects, hearing and tracking, harvesting and mining and searching.

### Statuses:

A player may be affected by status conditions. Such status conditions may cause transformations or have other effects. This will later be more fully developed.

### Transformation:

There are conditions or effects that can cause bodies to transform from one creature to another, this could happen quickly or slowly. A character’s hands could transform into wyvern wings, the skin type could change from skin to hair, A tail could develop, body parts could grow or shrink, color could change, features could be added or lost. It is unknown how much of a role this will be in game play at this time.

## Key Dates:

December 2019 - January 2020: Initial game development

May 15, 2020: Discord server set up for game development, game overhaul

## Credits:

Thank you to the following people for their work:

Daniel Masker for his client/server work

Michael Clinesmith for is body part design

Michael Clinesmith for designing this document