package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * todo: class comment
 *
 * @author traznatovic
 */
public class HelloWorldSparkFreemarkerStyle
{
  public static void main(String[] args)
  {
    final Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

    Spark.get("/", new Route()
    {
      @Override
      public Object handle(final Request request, final Response response)
      {
        StringWriter writer = new StringWriter();
        try
        {
          Template helloTemplate = configuration.getTemplate("hello.ftl");

          Map<String, Object> helloMap = new HashMap<String, Object>();
          helloMap.put("name", "FreeMarker");

          helloTemplate.process(helloMap, writer);

          System.out.println(writer);
        }
        catch (Exception e)
        {
          halt(500);
          e.printStackTrace();
        }
        return writer;
      }

    });
  }
}
