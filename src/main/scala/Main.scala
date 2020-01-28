import java.util.Date
import scala.collection.mutable

abstract class Book {
  val author: String
  val title: String
  val genre: String
  val price: Float
  val published_date: Date
  val description: String
}

object Main extends App {
  val xml = scala.xml.XML.loadFile("src/books.xml")
  var hashMap = new mutable.HashMap[String, Book]()
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  for (element <- xml \\ "book") {
    var id = (element \ "@id").toString()
    var book = new Book {
      override val author: String = (element \ "author").text
      override val title: String = (element \ "title").text
      override val genre: String = (element \ "genre").text
      override val price: Float = (element \ "price").text.toFloat
      override val published_date: Date = format.parse((element \ "publish_date").text)
      override val description: String = (element \ "description").text
    }
    hashMap += (id -> book)
    //println(value)
  }
  println(hashMap("bk112").title)
}