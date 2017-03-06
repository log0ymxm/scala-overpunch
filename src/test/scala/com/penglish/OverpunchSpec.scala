package com.penglish

import org.scalatest._
import scala.math.BigDecimal.RoundingMode
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class OverpunchSpec extends FunSuite with Matchers {
  test("decode no decimal positive") {
    Overpunch.decode("12345{") shouldEqual BigDecimal("1234.50")
  }

  test("decode no decimal negative") {
    Overpunch.decode("12345}") shouldEqual BigDecimal("-1234.50")
  }

  test("decode 4 decimal negative") {
    Overpunch.decode("12345}", decimals=4) shouldEqual BigDecimal("-12.3450")
  }

  test("decode 0 decimal negative") {
    Overpunch.decode("12345}", decimals=0) shouldEqual BigDecimal("-123450")
  }

  test("encode no decimal positive") {
    Overpunch.encode(BigDecimal("1234.50")) shouldEqual "12345{"
  }

  test("encode no decimal negative") {
    Overpunch.encode(BigDecimal("-1234.50")) shouldEqual "12345}"
  }

  test("encode 4 decimal negative") {
    Overpunch.encode(BigDecimal("-12.3450"), decimals=4) shouldEqual "12345}"
  }

  test("encode 0 decimal negative") {
    Overpunch.encode(BigDecimal("-123450"), decimals=0) shouldEqual "12345}"
  }

  test("encode 2 decimal round default") {
    Overpunch.encode(BigDecimal("12.3450"), decimals=2) shouldEqual "123E"
  }

  test("encode 2 decimal negative round default") {
    Overpunch.encode(BigDecimal("-12.3450"), decimals=2) shouldEqual "123N"
  }

  test("encode 2 decimal round custom") {
    Overpunch.encode(BigDecimal("12.3450"), decimals=2, rounding=RoundingMode.FLOOR) shouldEqual "123D"
  }

  test("encode 2 decimal negative round custom") {
    Overpunch.encode(BigDecimal("-12.3450"), decimals=2, rounding=RoundingMode.FLOOR) shouldEqual "123N"
  }

  test("encode integer") {
    Overpunch.encode(150, decimals=0) shouldEqual "15{"
  }

}

import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary

object OverpunchCheckSpec extends Properties("BigDecimal") {
  // TODO these don't pass yet, need to handle cases where these more exhaustive tests fail
  // property("encode_decode") = forAll { (n: BigDecimal) =>
  //   Overpunch.decode(Overpunch.encode(n, decimals=n.precision), decimals=n.precision) == n
  // }

  val overpunchGen = for {
    num <- arbitrary[Int]
    punch <- Gen.oneOf('{', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', '}', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R')
  } yield List(Math.abs(num), punch).mkString

  // property("decode_encode") = forAll(overpunchGen) { (s) =>
  //   Overpunch.encode(Overpunch.decode(s)) == s
  // }
}
