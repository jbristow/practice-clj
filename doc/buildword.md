### Build Word

You have a word in lowercase. Your task is to write this word using the
fragments you are given. Each element of fragments can be used more than once,
but they cannot overlap. It is not guaranteed that it's always possible to write
the word using the given fragments.

What is the minimum number of elements you have to use? Return 0 if it's not
possible to build a word.

#### Example

```clojure
;; bu + ild + wo + rd == 4
(build-word "buildword" ["buil" "dwor" "bu" "ild" "wo" "rd"]) ; 4
```


