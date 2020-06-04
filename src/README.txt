README.txt

Hello, first off to play this game you will need to use our custom adventure client. (Client.legacy.CustomAdventureClient)
Project structure is also important as it references the text files from WorkingDirectory/Text/files
We do some cool frame menus and such so its window is needed to be standardized and controlled.

Secondly, we made a tutorial level for the rest of a game that at the moment is unfinished, we have character creation,
a spawn room, and code we would use to do some of the random generation of rooms, items, monsters, and the like.

This is all to say nothing past the tutorial level is working properly. We put lots of effort into the game as a whole and lots
of our time was spent in random generation that we don't fully showcase in the working code. There are two randomly
generated items in the tutorial but we also have code that generates random rooms, dungeons, and bosses, but not monsters.
We came up to the wire and to get all that was necessary done on time we had to sacrifice some of our hard work. We kept
most of that work in to show that we did work really hard on more than just the tutorial we display thorough working code.

If you'd like to see what we did with the generation, we jury-rigged a cheat-code to generate a level.
It sets up what we have so far for the generation, then pops all 'normal' players into the room.
You must have completed the tutorial first: cheatcodesareboss42 <size> <rooms>

Size is the size of the matrix, for example 10 would be a 10 by 10
Rooms is the number of rooms to attempt to add
Leaving the args blank lets the generator use the default, 11 by 11 with 9 rooms.

Thanks for a great semester,

Joseph Teahen, Daniel Masker, Emma Smith, Ben Hodsdon