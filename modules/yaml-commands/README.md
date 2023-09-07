# yaml-commands

Parses commands from Yaml files into a CommandList.

## Examples
```yaml
# Simple Strings will be a console command
simple-command: say Hello World

# Lists will be executed in order
list-of-commands:
    - say Hello
    - say World
      
# You can also declare player commands. Placeholders are supported
player-command:
  sender: player
  command: say Hello world, I'm %player_name%
  
# Console commands also support placeholders, if you pass a player object to the run(...) method
console-greets-player-command:
  sender: console
  command: tell %player_name% Hello %player_name%! I'm the console!
  
# Console and player commands can also be mixed
on-join-commands:
  - sender: console
    command: say %player_name% has just joined the server!
  - sender: player
    command: say That's right, I have just joined the server!

```