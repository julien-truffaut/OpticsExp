package optic.overload

case class Traversal[A,B](desc : List[String]) {
  def compose[C](o: Lens[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)
  def compose[C](o: Traversal[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)

  def composeTraversal[C](o: Traversal[B, C]): Traversal[A, C] = compose(o)
}

case class Lens[A,B](desc : List[String]) {
  def compose[C](o: Lens[B, C]): Lens[A, C] = Lens(desc ++ o.desc)
  def compose[C](o: Traversal[B, C]): Traversal[A, C] = Traversal(desc ++ o.desc)

  def composeTraversal[C](o: Traversal[B, C]): Traversal[A, C] = compose(o)
}



