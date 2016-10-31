package optic.overload

trait Each[S, A] {
  def each: Traversal[S, A]
}

object Each {
  def each[S, A](implicit ev: Each[S, A]): Traversal[S, A] = ev.each

  implicit def listEach[A]: Each[List[A], A] = new Each[List[A], A] {
    override def each: Traversal[List[A], A] = Traversal(List("each"))
  }
}
