package vanilla

import org.scalatest.{Matchers, WordSpec}

class PersonSpec extends WordSpec with Matchers {

  "a Person" should {

    "inc should increase age" in {
      val p = Person("Paul", 25)
      (p.inc.age - p.age) shouldEqual 1
    }

  }

}
