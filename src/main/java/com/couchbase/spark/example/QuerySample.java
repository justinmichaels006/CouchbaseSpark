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

        String query = ("select name, count(*) as count from `travel-sample` group by name");

        String query2 = ("explain SELECT\n" +
                "   config.Config.CatalogId,\n" +
                "   config.Config.FamilyName,\n" +
                "   config.Config.Title,\n" +
                "   (\n" +
                "      SELECT\n" +
                "         c.Id,\n" +
                "         c.`Desc` as CategoryDescription,\n" +
                "         (\n" +
                "            SELECT\n" +
                "               m.Id,\n" +
                "               m.ModuleName,\n" +
                "               m.ModuleDescription,\n" +
                "               (\n" +
                "                  SELECT\n" +
                "                     o.Id,\n" +
                "                     o.OptionName,\n" +
                "                     o.OptionDescription,\n" +
                "                     (\n" +
                "                        SELECT\n" +
                "                           s.Id,\n" +
                "                           s.SkuName,\n" +
                "                           s.SkuDescription\n" +
                "                        FROM testload sku\n" +
                "                           USE KEYS o.SKUs\n" +
                "                           LEFT UNNEST sku.SKUs.SKU s\n" +
                "                     ) AS SKU\n" +
                "                  FROM testload opt\n" +
                "                     USE KEYS m.Options\n" +
                "                     LEFT UNNEST opt.Options.`Option` o\n" +
                "               ) AS `Option`\n" +
                "            FROM testload mod\n" +
                "               USE KEYS c.Modules\n" +
                "               LEFT UNNEST mod.Modules.Module m\n" +
                "         ) AS Module\n" +
                "      FROM testload cat\n" +
                "         USE KEYS config.Config.Categories\n" +
                "         LEFT UNNEST cat.Categories.Category c\n" +
                "   ) AS Category\n" +
                "FROM testload config\n" +
                "   USE KEYS \"OC_c1660wsap_29\"\n");

        List<CouchbaseQueryRow> results = couchbaseSparkContext
                .couchbaseQuery(N1qlQuery.simple(query))
                .collect();

        System.out.println(results);


    }
}
