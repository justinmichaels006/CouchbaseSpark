import com.couchbase.client.java.document.JsonDocument
import com.couchbase.spark._
import org.apache.spark.{SparkConf, SparkContext}

object SubdocSample {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DatasetSample")
      .set("com.couchbase.bucket.travel-sample", "")

    val sc = new SparkContext(conf)

    val result = sc
      .couchbaseSubdocLookup(Seq("airline_10"), Seq("name", "iata"), exists = Seq("type"))
      .collect()

    // Prints
    // SubdocLookupResult(
    //    airline_10,0,Map(name -> 40-Mile Air, iata -> Q5)
    // )
    result.foreach(println)

    // Prints
    // airline_10 document
    //{
    //"id": 10,
    //"type": "airline",
    //"name": "40-Mile Air",
    //"iata": "Q5",
    //"icao": "MLA",
    //"callsign": "MILE-AIR",
    //"country": "United States"
    //}

    val r3 = sc
      .parallelize(Seq("airline_10")) // Define Document IDs
      .couchbaseGet[JsonDocument]() // Load document from Couchbase
      .map(_.content()) // extract the content
      .collect() // collect all data
      .foreach(println) // print it out

  }
}
