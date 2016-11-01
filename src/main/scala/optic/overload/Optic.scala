package optic.overload

case class Traversal[A,B](desc : List[String]) {
  def compose[C](o: Lens[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)
  def compose[C](o: Traversal[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)

  def composeTraversal[C](o: Traversal[B, C]): Traversal[A, C] = compose(o)

  override def toString: String = desc.mkString(",")
}

case class Lens[A,B](desc : List[String]) {
  def compose[C](o: Lens[B, C]): Lens[A, C] = Lens(desc ++ o.desc)
  def compose[C](o: Traversal[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)

  def composeTraversal[C](o: Traversal[B, C]): Traversal[A, C] = compose(o)

  override def toString: String = desc.mkString(",")
}

object Test extends App {

  val foo: Lens[String, Int] = Lens(List("foo"))
  val bar: Traversal[Int, String] = Traversal(List("bar"))

  // Fully defined optics compose fine
  val foobar = foo compose bar
  println(foo compose bar compose foo)

  val ages: Lens[String, List[Int]] = Lens(List("ages"))
  def all[A]: Traversal[List[A], A] = Traversal(List("all"))

  //  does not compile
  //  println((ages compose all).desc)
  //  but it works for the following:
  println(ages compose all[Int])
  println(ages composeTraversal all)

}

