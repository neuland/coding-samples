package patternmatching

/*
- Pattern Matching ist syntactic sugar für if-elseif-else-Konstrukte
- Voraussetzung ist, daß das Objekt eine unapply-Methode hat (Case-Klassen, Collections etc. haben die)
 */


object PatternMatching {

  def main(args: Array[String]) {
//        exampleWerte()
//        exampleOption()
//        exampleEither()
//        exampleType()
    exampleCaseClass()
  }


  def exampleWerte(): Unit = {
    val number = 44

    val res = number match {
      // Konstanten
      case 1 => "ich bin ne 1"
      case 2 => "ich bin ne 2"
      case 3 | 4 => "ich bin ne 3 oder 4"
      // expression
      case greaterTen if greaterTen > 10 => s"ich bin >10 ($greaterTen)"
      // ausdrücke
      case _ => "ich bin irgendwas"
    }

    println(res)
  }

  def exampleOption(): Unit = {
    val option: Option[Int] = Option(44)

    val res = option match {
      case Some(number) => s"Ich bin ne Option mit dem Wert $number"
      case None => "ich bin ne leere Option"
    }

    println(res)
  }

  def exampleEither(): Unit = {
    val either: Either[String, Int] = Left("huhu")


    //    val either: Either[String, Int] = Right(42)

    val res = either match {
      case Left(l) => s"Ich bin ein Left mit dem Wert $l"
      case Right(r) => s"Ich bin ein Right mit dem Wert $r"
    }

    println(res)
  }

  def exampleType(): Unit = {

    def matchFunction(value: Any) = {
      value match {
        case s: String => s"Ich bin ein String ($s)"
        case i: Int => s"Ich bin ein Int ($i)"
        case _: Double => s"Ich bin ein Double"
        case _ => "Unbekannter Datentyp"
      }
    }

    println(matchFunction(1))
    println(matchFunction("Hallo"))
    println(matchFunction(Option("Hallo")))
  }

  def exampleCaseClass(): Unit = {
    val person = Person("Hans", 18, 'M')

    val res = person match {
      case Person(name, age, 'M') if age >= 18 => s"$name ist ein Mann"
      case Person(name, age, 'M') => s"$name ist ein Junge"
      case Person(name, age, 'F') if age >= 18 => s"$name ist eine Frau"
      case Person(name, age, 'F') => s"$name ist ein Mädchen"
    }

    println(res)
    // Falls man mal einen alias für Pattern braucht: case p @ Person(_, _, 'F') => p...
  }

}

case class Person(name: String, age: Int, gender: Byte)