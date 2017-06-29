import java.rmi.activation.ActivationGroup_Stub

/**
  * Created by eliranm on 27/06/2017.
  */
class jsonex {

}

case class JsonObject(data: Map[JsonString, JsonValue]) extends JsonValue {

  override def compactPrint: String = {
    data.map(x => s"${x._1.compactPrint}: ${x._2.compactPrint}").mkString("{", ",", "}")
  }

  override def prettyPrint: String = {
    data.map(x => s"${x._1.prettyPrint}: ${x._2.prettyPrint}").mkString("{\n\t", ",\n\t", "\n}")
  }
//  def add(fields: List[JsonField]) = {
//    fields.foreach(field => data += (field.key -> field.value))
//  }

//  def replace(newField: JsonField) = {
//    data = data + (newField.key -> newField.value)
//  }
}

object JsonObject{

  def add(obj: JsonObject, fields: List[JsonField]): JsonObject = {
    JsonObject(obj.data ++ fields.map(field => field.key -> field.value).toMap)
  }

  def combine(fields: List[JsonField]): JsonObject = {
    JsonObject(fields.map(field => field.key -> field.value).toMap)
  }
  def replace(obj: JsonObject, newField: JsonField) = {
    JsonObject(obj.data + (newField.key -> newField.value))
  }
}

case class JsonField(key: JsonString, value: JsonValue)


trait JsonValue {
  def compactPrint: String
  def prettyPrint: String
}

case class JsonInt(data: Int) extends JsonValue {
  override def compactPrint: String = data.toString()
  override def prettyPrint: String = data.toString()
}

case class JsonDouble(data: Double) extends JsonValue {
  override def compactPrint: String = data.toString()
  override def prettyPrint: String = data.toString()
}

case class JsonString(data: String) extends JsonValue {
  override def compactPrint: String = s""""$data""""
  override def prettyPrint: String = s""""$data""""
}

case class JsonArray(data: Array[JsonValue]) extends JsonValue {
  override def compactPrint: String = data.map(element => element.compactPrint).mkString("[", ", ", "]")

  override def prettyPrint: String = data.map(element => element.prettyPrint).mkString("[\n\t\t", ",\n\t\t", "\n\t]")
}

case class JsonBoolean(data: Boolean) extends JsonValue {
  override def compactPrint: String = data.toString()
  override def prettyPrint: String = data.toString
}

object JsonNull extends JsonValue {
  override def compactPrint: String = "null"
  override def prettyPrint: String = "null"
}

object Json {
  val arrayPattern = "\\[(.*)\\]".r
  val fieldPattern = "(.*)\\:(.*)".r

  def jsonParse(str: String) : JsonValue = str match {
    case  x if (x.charAt(0) == '{' && x.charAt(x.length()-1) == '}') => {
      //split to fields and
      JsonString(s"you're in object pattern ${x.substring(1,x.length()-1)}")
    }
    case arrayPattern(middle) => JsonString(s"you're in arrayPattern: $middle")
    case fieldPattern(left,right) => JsonString(s"you're in field pattern: $left:$right")
    case "null" => JsonNull
    case "true" => JsonBoolean(true)
    case "false" => JsonBoolean(false)
  }
}

object Test extends App {
  val lastName = JsonString("lastName")
  val homeTown = JsonField(JsonString("hometown"), JsonString("Yehud"))
  val school = JsonField(JsonString("school"), JsonArray(Array(JsonString("Makif High School"), JsonString("Ben Gurion University"))))
  val myJson = JsonObject.combine(
    List(JsonField(JsonString("firstName"), JsonString("Eliran")),
      JsonField(lastName,JsonString("Marhoome")),
      JsonField(JsonString("age"),JsonInt(29)),
      JsonField(JsonString("city"), JsonString("Tel Aviv")),
      JsonField(JsonString("married"),JsonBoolean(true)),
      JsonField(JsonString("children"), JsonArray(Array(JsonString("a"), JsonString("b"))))
      ))
  println(myJson.compactPrint)
  println(JsonObject.replace(myJson,JsonField(lastName, JsonString("Marom"))).compactPrint)
  println(JsonObject.add(myJson, List(school, homeTown, JsonField(JsonString("object"),JsonObject(Map(JsonString("name") -> JsonString("Eliran")))))).compactPrint)
//  println(myJson.prettyPrint)
  println(Json.jsonParse("[1,2,3,4]").compactPrint)
  val key = """"key""""
  val value = """"value""""
  println(Json.jsonParse(s"$key:$value").compactPrint)
  println(Json.jsonParse(s"{$key:$value}").compactPrint)



}
