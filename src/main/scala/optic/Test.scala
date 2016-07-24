package optic

object Test extends App {

  val foo: Lens[String, Int] = Lens(List("foo"))
  val bar: Traversal[Int, String] = Traversal(List("bar"))
  val fizz: Prism[Int, String] = Prism(List("fizz"))

  val foobar = foo compose bar
  println((foo compose bar compose foo).desc)

  println((foo compose fizz compose foo compose bar).desc)

}