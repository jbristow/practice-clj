### chess

You know the position of two
[Knights](https://en.wikipedia.org/wiki/Knight_%28chess%29) on a standard chess
board (8x8), determine if they can attack each other or not.

Example: 
```clojure
;; white knight is on D4, 
;; black knight is on E2
(knight-attack? "D4" "e2") ; true
```
