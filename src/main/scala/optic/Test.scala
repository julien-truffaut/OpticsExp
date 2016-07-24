package optic

object Test extends App {

  val foo: Lens[String, Int] = Lens(List("foo"))
  val bar: Traversal[Int, String] = Traversal(List("bar"))

  val foobar = foo compose bar
  println((foo compose bar compose foo).desc)

  val ages: Lens[String, List[Int]] = Lens(List("ages"))
  def all[A]: Traversal[List[A], A] = Traversal(List("all"))

  println((ages compose all[Int]).desc)
  println((ages composeTraversal all).desc)
  // fail
  println((ages compose all).desc)

  println((ages compose Each.each[List[Int], Int]).desc)
  println((ages composeTraversal Each.each).desc)

  // fail
  println((ages compose Each.each).desc)

}