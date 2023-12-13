# Wordle 

## Brute Force 

Word Length: 5
Guesses: 10

### Picking the first available word
 - Average time: 678 milliseconds
 - Wins: 67

### Picking a random available word
 - Average time: 670 milliseconds
 - Wins: 87

Although the times are very similar, picking a random word performs better at the game. This is most likely because the first available word is always the first word in the dictionary, and the dictionary is sorted alphabetically.