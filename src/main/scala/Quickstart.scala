import com.couchbase.spark.sql._
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.EqualTo
import org.apache.spark.{SparkConf, SparkContext}

object Quickstart {

  def main(args: Array[String]): Unit = {

    // Configure Spark
    val cfg = new SparkConf()
      .setAppName("couchbaseQuickstart")
      .setMaster("local[*]")
      .set("com.couchbase.bucket.travel-sample", "")

    // Generate The Context
    val sc = new SparkContext(cfg)
    val sqlc = new SQLContext(sc)

    // Load Landmarks from HDFS
    val landmarks = sqlc.read.couchbase(schemaFilter = EqualTo("type", "landmark"))
    landmarks.registerTempTable("landmarks")

    // Load Airports from Couchbase
    val airports = sqlc.read.couchbase(schemaFilter = EqualTo("type", "airport"))
    airports.registerTempTable("airports")

    // find all landmarks in the same city as the given FAA code
    val toFind = "SFO" // try SFO or LAX

    airports
      .join(landmarks, airports("city") === landmarks("city"))
      .select(airports("faa"), landmarks("name"), landmarks("url"))
      .where(airports("faa") === toFind and landmarks("url").isNotNull)
      .orderBy(landmarks("name").asc)
      .show(20)

  }

}