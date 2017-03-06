Decode (and encode if you _really_ want to) [overpunch](https://en.wikipedia.org/wiki/Signed_overpunch) formatted numbers into decimals. This library is a simple port of the logic from the [python overpunch](https://github.com/truveris/overpunch) library.

## Examples

```scala
import com.penglish.Overpunch

Overpunch.decode("10}", decimals=0) == BigDecimal("-100")
Overpunch.decode("45A", decimals=0) == BigDecimal("451")
Overpunch.decode("1000}") == BigDecimal("-100.00")
```

## TODO

- I ported over the original python tests, and they all pass. I've added some rigorous scalacheck tests, but they currently don't pass. The library is probably usable, scalacheck seems to be just hitting the less likely edge cases.
