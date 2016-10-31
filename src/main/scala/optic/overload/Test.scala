package optic.overload

object Test extends App {

  val foo: Lens[String, Int] = Lens(List("foo"))
  val bar: Traversal[Int, String] = Traversal(List("bar"))

  // Fully defined optics compose fine
  val foobar = foo compose bar
  println((foo compose bar compose foo).desc)

  val ages: Lens[String, List[Int]] = Lens(List("ages"))
  def all[A]: Traversal[List[A], A] = Traversal(List("all"))

//  does not compile
//  println((ages compose all).desc)
//  but it works for the following:
  println((ages compose all[Int]).desc)
  println((ages composeTraversal all).desc)

}