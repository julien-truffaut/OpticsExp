package optic

sealed trait Optic[G[_,_] <: Optic[G, _, _], S, A] {
  def compose[F[_, _] <: Optic[F, _, _], B](o: F[A, B])(implicit lub : Lub[G,F]) : lub.T[S, B] =
    lub.compose(self, o)

  def composeTraversal[B](o: Traversal[A, B])(implicit lub : Lub[G,Traversal]) : lub.T[S, B] =
    lub.compose(self, o)

  def self: G[S,A]
  def desc: List[String]
}

case class Traversal[A,B](desc : List[String]) extends Optic[Traversal,A,B] {
  def self = this
}

case class Lens[A,B](desc : List[String]) extends Optic[Lens,A,B] {
  def self = this
}

case class Prism[A,B](desc : List[String]) extends Optic[Prism,A,B] {
  def self = this
}

