package vanilla

case class Person(name: String, age: Int){
  def inc: Person = copy(age = age + 1)
}
