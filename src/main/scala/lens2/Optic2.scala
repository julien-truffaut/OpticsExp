package lens2




//class .. => Walkable p where
//  walk :: (forall f. Applicative f => (a -> f b) -> s -> f t) -> p a b -> p s t

trait Profunctor[P[-_,+_]] {
  def dimap[A,B,C,D](f : A => B, g: C => D)(p: P[B,C]): P[A,D]
}

trait Strong[P[-_,+_]] extends Profunctor[P] {
  def first[A,B,C](p: P[A,B]): P[(A,C),(B,C)]
}

//type Lens s t a b = forall p. Strong p => p a b -> p s t

abstract class Lub[X[_[-_,+_]],Y[_[-_,+_]]] {
  type Z[_[-_,+_]]

  def subst1[F[-_[_[-_,+_]]]](p : F[Z]): F[X]
  def subst2[F[-_[_[-_,+_]]]](p : F[Z]): F[Y]
}


//
//trait Optic[-S, +T, +A, -B] {
//  type K[_[-_,+_]]
//  def compose[J[_[-_,+_]],U,V](o : Optic[A,B,U,V])(implicit lub: Lub[K,o.K]) = new Optic[S,T,U,V] {
//    type K[P[-_,+_]] = lub.Z[P]
//    override def apply[P[-_,+_]](o : P[U,V])(implicit k : K[P]): P[S,T] = ???
//  }
//  def apply[P[-_,+_]](o : P[A,B])(implicit k : K[P]): P[S,T]
//}



//trait Optic[A,B] {
//
//  implicit def lubLens[C] : Lub[Lens,C]
//  implicit def lubTraversal[C]  : Lub[Traversal,C]
//
//  def compose[F[_,_],C](o : F[B,C])(implicit lub: Lub[F,C]): lub.T = lub.gimme(o)
//}

//trait Lub[F[_,_],A, B, C] {
//  type T <: Optic[A, C]
//  def gimme(o : F[B,C]): T
//}
//
//case class Lens[A,B](s: List[String]) extends Optic[A,B]
//
//object Lens {
//  implicit def lubLens[A, B, C] : Lub[Lens, A, B, C] = new Lub[Lens,C] {
//    override type T = Lens[A, C]
//    def gimme(o : Lens[B,C]): Lens[A,C] = Lens(s ++ o.s)
//  }
//
//  implicit def lubTraversal[C] : Lub[Traversal,C] = new Lub[Traversal,C] {
//    override type T = Traversal[A, C]
//    def gimme(o : Traversal[B,C]): Traversal[A,C] = Traversal(s ++ o.s)
//  }
//}
//
//case class Traversal[A,B](s: List[String]) extends Optic[A,B] {
//  override implicit def lubLens[C] : Lub[Lens,C] = new Lub[Lens,C] {
//    override type T = Traversal[A, C]
//    def gimme(o : Lens[B,C]): Traversal[A,C] = Traversal(s ++ o.s)
//  }
//
//  override implicit def lubTraversal[C] : Lub[Traversal,C] = new Lub[Traversal,C] {
//    override type T = Traversal[A, C]
//    def gimme(o : Traversal[B,C]): Traversal[A,C] = Traversal(s ++ o.s)
//  }
//}
//
//
//object Test extends App {
//
//  val foo: lens2.Lens[String, Int] = lens2.Lens(List("foo"))
//  val bar: Traversal[Int, String] = Traversal(List("bar"))
//
//  val foobar = foo compose bar
//
////  println(foobar.isInstanceOf[Traversal[String ,String]])
////
////  val foobarfoo = foobar compose foo
//////
////  println((foo compose bar compose foo).desc)
//
//}