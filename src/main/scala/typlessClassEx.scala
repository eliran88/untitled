/**
  * Created by eliranm on 28/06/2017.
  */

import scala.collection.mutable

object typlessClassEx {

  def mySort[T](numbers: List[T])(implicit order: Ordering[T]) : List[T] = {
    //run on each element to put it on it place
    bubbleSort[T](numbers,List.empty)
  }

  def bubbleSort[T](list: List[T], newList: List[T])(implicit order: Ordering[T]) : List[T] = {
    val tempList = mutable.Seq[T](list:_*)

    for {
      i <- 0 until list.size
      j <- i + 1 until list.size
    } yield ""

    tempList.toList
  }

}
 trait Ordering[T] {
  def isSmall(left: T, right: T): Boolean
}


object Test2 extends App {
  val l1 = List(5,4,3,2,1)
  implicit val intOrdering = new Ordering[Int] {
    def isSmall(left: Int, right: Int) = left < right
  }

  val sortedList = typlessClassEx.mySort(l1)
  println(sortedList)
}