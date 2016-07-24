package optic

object Test extends App {

  val foo: Lens[String, Int] = Lens(List("foo"))
  val bar: Traversal[Int, String] = Traversal(List("bar"))
  val fizz: Prism[Int, String] = Prism(List("fizz"))

  val foobar = foo compose bar
  println((foo compose bar compose foo).desc)
  println((foo compose fizz compose foo compose bar).desc)

  val ages: Lens[String, List[Int]] = Lens(List("ages"))

  println((ages compose Each.each[List[Int], Int]).desc)
  println((ages composeTraversal Each.each).desc)

  // fail
  println((ages compose Each.each).desc)

}