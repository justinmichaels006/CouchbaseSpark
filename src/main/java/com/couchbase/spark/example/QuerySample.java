package com.couchbase.spark.example;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.spark.japi.CouchbaseSparkContext;
import com.couchbase.spark.rdd.CouchbaseQueryRow;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

import java.io.IOException;
import java.util.List;

public class QuerySample {

    public static void main(String[] args) throws IOException {

        // Configure Spark
        SparkConf conf = new SparkConf()
                .setAppName("CouchbaseQuery")
                .setMaster("local[*]")
                .set("com.couchbase.bucket.travel-sample", "");


        // Create Java context from configuration
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Create SQL context from Java context
        SQLContext sqlContext = new SQLContext(sc);

        // Create Couchbase context using static method
        CouchbaseSparkContext couchbaseSparkContext = CouchbaseSparkContext.couchbaseContext(sc);

        //String query = ("SELECT * FROM `travel-sample` WHERE type = 'airline' LIMIT 10");
        String query = ("select airline, count(*) from `travel-sample` group by airline");

        List<CouchbaseQueryRow> results = couchbaseSparkContext
                .couchbaseQuery(N1qlQuery.simple(query))
                .collect();

        System.out.println(results);


    }
}
