import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.couchbase.spark._
import com.couchbase.spark.sql._
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.EqualTo
import org.apache.spark.{SparkConf, SparkContext}

object Quickstart {

  def main(args: Array[String]): Unit = {

    // Configure Spark
    val cfg = new SparkConf()
      .setAppName("couchbaseQuickstart") // give your app a name
      .setMaster("spark://Justins-MacBook-Pro-2.local:7077") // set the master to local for easy experimenting
      .set("com.couchbase.bucket.travel-sample", "") // open the travel-sample bucket

    // Generate The Context
    val sc = new SparkContext(cfg)
    val sql = new SQLContext(sc)

    sc
      .couchbaseGet[JsonDocument](Seq("airline_10123", "airline_10748"))
      .collect()
      .foreach(println)

    sc
      .couchbaseGet[JsonDocument](Seq("airline_10123", "airline_10748"))
      .map(oldDoc => {
        val id = "my_" + oldDoc.id()
        val content = JsonObject.create().put("name", oldDoc.content().getString("name"))
        JsonDocument.create(id, content)
      })
      .saveToCouchbase()

    // Create a DataFrame with Schema Inference
    val airlines = sql.read.couchbase(schemaFilter = EqualTo("type", "airline"))

    // Print The Schema
    airlines.printSchema()

  }

}