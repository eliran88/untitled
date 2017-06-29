import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator

/**
  * Created by eliranm on 28/06/2017.
  */
object calculator {
  def Addition[T](operandA:T, operandB : T) (implicit operator : Operators[T]): T = {
    operator.adder(operandA,operandB)
  }
  def Subtraction[T](operandA:T, operandB : T) (implicit operator : Operators[T]): T = {
    operator.subber(operandA,operandB)
  }

}

trait Operators[T] {
  def adder(left: T, right: T): T
  def subber(left: T, right: T): T

}



object TestCalc extends App {

//  implicit val intOperators = new Operators[Int] {
//    def adder(a: Int, b: Int) = a+b
//    def subber(a: Int, b: Int) = a-b
//  }
//  implicit val floatOperators = new Operators[Float] {
//    def adder(a: Float, b: Float) = a+b
//    def subber(a: Float, b: Float) = a-b
//  }
//
//  implicit class ExtendsCalc(calc: Operators[Int]) {
//    def Multiply(operandA: Int, operandB: Int) : Int = {
//      //run operandB times
//      var sum = operandA
//      for (i <- 1 to operandB) yield sum = calc.adder(operandA,sum)
//      sum
//    }
//  }
//
//
//
//  println(calculator.Addition(1,1))
//  println(calculator.Addition(1.1f,1.1f))
//  println(calculator.Multiply(1,1))
}