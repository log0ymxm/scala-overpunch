package com.github.log0ymxm

import scala.math.BigDecimal.RoundingMode

object Overpunch {
  val decode_map = Map[String, (String, String)](
    "0" -> ("+", "0"),
    "1" -> ("+", "1"),
    "2" -> ("+", "2"),
    "3" -> ("+", "3"),
    "4" -> ("+", "4"),
    "5" -> ("+", "5"),
    "6" -> ("+", "6"),
    "7" -> ("+", "7"),
    "8" -> ("+", "8"),
    "9" -> ("+", "9"),
    "{" -> ("+", "0"),
    "A" -> ("+", "1"),
    "B" -> ("+", "2"),
    "C" -> ("+", "3"),
    "D" -> ("+", "4"),
    "E" -> ("+", "5"),
    "F" -> ("+", "6"),
    "G" -> ("+", "7"),
    "H" -> ("+", "8"),
    "I" -> ("+", "9"),
    "}" -> ("-", "0"),
    "J" -> ("-", "1"),
    "K" -> ("-", "2"),
    "L" -> ("-", "3"),
    "M" -> ("-", "4"),
    "N" -> ("-", "5"),
    "O" -> ("-", "6"),
    "P" -> ("-", "7"),
    "Q" -> ("-", "8"),
    "R" -> ("-", "9")
  )

  def decode(in: String, decimals: Int = 2): BigDecimal = {
    val n = in.length
    val lastChar = in.takeRight(1)
    val (sign, cent) = decode_map(lastChar)

    val core = (decimals == 0) match {
      case true => in.dropRight(1)
      case false => in.take(n - decimals) + "." + in.drop(n - decimals).dropRight(1)
    }

    BigDecimal(sign + core + cent)
  }

  val encode_map = Map[(Int, String), Char](
    (1, "0") -> '{',
    (1, "1") -> 'A',
    (1, "2") -> 'B',
    (1, "3") -> 'C',
    (1, "4") -> 'D',
    (1, "5") -> 'E',
    (1, "6") -> 'F',
    (1, "7") -> 'G',
    (1, "8") -> 'H',
    (1, "9") -> 'I',
    (-1, "0") -> '}',
    (-1, "1") -> 'J',
    (-1, "2") -> 'K',
    (-1, "3") -> 'L',
    (-1, "4") -> 'M',
    (-1, "5") -> 'N',
    (-1, "6") -> 'O',
    (-1, "7") -> 'P',
    (-1, "8") -> 'Q',
    (-1, "9") -> 'R'
  )

  def encode(in: BigDecimal, decimals: Int = 2, rounding: RoundingMode.Value = RoundingMode.HALF_UP): String = {
    val rounded = in.setScale(decimals, rounding).rounded
    val sign = in.signum match {
      case 0 => 1
      case x => x
    }
    val value = new StringBuilder(rounded.abs.toString.replace(".", ""))
    val n = value.length
    value.setCharAt(n - 1, encode_map((sign, value.toString.takeRight(1))))
    value.toString()
  }
}
