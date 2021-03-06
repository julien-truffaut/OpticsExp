package optic.lub

sealed trait Lub[F[_, _] <: Optic[F, _, _], G[_, _] <: Optic[G, _, _]]{
  // add witness that F[_,_] <: T[_,_] and G[_,_] <: T[_,_]
  type T[_, _] <: Optic[T, _, _]
  def compose[A, B, C](o1: F[A, B], o2: G[B, C]): T[A, C]
}

object Lub {

  type Aux[F[_, _] <: Optic[F, _, _], G[_, _] <: Optic[G, _, _], H[_,_] <: Optic[H, _, _]] = Lub[F, G]{
    type T[A, B] = H[A, B]
  }

  implicit val lensLens: Aux[Lens, Lens, Lens] = new Lub[Lens, Lens] {
    override type T[A, B] = Lens[A, B]
    override def compose[A, B, C](o1: Lens[A, B], o2: Lens[B, C]): Lens[A, C] = Lens(o1.desc ++ o2.desc)
  }

  implicit val lensTraversal: Aux[Lens, Traversal, Traversal] = new Lub[Lens, Traversal] {
    override type T[A, B] = Traversal[A, B]
    override def compose[A, B, C](o1: Lens[A, B], o2: Traversal[B, C]): Traversal[A, C] = Traversal(o1.desc ++ o2.desc)
  }

  implicit val traversalLens: Aux[Traversal, Lens, Traversal] = new Lub[Traversal, Lens] {
    override type T[A, B] = Traversal[A, B]
    override def compose[A, B, C](o1: Traversal[A, B], o2: Lens[B, C]): Traversal[A, C] = Traversal(o1.desc ++ o2.desc)
  }

  implicit val traversalTraversal: Aux[Traversal, Traversal, Traversal] = new Lub[Traversal, Traversal] {
    override type T[A, B] = Traversal[A, B]
    override def compose[A, B, C](o1: Traversal[A, B], o2: Traversal[B, C]): Traversal[A, C] = Traversal(o1.desc ++ o2.desc)
  }
}