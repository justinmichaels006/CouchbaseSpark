

/**
  * Created by justin on 3/23/16.
  */
class ClickLoader {

  /*val env: CouchbaseEnvironment = DefaultCouchbaseEnvironment.builder.queryEnabled(true).dnsSrvEnabled(false).build
  val cluster: CouchbaseCluster = CouchbaseCluster.create(env, "192.168.61.101")
  val bucket: Bucket = cluster.openBucket("testload")

  val conf: SparkConf = new SparkConf().setAppName("myApp").setMaster("spark://Justins-MacBook-Pro-2.local:7077")
  val sc: JavaSparkContext = new JavaSparkContext(conf)

  // The Couchbase-Enabled spark context
  val csc: CouchbaseSparkContext = couchbaseContext(sc)

  val data: util.Random = Array.asInstanceOf //(1, 2, 3, 4, 5)
  val distData: JavaRDD[Integer] = sc.parallelize(data)
  System.out.println("Dist:  " + distData)

  val lines: JavaRDD[String] = sc.textFile("/Users/justin/Documents/Demo/spark/spark-1.6.0-bin-hadoop2.6/data/mllib/sample_isotonic_regression_data.txt")
  val lineLengths: JavaRDD[Integer] = lines.map(new Function[String, Integer]() {
    def call(s: String): Integer = {
      return s.length
    }
  })


  val totalLength: Int = lineLengths.reduce(new Function2[Integer, Integer, Integer]() {
    def call(a: Integer, b: Integer): Integer = {
      return a + b
    }
  })
  System.out.println("Line Count:  " + totalLength)

  // clickstream
  {  "timestamp" : 123,
    "url" : "a.html"  ,
    "userid" : 1,
    "ref_domain" : "facebook.com",
  }
  {  "timestamp" : 124,
    "url" : "b.html"  ,
    "userid" : 2,
    "ref_domain" : "cnn.com",
  }

  // user data
  {
    "id":1,
    "username" : "sujee",
    "age": 40,
  }
  {
    "id":2,
    "username" : "justin",
    "age": 20,
  }

  // site data
  {
    "site" : "facebook.com",
    "category" : "social"
  }
  {
    "site" : "cnn.com",
    "category" : "news"
  }
*/
}
